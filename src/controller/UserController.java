/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import model.User;

/**
 *
 * @author arnau
 */
public class UserController {
    EntityManagerFactory emf;
    
    public UserController(EntityManagerFactory emf){
      this.emf = emf;  
    }
    
    public User create(User u){
        EntityManager em = emf.createEntityManager();
        EntityTransaction transac = em.getTransaction();
        transac.begin();
        em.persist(u);
        transac.commit();
        return u;
    }
    
    public User read(String email) {
        
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("User.findByEmail");
        q.setParameter("email", email);
        User u = (User) q.getSingleResult();
        return u;
    }
    
}
