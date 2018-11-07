/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rouelibreapp;

import controller.UserController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.User;


/**
 *
 * @author arnau
 */
public class RoueLibreApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        User u = new User("morgana", "laise", "BONNNNJOUUUUUUR", "fds@lkkkkl.Com", "mdp");
//        u.setGender(Gender.FEMALE);
//        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("roueLibreAppPU");
        UserController userController = new UserController(emf);
//        userController.create(new User("Yo", "Plait", "Fromage frais", "CMonYop@gmail.com", "woké"));
        User userRead = userController.read("CMonYop@gmail.com");
        System.out.println(userRead.getIdUser());


        
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction transac = em.getTransaction();
//        transac.begin();
//        em.persist(u);
//        transac.commit();
    }
    
}
