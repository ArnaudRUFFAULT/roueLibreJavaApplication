/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author arnau
 */
public class ReviewTest {
    
    public ReviewTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getIdReview method, of class Review.
     */
    @Test
    public void testGetIdReview() {
        System.out.println("getIdReview");
        Review instance = new Review();
        Integer expResult = null;
        Integer result = instance.getIdReview();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdReview method, of class Review.
     */
    @Test
    public void testSetIdReview() {
        System.out.println("setIdReview");
        Integer idReview = null;
        Review instance = new Review();
        instance.setIdReview(idReview);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReview method, of class Review.
     */
    @Test
    public void testGetReview() {
        System.out.println("getReview");
        Review instance = new Review();
        String expResult = "";
        String result = instance.getReview();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setReview method, of class Review.
     */
    @Test
    public void testSetReview() {
        System.out.println("setReview");
        String review = "";
        Review instance = new Review();
        instance.setReview(review);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRecommendation method, of class Review.
     */
    @Test
    public void testGetRecommendation() {
        System.out.println("getRecommendation");
        Review instance = new Review();
        boolean expResult = false;
        boolean result = instance.getRecommendation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRecommendation method, of class Review.
     */
    @Test
    public void testSetRecommendation() {
        System.out.println("setRecommendation");
        boolean recommendation = false;
        Review instance = new Review();
        instance.setRecommendation(recommendation);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDate method, of class Review.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        Review instance = new Review();
        Calendar expResult = null;
        Calendar result = instance.getDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDate method, of class Review.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        Calendar date = null;
        Review instance = new Review();
        instance.setDate(date);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRating method, of class Review.
     */
    @Test
    public void testGetRating() {
        System.out.println("getRating");
        Review instance = new Review();
        int expResult = 0;
        int result = instance.getRating();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRating method, of class Review.
     */
    @Test
    public void testSetRating() {
        System.out.println("setRating");
        int rating = 0;
        Review instance = new Review();
        instance.setRating(rating);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIdUserisAbout method, of class Review.
     */
    @Test
    public void testGetIdUserisAbout() {
        System.out.println("getIdUserisAbout");
        Review instance = new Review();
        User expResult = null;
        User result = instance.getIdUserisAbout();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdUserisAbout method, of class Review.
     */
    @Test
    public void testSetIdUserisAbout() {
        System.out.println("setIdUserisAbout");
        User idUserisAbout = null;
        Review instance = new Review();
        instance.setIdUserisAbout(idUserisAbout);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIdUser method, of class Review.
     */
    @Test
    public void testGetIdUser() {
        System.out.println("getIdUser");
        Review instance = new Review();
        User expResult = null;
        User result = instance.getIdUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdUser method, of class Review.
     */
    @Test
    public void testSetIdUser() {
        System.out.println("setIdUser");
        User idUser = null;
        Review instance = new Review();
        instance.setIdUser(idUser);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Review.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Review instance = new Review();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Review.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Review instance = new Review();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Review.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Review instance = new Review();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
