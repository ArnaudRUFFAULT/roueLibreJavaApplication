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
import model.Adress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Product;
import model.Review;
import model.Rent;
import model.User;

/**
 *
 * @author jeremy
 */
public class UserJpaController implements Serializable {

    public UserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(User user) {
        if (user.getAdressCollection() == null) {
            user.setAdressCollection(new ArrayList<Adress>());
        }
        if (user.getProductCollection() == null) {
            user.setProductCollection(new ArrayList<Product>());
        }
        if (user.getReviewCollection() == null) {
            user.setReviewCollection(new ArrayList<Review>());
        }
        if (user.getReviewCollection1() == null) {
            user.setReviewCollection1(new ArrayList<Review>());
        }
        if (user.getRentCollection() == null) {
            user.setRentCollection(new ArrayList<Rent>());
        }
        if (user.getRentCollection1() == null) {
            user.setRentCollection1(new ArrayList<Rent>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Adress> attachedAdressCollection = new ArrayList<Adress>();
            for (Adress adressCollectionAdressToAttach : user.getAdressCollection()) {
                adressCollectionAdressToAttach = em.getReference(adressCollectionAdressToAttach.getClass(), adressCollectionAdressToAttach.getIdAdress());
                attachedAdressCollection.add(adressCollectionAdressToAttach);
            }
            user.setAdressCollection(attachedAdressCollection);
            Collection<Product> attachedProductCollection = new ArrayList<Product>();
            for (Product productCollectionProductToAttach : user.getProductCollection()) {
                productCollectionProductToAttach = em.getReference(productCollectionProductToAttach.getClass(), productCollectionProductToAttach.getIdProduct());
                attachedProductCollection.add(productCollectionProductToAttach);
            }
            user.setProductCollection(attachedProductCollection);
            Collection<Review> attachedReviewCollection = new ArrayList<Review>();
            for (Review reviewCollectionReviewToAttach : user.getReviewCollection()) {
                reviewCollectionReviewToAttach = em.getReference(reviewCollectionReviewToAttach.getClass(), reviewCollectionReviewToAttach.getIdReview());
                attachedReviewCollection.add(reviewCollectionReviewToAttach);
            }
            user.setReviewCollection(attachedReviewCollection);
            Collection<Review> attachedReviewCollection1 = new ArrayList<Review>();
            for (Review reviewCollection1ReviewToAttach : user.getReviewCollection1()) {
                reviewCollection1ReviewToAttach = em.getReference(reviewCollection1ReviewToAttach.getClass(), reviewCollection1ReviewToAttach.getIdReview());
                attachedReviewCollection1.add(reviewCollection1ReviewToAttach);
            }
            user.setReviewCollection1(attachedReviewCollection1);
            Collection<Rent> attachedRentCollection = new ArrayList<Rent>();
            for (Rent rentCollectionRentToAttach : user.getRentCollection()) {
                rentCollectionRentToAttach = em.getReference(rentCollectionRentToAttach.getClass(), rentCollectionRentToAttach.getIdRent());
                attachedRentCollection.add(rentCollectionRentToAttach);
            }
            user.setRentCollection(attachedRentCollection);
            Collection<Rent> attachedRentCollection1 = new ArrayList<Rent>();
            for (Rent rentCollection1RentToAttach : user.getRentCollection1()) {
                rentCollection1RentToAttach = em.getReference(rentCollection1RentToAttach.getClass(), rentCollection1RentToAttach.getIdRent());
                attachedRentCollection1.add(rentCollection1RentToAttach);
            }
            user.setRentCollection1(attachedRentCollection1);
            em.persist(user);
            for (Adress adressCollectionAdress : user.getAdressCollection()) {
                adressCollectionAdress.getUserCollection().add(user);
                adressCollectionAdress = em.merge(adressCollectionAdress);
            }
            for (Product productCollectionProduct : user.getProductCollection()) {
                User oldIdUserOfProductCollectionProduct = productCollectionProduct.getIdUser();
                productCollectionProduct.setIdUser(user);
                productCollectionProduct = em.merge(productCollectionProduct);
                if (oldIdUserOfProductCollectionProduct != null) {
                    oldIdUserOfProductCollectionProduct.getProductCollection().remove(productCollectionProduct);
                    oldIdUserOfProductCollectionProduct = em.merge(oldIdUserOfProductCollectionProduct);
                }
            }
            for (Review reviewCollectionReview : user.getReviewCollection()) {
                User oldIdUserisAboutOfReviewCollectionReview = reviewCollectionReview.getIdUserisAbout();
                reviewCollectionReview.setIdUserisAbout(user);
                reviewCollectionReview = em.merge(reviewCollectionReview);
                if (oldIdUserisAboutOfReviewCollectionReview != null) {
                    oldIdUserisAboutOfReviewCollectionReview.getReviewCollection().remove(reviewCollectionReview);
                    oldIdUserisAboutOfReviewCollectionReview = em.merge(oldIdUserisAboutOfReviewCollectionReview);
                }
            }
            for (Review reviewCollection1Review : user.getReviewCollection1()) {
                User oldIdUserOfReviewCollection1Review = reviewCollection1Review.getIdUser();
                reviewCollection1Review.setIdUser(user);
                reviewCollection1Review = em.merge(reviewCollection1Review);
                if (oldIdUserOfReviewCollection1Review != null) {
                    oldIdUserOfReviewCollection1Review.getReviewCollection1().remove(reviewCollection1Review);
                    oldIdUserOfReviewCollection1Review = em.merge(oldIdUserOfReviewCollection1Review);
                }
            }
            for (Rent rentCollectionRent : user.getRentCollection()) {
                User oldIdUserOfRentCollectionRent = rentCollectionRent.getIdUser();
                rentCollectionRent.setIdUser(user);
                rentCollectionRent = em.merge(rentCollectionRent);
                if (oldIdUserOfRentCollectionRent != null) {
                    oldIdUserOfRentCollectionRent.getRentCollection().remove(rentCollectionRent);
                    oldIdUserOfRentCollectionRent = em.merge(oldIdUserOfRentCollectionRent);
                }
            }
            for (Rent rentCollection1Rent : user.getRentCollection1()) {
                User oldIdUserborrowOfRentCollection1Rent = rentCollection1Rent.getIdUserborrow();
                rentCollection1Rent.setIdUserborrow(user);
                rentCollection1Rent = em.merge(rentCollection1Rent);
                if (oldIdUserborrowOfRentCollection1Rent != null) {
                    oldIdUserborrowOfRentCollection1Rent.getRentCollection1().remove(rentCollection1Rent);
                    oldIdUserborrowOfRentCollection1Rent = em.merge(oldIdUserborrowOfRentCollection1Rent);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(User user) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User persistentUser = em.find(User.class, user.getIdUser());
            Collection<Adress> adressCollectionOld = persistentUser.getAdressCollection();
            Collection<Adress> adressCollectionNew = user.getAdressCollection();
            Collection<Product> productCollectionOld = persistentUser.getProductCollection();
            Collection<Product> productCollectionNew = user.getProductCollection();
            Collection<Review> reviewCollectionOld = persistentUser.getReviewCollection();
            Collection<Review> reviewCollectionNew = user.getReviewCollection();
            Collection<Review> reviewCollection1Old = persistentUser.getReviewCollection1();
            Collection<Review> reviewCollection1New = user.getReviewCollection1();
            Collection<Rent> rentCollectionOld = persistentUser.getRentCollection();
            Collection<Rent> rentCollectionNew = user.getRentCollection();
            Collection<Rent> rentCollection1Old = persistentUser.getRentCollection1();
            Collection<Rent> rentCollection1New = user.getRentCollection1();
            List<String> illegalOrphanMessages = null;
            for (Product productCollectionOldProduct : productCollectionOld) {
                if (!productCollectionNew.contains(productCollectionOldProduct)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Product " + productCollectionOldProduct + " since its idUser field is not nullable.");
                }
            }
            for (Review reviewCollectionOldReview : reviewCollectionOld) {
                if (!reviewCollectionNew.contains(reviewCollectionOldReview)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Review " + reviewCollectionOldReview + " since its idUserisAbout field is not nullable.");
                }
            }
            for (Review reviewCollection1OldReview : reviewCollection1Old) {
                if (!reviewCollection1New.contains(reviewCollection1OldReview)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Review " + reviewCollection1OldReview + " since its idUser field is not nullable.");
                }
            }
            for (Rent rentCollectionOldRent : rentCollectionOld) {
                if (!rentCollectionNew.contains(rentCollectionOldRent)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Rent " + rentCollectionOldRent + " since its idUser field is not nullable.");
                }
            }
            for (Rent rentCollection1OldRent : rentCollection1Old) {
                if (!rentCollection1New.contains(rentCollection1OldRent)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Rent " + rentCollection1OldRent + " since its idUserborrow field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Adress> attachedAdressCollectionNew = new ArrayList<Adress>();
            for (Adress adressCollectionNewAdressToAttach : adressCollectionNew) {
                adressCollectionNewAdressToAttach = em.getReference(adressCollectionNewAdressToAttach.getClass(), adressCollectionNewAdressToAttach.getIdAdress());
                attachedAdressCollectionNew.add(adressCollectionNewAdressToAttach);
            }
            adressCollectionNew = attachedAdressCollectionNew;
            user.setAdressCollection(adressCollectionNew);
            Collection<Product> attachedProductCollectionNew = new ArrayList<Product>();
            for (Product productCollectionNewProductToAttach : productCollectionNew) {
                productCollectionNewProductToAttach = em.getReference(productCollectionNewProductToAttach.getClass(), productCollectionNewProductToAttach.getIdProduct());
                attachedProductCollectionNew.add(productCollectionNewProductToAttach);
            }
            productCollectionNew = attachedProductCollectionNew;
            user.setProductCollection(productCollectionNew);
            Collection<Review> attachedReviewCollectionNew = new ArrayList<Review>();
            for (Review reviewCollectionNewReviewToAttach : reviewCollectionNew) {
                reviewCollectionNewReviewToAttach = em.getReference(reviewCollectionNewReviewToAttach.getClass(), reviewCollectionNewReviewToAttach.getIdReview());
                attachedReviewCollectionNew.add(reviewCollectionNewReviewToAttach);
            }
            reviewCollectionNew = attachedReviewCollectionNew;
            user.setReviewCollection(reviewCollectionNew);
            Collection<Review> attachedReviewCollection1New = new ArrayList<Review>();
            for (Review reviewCollection1NewReviewToAttach : reviewCollection1New) {
                reviewCollection1NewReviewToAttach = em.getReference(reviewCollection1NewReviewToAttach.getClass(), reviewCollection1NewReviewToAttach.getIdReview());
                attachedReviewCollection1New.add(reviewCollection1NewReviewToAttach);
            }
            reviewCollection1New = attachedReviewCollection1New;
            user.setReviewCollection1(reviewCollection1New);
            Collection<Rent> attachedRentCollectionNew = new ArrayList<Rent>();
            for (Rent rentCollectionNewRentToAttach : rentCollectionNew) {
                rentCollectionNewRentToAttach = em.getReference(rentCollectionNewRentToAttach.getClass(), rentCollectionNewRentToAttach.getIdRent());
                attachedRentCollectionNew.add(rentCollectionNewRentToAttach);
            }
            rentCollectionNew = attachedRentCollectionNew;
            user.setRentCollection(rentCollectionNew);
            Collection<Rent> attachedRentCollection1New = new ArrayList<Rent>();
            for (Rent rentCollection1NewRentToAttach : rentCollection1New) {
                rentCollection1NewRentToAttach = em.getReference(rentCollection1NewRentToAttach.getClass(), rentCollection1NewRentToAttach.getIdRent());
                attachedRentCollection1New.add(rentCollection1NewRentToAttach);
            }
            rentCollection1New = attachedRentCollection1New;
            user.setRentCollection1(rentCollection1New);
            user = em.merge(user);
            for (Adress adressCollectionOldAdress : adressCollectionOld) {
                if (!adressCollectionNew.contains(adressCollectionOldAdress)) {
                    adressCollectionOldAdress.getUserCollection().remove(user);
                    adressCollectionOldAdress = em.merge(adressCollectionOldAdress);
                }
            }
            for (Adress adressCollectionNewAdress : adressCollectionNew) {
                if (!adressCollectionOld.contains(adressCollectionNewAdress)) {
                    adressCollectionNewAdress.getUserCollection().add(user);
                    adressCollectionNewAdress = em.merge(adressCollectionNewAdress);
                }
            }
            for (Product productCollectionNewProduct : productCollectionNew) {
                if (!productCollectionOld.contains(productCollectionNewProduct)) {
                    User oldIdUserOfProductCollectionNewProduct = productCollectionNewProduct.getIdUser();
                    productCollectionNewProduct.setIdUser(user);
                    productCollectionNewProduct = em.merge(productCollectionNewProduct);
                    if (oldIdUserOfProductCollectionNewProduct != null && !oldIdUserOfProductCollectionNewProduct.equals(user)) {
                        oldIdUserOfProductCollectionNewProduct.getProductCollection().remove(productCollectionNewProduct);
                        oldIdUserOfProductCollectionNewProduct = em.merge(oldIdUserOfProductCollectionNewProduct);
                    }
                }
            }
            for (Review reviewCollectionNewReview : reviewCollectionNew) {
                if (!reviewCollectionOld.contains(reviewCollectionNewReview)) {
                    User oldIdUserisAboutOfReviewCollectionNewReview = reviewCollectionNewReview.getIdUserisAbout();
                    reviewCollectionNewReview.setIdUserisAbout(user);
                    reviewCollectionNewReview = em.merge(reviewCollectionNewReview);
                    if (oldIdUserisAboutOfReviewCollectionNewReview != null && !oldIdUserisAboutOfReviewCollectionNewReview.equals(user)) {
                        oldIdUserisAboutOfReviewCollectionNewReview.getReviewCollection().remove(reviewCollectionNewReview);
                        oldIdUserisAboutOfReviewCollectionNewReview = em.merge(oldIdUserisAboutOfReviewCollectionNewReview);
                    }
                }
            }
            for (Review reviewCollection1NewReview : reviewCollection1New) {
                if (!reviewCollection1Old.contains(reviewCollection1NewReview)) {
                    User oldIdUserOfReviewCollection1NewReview = reviewCollection1NewReview.getIdUser();
                    reviewCollection1NewReview.setIdUser(user);
                    reviewCollection1NewReview = em.merge(reviewCollection1NewReview);
                    if (oldIdUserOfReviewCollection1NewReview != null && !oldIdUserOfReviewCollection1NewReview.equals(user)) {
                        oldIdUserOfReviewCollection1NewReview.getReviewCollection1().remove(reviewCollection1NewReview);
                        oldIdUserOfReviewCollection1NewReview = em.merge(oldIdUserOfReviewCollection1NewReview);
                    }
                }
            }
            for (Rent rentCollectionNewRent : rentCollectionNew) {
                if (!rentCollectionOld.contains(rentCollectionNewRent)) {
                    User oldIdUserOfRentCollectionNewRent = rentCollectionNewRent.getIdUser();
                    rentCollectionNewRent.setIdUser(user);
                    rentCollectionNewRent = em.merge(rentCollectionNewRent);
                    if (oldIdUserOfRentCollectionNewRent != null && !oldIdUserOfRentCollectionNewRent.equals(user)) {
                        oldIdUserOfRentCollectionNewRent.getRentCollection().remove(rentCollectionNewRent);
                        oldIdUserOfRentCollectionNewRent = em.merge(oldIdUserOfRentCollectionNewRent);
                    }
                }
            }
            for (Rent rentCollection1NewRent : rentCollection1New) {
                if (!rentCollection1Old.contains(rentCollection1NewRent)) {
                    User oldIdUserborrowOfRentCollection1NewRent = rentCollection1NewRent.getIdUserborrow();
                    rentCollection1NewRent.setIdUserborrow(user);
                    rentCollection1NewRent = em.merge(rentCollection1NewRent);
                    if (oldIdUserborrowOfRentCollection1NewRent != null && !oldIdUserborrowOfRentCollection1NewRent.equals(user)) {
                        oldIdUserborrowOfRentCollection1NewRent.getRentCollection1().remove(rentCollection1NewRent);
                        oldIdUserborrowOfRentCollection1NewRent = em.merge(oldIdUserborrowOfRentCollection1NewRent);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = user.getIdUser();
                if (findUser(id) == null) {
                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
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
            User user;
            try {
                user = em.getReference(User.class, id);
                user.getIdUser();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Product> productCollectionOrphanCheck = user.getProductCollection();
            for (Product productCollectionOrphanCheckProduct : productCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Product " + productCollectionOrphanCheckProduct + " in its productCollection field has a non-nullable idUser field.");
            }
            Collection<Review> reviewCollectionOrphanCheck = user.getReviewCollection();
            for (Review reviewCollectionOrphanCheckReview : reviewCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Review " + reviewCollectionOrphanCheckReview + " in its reviewCollection field has a non-nullable idUserisAbout field.");
            }
            Collection<Review> reviewCollection1OrphanCheck = user.getReviewCollection1();
            for (Review reviewCollection1OrphanCheckReview : reviewCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Review " + reviewCollection1OrphanCheckReview + " in its reviewCollection1 field has a non-nullable idUser field.");
            }
            Collection<Rent> rentCollectionOrphanCheck = user.getRentCollection();
            for (Rent rentCollectionOrphanCheckRent : rentCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Rent " + rentCollectionOrphanCheckRent + " in its rentCollection field has a non-nullable idUser field.");
            }
            Collection<Rent> rentCollection1OrphanCheck = user.getRentCollection1();
            for (Rent rentCollection1OrphanCheckRent : rentCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Rent " + rentCollection1OrphanCheckRent + " in its rentCollection1 field has a non-nullable idUserborrow field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Adress> adressCollection = user.getAdressCollection();
            for (Adress adressCollectionAdress : adressCollection) {
                adressCollectionAdress.getUserCollection().remove(user);
                adressCollectionAdress = em.merge(adressCollectionAdress);
            }
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<User> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    public List<User> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
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

    public User findUser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<User> rt = cq.from(User.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
