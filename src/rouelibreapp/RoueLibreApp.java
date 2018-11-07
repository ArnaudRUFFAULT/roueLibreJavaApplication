/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rouelibreapp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import model.User;
import model.User.Gender;


/**
 *
 * @author arnau
 */
public class RoueLibreApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        User u = new User("morgana", "laise", "BONNNNJOUUUUUUR", "fds@lkkkkl.Com", "mdp");
        u.setGender(Gender.FEMALE);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("roueLibreAppPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transac = em.getTransaction();
        transac.begin();
        em.persist(u);
        transac.commit();
    }
    
}
