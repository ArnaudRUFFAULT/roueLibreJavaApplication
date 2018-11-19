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
import model.Product;
import model.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Rent;

/**
 *
 * @author jeremy
 */
public class RentJpaController implements Serializable {

    public RentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rent rent) throws IllegalOrphanException {
        List<String> illegalOrphanMessages = null;
        Product idProductOrphanCheck = rent.getIdProduct();
        if (idProductOrphanCheck != null) {
            Rent oldRentOfIdProduct = idProductOrphanCheck.getRent();
            if (oldRentOfIdProduct != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Product " + idProductOrphanCheck + " already has an item of type Rent whose idProduct column cannot be null. Please make another selection for the idProduct field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Product idProduct = rent.getIdProduct();
            if (idProduct != null) {
                idProduct = em.getReference(idProduct.getClass(), idProduct.getIdProduct());
                rent.setIdProduct(idProduct);
            }
            User idUser = rent.getIdUser();
            if (idUser != null) {
                idUser = em.getReference(idUser.getClass(), idUser.getIdUser());
                rent.setIdUser(idUser);
            }
            User idUserborrow = rent.getIdUserborrow();
            if (idUserborrow != null) {
                idUserborrow = em.getReference(idUserborrow.getClass(), idUserborrow.getIdUser());
                rent.setIdUserborrow(idUserborrow);
            }
            em.persist(rent);
            if (idProduct != null) {
                idProduct.setRent(rent);
                idProduct = em.merge(idProduct);
            }
            if (idUser != null) {
                idUser.getRentCollection().add(rent);
                idUser = em.merge(idUser);
            }
            if (idUserborrow != null) {
                idUserborrow.getRentCollection().add(rent);
                idUserborrow = em.merge(idUserborrow);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rent rent) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rent persistentRent = em.find(Rent.class, rent.getIdRent());
            Product idProductOld = persistentRent.getIdProduct();
            Product idProductNew = rent.getIdProduct();
            User idUserOld = persistentRent.getIdUser();
            User idUserNew = rent.getIdUser();
            User idUserborrowOld = persistentRent.getIdUserborrow();
            User idUserborrowNew = rent.getIdUserborrow();
            List<String> illegalOrphanMessages = null;
            if (idProductNew != null && !idProductNew.equals(idProductOld)) {
                Rent oldRentOfIdProduct = idProductNew.getRent();
                if (oldRentOfIdProduct != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Product " + idProductNew + " already has an item of type Rent whose idProduct column cannot be null. Please make another selection for the idProduct field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idProductNew != null) {
                idProductNew = em.getReference(idProductNew.getClass(), idProductNew.getIdProduct());
                rent.setIdProduct(idProductNew);
            }
            if (idUserNew != null) {
                idUserNew = em.getReference(idUserNew.getClass(), idUserNew.getIdUser());
                rent.setIdUser(idUserNew);
            }
            if (idUserborrowNew != null) {
                idUserborrowNew = em.getReference(idUserborrowNew.getClass(), idUserborrowNew.getIdUser());
                rent.setIdUserborrow(idUserborrowNew);
            }
            rent = em.merge(rent);
            if (idProductOld != null && !idProductOld.equals(idProductNew)) {
                idProductOld.setRent(null);
                idProductOld = em.merge(idProductOld);
            }
            if (idProductNew != null && !idProductNew.equals(idProductOld)) {
                idProductNew.setRent(rent);
                idProductNew = em.merge(idProductNew);
            }
            if (idUserOld != null && !idUserOld.equals(idUserNew)) {
                idUserOld.getRentCollection().remove(rent);
                idUserOld = em.merge(idUserOld);
            }
            if (idUserNew != null && !idUserNew.equals(idUserOld)) {
                idUserNew.getRentCollection().add(rent);
                idUserNew = em.merge(idUserNew);
            }
            if (idUserborrowOld != null && !idUserborrowOld.equals(idUserborrowNew)) {
                idUserborrowOld.getRentCollection().remove(rent);
                idUserborrowOld = em.merge(idUserborrowOld);
            }
            if (idUserborrowNew != null && !idUserborrowNew.equals(idUserborrowOld)) {
                idUserborrowNew.getRentCollection().add(rent);
                idUserborrowNew = em.merge(idUserborrowNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rent.getIdRent();
                if (findRent(id) == null) {
                    throw new NonexistentEntityException("The rent with id " + id + " no longer exists.");
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
            Rent rent;
            try {
                rent = em.getReference(Rent.class, id);
                rent.getIdRent();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rent with id " + id + " no longer exists.", enfe);
            }
            Product idProduct = rent.getIdProduct();
            if (idProduct != null) {
                idProduct.setRent(null);
                idProduct = em.merge(idProduct);
            }
            User idUser = rent.getIdUser();
            if (idUser != null) {
                idUser.getRentCollection().remove(rent);
                idUser = em.merge(idUser);
            }
            User idUserborrow = rent.getIdUserborrow();
            if (idUserborrow != null) {
                idUserborrow.getRentCollection().remove(rent);
                idUserborrow = em.merge(idUserborrow);
            }
            em.remove(rent);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rent> findRentEntities() {
        return findRentEntities(true, -1, -1);
    }

    public List<Rent> findRentEntities(int maxResults, int firstResult) {
        return findRentEntities(false, maxResults, firstResult);
    }

    private List<Rent> findRentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rent.class));
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

    public Rent findRent(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rent.class, id);
        } finally {
            em.close();
        }
    }

    public int getRentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rent> rt = cq.from(Rent.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
