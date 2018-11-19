/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Review;
import model.User;

/**
 *
 * @author jeremy
 */
public class ReviewJpaController implements Serializable {

    public ReviewJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Review review) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User idUserisAbout = review.getIdUserisAbout();
            if (idUserisAbout != null) {
                idUserisAbout = em.getReference(idUserisAbout.getClass(), idUserisAbout.getIdUser());
                review.setIdUserisAbout(idUserisAbout);
            }
            User idUser = review.getIdUser();
            if (idUser != null) {
                idUser = em.getReference(idUser.getClass(), idUser.getIdUser());
                review.setIdUser(idUser);
            }
            em.persist(review);
            if (idUserisAbout != null) {
                idUserisAbout.getReviewCollection().add(review);
                idUserisAbout = em.merge(idUserisAbout);
            }
            if (idUser != null) {
                idUser.getReviewCollection().add(review);
                idUser = em.merge(idUser);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Review review) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Review persistentReview = em.find(Review.class, review.getIdReview());
            User idUserisAboutOld = persistentReview.getIdUserisAbout();
            User idUserisAboutNew = review.getIdUserisAbout();
            User idUserOld = persistentReview.getIdUser();
            User idUserNew = review.getIdUser();
            if (idUserisAboutNew != null) {
                idUserisAboutNew = em.getReference(idUserisAboutNew.getClass(), idUserisAboutNew.getIdUser());
                review.setIdUserisAbout(idUserisAboutNew);
            }
            if (idUserNew != null) {
                idUserNew = em.getReference(idUserNew.getClass(), idUserNew.getIdUser());
                review.setIdUser(idUserNew);
            }
            review = em.merge(review);
            if (idUserisAboutOld != null && !idUserisAboutOld.equals(idUserisAboutNew)) {
                idUserisAboutOld.getReviewCollection().remove(review);
                idUserisAboutOld = em.merge(idUserisAboutOld);
            }
            if (idUserisAboutNew != null && !idUserisAboutNew.equals(idUserisAboutOld)) {
                idUserisAboutNew.getReviewCollection().add(review);
                idUserisAboutNew = em.merge(idUserisAboutNew);
            }
            if (idUserOld != null && !idUserOld.equals(idUserNew)) {
                idUserOld.getReviewCollection().remove(review);
                idUserOld = em.merge(idUserOld);
            }
            if (idUserNew != null && !idUserNew.equals(idUserOld)) {
                idUserNew.getReviewCollection().add(review);
                idUserNew = em.merge(idUserNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = review.getIdReview();
                if (findReview(id) == null) {
                    throw new NonexistentEntityException("The review with id " + id + " no longer exists.");
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
            Review review;
            try {
                review = em.getReference(Review.class, id);
                review.getIdReview();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The review with id " + id + " no longer exists.", enfe);
            }
            User idUserisAbout = review.getIdUserisAbout();
            if (idUserisAbout != null) {
                idUserisAbout.getReviewCollection().remove(review);
                idUserisAbout = em.merge(idUserisAbout);
            }
            User idUser = review.getIdUser();
            if (idUser != null) {
                idUser.getReviewCollection().remove(review);
                idUser = em.merge(idUser);
            }
            em.remove(review);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Review> findReviewEntities() {
        return findReviewEntities(true, -1, -1);
    }

    public List<Review> findReviewEntities(int maxResults, int firstResult) {
        return findReviewEntities(false, maxResults, firstResult);
    }

    private List<Review> findReviewEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Review.class));
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

    public Review findReview(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Review.class, id);
        } finally {
            em.close();
        }
    }

    public int getReviewCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Review> rt = cq.from(Review.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
