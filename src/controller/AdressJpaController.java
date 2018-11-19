/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Adress;
import model.Product;

/**
 *
 * @author jeremy
 */
public class AdressJpaController implements Serializable {

    public AdressJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Adress adress) {
        if (adress.getUserCollection() == null) {
            adress.setUserCollection(new ArrayList<User>());
        }
        if (adress.getProductCollection() == null) {
            adress.setProductCollection(new ArrayList<Product>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<User> attachedUserCollection = new ArrayList<User>();
            for (User userCollectionUserToAttach : adress.getUserCollection()) {
                userCollectionUserToAttach = em.getReference(userCollectionUserToAttach.getClass(), userCollectionUserToAttach.getIdUser());
                attachedUserCollection.add(userCollectionUserToAttach);
            }
            adress.setUserCollection(attachedUserCollection);
            Collection<Product> attachedProductCollection = new ArrayList<Product>();
            for (Product productCollectionProductToAttach : adress.getProductCollection()) {
                productCollectionProductToAttach = em.getReference(productCollectionProductToAttach.getClass(), productCollectionProductToAttach.getIdProduct());
                attachedProductCollection.add(productCollectionProductToAttach);
            }
            adress.setProductCollection(attachedProductCollection);
            em.persist(adress);
            for (User userCollectionUser : adress.getUserCollection()) {
                userCollectionUser.getAdressCollection().add(adress);
                userCollectionUser = em.merge(userCollectionUser);
            }
            for (Product productCollectionProduct : adress.getProductCollection()) {
                productCollectionProduct.getAdressCollection().add(adress);
                productCollectionProduct = em.merge(productCollectionProduct);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Adress adress) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Adress persistentAdress = em.find(Adress.class, adress.getIdAdress());
            Collection<User> userCollectionOld = persistentAdress.getUserCollection();
            Collection<User> userCollectionNew = adress.getUserCollection();
            Collection<Product> productCollectionOld = persistentAdress.getProductCollection();
            Collection<Product> productCollectionNew = adress.getProductCollection();
            Collection<User> attachedUserCollectionNew = new ArrayList<User>();
            for (User userCollectionNewUserToAttach : userCollectionNew) {
                userCollectionNewUserToAttach = em.getReference(userCollectionNewUserToAttach.getClass(), userCollectionNewUserToAttach.getIdUser());
                attachedUserCollectionNew.add(userCollectionNewUserToAttach);
            }
            userCollectionNew = attachedUserCollectionNew;
            adress.setUserCollection(userCollectionNew);
            Collection<Product> attachedProductCollectionNew = new ArrayList<Product>();
            for (Product productCollectionNewProductToAttach : productCollectionNew) {
                productCollectionNewProductToAttach = em.getReference(productCollectionNewProductToAttach.getClass(), productCollectionNewProductToAttach.getIdProduct());
                attachedProductCollectionNew.add(productCollectionNewProductToAttach);
            }
            productCollectionNew = attachedProductCollectionNew;
            adress.setProductCollection(productCollectionNew);
            adress = em.merge(adress);
            for (User userCollectionOldUser : userCollectionOld) {
                if (!userCollectionNew.contains(userCollectionOldUser)) {
                    userCollectionOldUser.getAdressCollection().remove(adress);
                    userCollectionOldUser = em.merge(userCollectionOldUser);
                }
            }
            for (User userCollectionNewUser : userCollectionNew) {
                if (!userCollectionOld.contains(userCollectionNewUser)) {
                    userCollectionNewUser.getAdressCollection().add(adress);
                    userCollectionNewUser = em.merge(userCollectionNewUser);
                }
            }
            for (Product productCollectionOldProduct : productCollectionOld) {
                if (!productCollectionNew.contains(productCollectionOldProduct)) {
                    productCollectionOldProduct.getAdressCollection().remove(adress);
                    productCollectionOldProduct = em.merge(productCollectionOldProduct);
                }
            }
            for (Product productCollectionNewProduct : productCollectionNew) {
                if (!productCollectionOld.contains(productCollectionNewProduct)) {
                    productCollectionNewProduct.getAdressCollection().add(adress);
                    productCollectionNewProduct = em.merge(productCollectionNewProduct);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = adress.getIdAdress();
                if (findAdress(id) == null) {
                    throw new NonexistentEntityException("The adress with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Adress adress;
            try {
                adress = em.getReference(Adress.class, id);
                adress.getIdAdress();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The adress with id " + id + " no longer exists.", enfe);
            }
            Collection<User> userCollection = adress.getUserCollection();
            for (User userCollectionUser : userCollection) {
                userCollectionUser.getAdressCollection().remove(adress);
                userCollectionUser = em.merge(userCollectionUser);
            }
            Collection<Product> productCollection = adress.getProductCollection();
            for (Product productCollectionProduct : productCollection) {
                productCollectionProduct.getAdressCollection().remove(adress);
                productCollectionProduct = em.merge(productCollectionProduct);
            }
            em.remove(adress);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Adress> findAdressEntities() {
        return findAdressEntities(true, -1, -1);
    }

    public List<Adress> findAdressEntities(int maxResults, int firstResult) {
        return findAdressEntities(false, maxResults, firstResult);
    }

    private List<Adress> findAdressEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Adress.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Adress findAdress(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Adress.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdressCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Adress> rt = cq.from(Adress.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
