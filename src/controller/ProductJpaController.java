/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.User;
import model.Rent;
import model.Adress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Product;

/**
 *
 * @author jeremy
 */
public class ProductJpaController implements Serializable {

    public ProductJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Product product) {
        if (product.getAdressCollection() == null) {
            product.setAdressCollection(new ArrayList<Adress>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User idUser = product.getIdUser();
            if (idUser != null) {
                idUser = em.getReference(idUser.getClass(), idUser.getIdUser());
                product.setIdUser(idUser);
            }
            Rent rent = product.getRent();
            if (rent != null) {
                rent = em.getReference(rent.getClass(), rent.getIdRent());
                product.setRent(rent);
            }
            Collection<Adress> attachedAdressCollection = new ArrayList<Adress>();
            for (Adress adressCollectionAdressToAttach : product.getAdressCollection()) {
                adressCollectionAdressToAttach = em.getReference(adressCollectionAdressToAttach.getClass(), adressCollectionAdressToAttach.getIdAdress());
                attachedAdressCollection.add(adressCollectionAdressToAttach);
            }
            product.setAdressCollection(attachedAdressCollection);
            em.persist(product);
            if (idUser != null) {
                idUser.getProductCollection().add(product);
                idUser = em.merge(idUser);
            }
            if (rent != null) {
                Product oldIdProductOfRent = rent.getIdProduct();
                if (oldIdProductOfRent != null) {
                    oldIdProductOfRent.setRent(null);
                    oldIdProductOfRent = em.merge(oldIdProductOfRent);
                }
                rent.setIdProduct(product);
                rent = em.merge(rent);
            }
            for (Adress adressCollectionAdress : product.getAdressCollection()) {
                adressCollectionAdress.getProductCollection().add(product);
                adressCollectionAdress = em.merge(adressCollectionAdress);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Product product) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Product persistentProduct = em.find(Product.class, product.getIdProduct());
            User idUserOld = persistentProduct.getIdUser();
            User idUserNew = product.getIdUser();
            Rent rentOld = persistentProduct.getRent();
            Rent rentNew = product.getRent();
            Collection<Adress> adressCollectionOld = persistentProduct.getAdressCollection();
            Collection<Adress> adressCollectionNew = product.getAdressCollection();
            List<String> illegalOrphanMessages = null;
            if (rentOld != null && !rentOld.equals(rentNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Rent " + rentOld + " since its idProduct field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idUserNew != null) {
                idUserNew = em.getReference(idUserNew.getClass(), idUserNew.getIdUser());
                product.setIdUser(idUserNew);
            }
            if (rentNew != null) {
                rentNew = em.getReference(rentNew.getClass(), rentNew.getIdRent());
                product.setRent(rentNew);
            }
            Collection<Adress> attachedAdressCollectionNew = new ArrayList<Adress>();
            for (Adress adressCollectionNewAdressToAttach : adressCollectionNew) {
                adressCollectionNewAdressToAttach = em.getReference(adressCollectionNewAdressToAttach.getClass(), adressCollectionNewAdressToAttach.getIdAdress());
                attachedAdressCollectionNew.add(adressCollectionNewAdressToAttach);
            }
            adressCollectionNew = attachedAdressCollectionNew;
            product.setAdressCollection(adressCollectionNew);
            product = em.merge(product);
            if (idUserOld != null && !idUserOld.equals(idUserNew)) {
                idUserOld.getProductCollection().remove(product);
                idUserOld = em.merge(idUserOld);
            }
            if (idUserNew != null && !idUserNew.equals(idUserOld)) {
                idUserNew.getProductCollection().add(product);
                idUserNew = em.merge(idUserNew);
            }
            if (rentNew != null && !rentNew.equals(rentOld)) {
                Product oldIdProductOfRent = rentNew.getIdProduct();
                if (oldIdProductOfRent != null) {
                    oldIdProductOfRent.setRent(null);
                    oldIdProductOfRent = em.merge(oldIdProductOfRent);
                }
                rentNew.setIdProduct(product);
                rentNew = em.merge(rentNew);
            }
            for (Adress adressCollectionOldAdress : adressCollectionOld) {
                if (!adressCollectionNew.contains(adressCollectionOldAdress)) {
                    adressCollectionOldAdress.getProductCollection().remove(product);
                    adressCollectionOldAdress = em.merge(adressCollectionOldAdress);
                }
            }
            for (Adress adressCollectionNewAdress : adressCollectionNew) {
                if (!adressCollectionOld.contains(adressCollectionNewAdress)) {
                    adressCollectionNewAdress.getProductCollection().add(product);
                    adressCollectionNewAdress = em.merge(adressCollectionNewAdress);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = product.getIdProduct();
                if (findProduct(id) == null) {
                    throw new NonexistentEntityException("The product with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Product product;
            try {
                product = em.getReference(Product.class, id);
                product.getIdProduct();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The product with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Rent rentOrphanCheck = product.getRent();
            if (rentOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Product (" + product + ") cannot be destroyed since the Rent " + rentOrphanCheck + " in its rent field has a non-nullable idProduct field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            User idUser = product.getIdUser();
            if (idUser != null) {
                idUser.getProductCollection().remove(product);
                idUser = em.merge(idUser);
            }
            Collection<Adress> adressCollection = product.getAdressCollection();
            for (Adress adressCollectionAdress : adressCollection) {
                adressCollectionAdress.getProductCollection().remove(product);
                adressCollectionAdress = em.merge(adressCollectionAdress);
            }
            em.remove(product);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Product> findProductEntities() {
        return findProductEntities(true, -1, -1);
    }

    public List<Product> findProductEntities(int maxResults, int firstResult) {
        return findProductEntities(false, maxResults, firstResult);
    }

    private List<Product> findProductEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Product.class));
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

    public Product findProduct(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Product.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Product> rt = cq.from(Product.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
