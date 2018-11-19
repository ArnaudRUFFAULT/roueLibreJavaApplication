/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rouelibreapp;

import controller.UserJpaController;
import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.User;


/**
 *
 * @author arnau
 */
public class RoueLibreApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IllegalOrphanException, NonexistentEntityException, Exception {
//        User u = new User("morgana", "laise", "BONNNNJOUUUUUUR", "fds@lkkkkl.Com", "mdp");
//        u.setGender(Gender.FEMALE);
//        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("roueLibreAppPU");
        UserJpaController userController = new UserJpaController(emf);
        //userController.create(new User ("Nico", "Ledeglingo", "BabaYaga", "Nicorette@gmail.th", "lolilol"));
        
        
        
        
        Nico.setLastname("Lechaud");
        userController.edit(Nico);
        

    }
    
}
