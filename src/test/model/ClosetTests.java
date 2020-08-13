package model;

import exceptions.DuplicateClothingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClosetTests {

    Closet testCloset;
    Clothing clothing1;
    Clothing clothing2;
    Clothing clothing3;
    Clothing clothing4;

    @BeforeEach
    public void setup() {
        testCloset = new Closet();
        clothing1 = new Clothing("H&M Shirt", "shirt", "black", 2.0);
        clothing2 = new Clothing("Levi Jeans", "pants", "blue", 32.0);
        clothing3 = new Clothing("Gucci Shirt", "shirt", "yellow", 3.0);
        clothing4 = new Clothing("off-white socks", "socks", "white", 3.0);

    }

    @Test
    public void testConstructor() {
        assertEquals(testCloset.collectionSize(), 0);

    }

    @Test
    public void testAddClothingMultipleNoException() {
        try {
            testCloset.addClothing(clothing1);
        } catch (DuplicateClothingException e) {
            fail();
        }

        assertEquals(testCloset.collectionSize(), 1);
        assertTrue(testCloset.containsClothing("H&M Shirt"));
        assertTrue(testCloset.getClothingByName("H&M Shirt").equalClothing(clothing1));

        try {
            testCloset.addClothing(clothing2);
        } catch (DuplicateClothingException e) {
            fail();
        }

        assertEquals(testCloset.collectionSize(), 2);
        assertTrue(testCloset.containsClothing("Levi Jeans"));
        assertTrue(testCloset.getClothingByName("Levi Jeans").equalClothing(clothing2));



    }

    @Test
    public void testAddClothingExpectDuplicateClothingException(){
        try {
            testCloset.addClothing(clothing1);
        } catch (DuplicateClothingException e) {
            fail();
        }

        assertEquals(testCloset.collectionSize(), 1);
        assertTrue(testCloset.containsClothing("H&M Shirt"));
        assertTrue(testCloset.getClothingByName("H&M Shirt").equalClothing(clothing1));

        try {
            testCloset.addClothing(clothing1);
             fail("Exception should've been thrown");
        } catch (DuplicateClothingException e) {
            System.out.println("Exception caught!");
        }

        assertEquals(testCloset.collectionSize(), 1);
        assertTrue(testCloset.containsClothing("H&M Shirt"));
        assertTrue(testCloset.getClothingByName("H&M Shirt").equalClothing(clothing1));

    }


    @Test
    public void testRemoveClothingMultipleNoException() {

        try {
            testCloset.addClothing(clothing1);
            testCloset.addClothing(new Clothing("Levi Jeans", "Pants", "Blue", 32.0));
        } catch (DuplicateClothingException e) {
            fail();
        }

        testCloset.removeClothing(clothing1);
        assertEquals(testCloset.collectionSize(), 1);
        assertTrue(testCloset.containsClothing("Levi Jeans"));
        assertFalse(testCloset.containsClothing("H&M Shirt"));

        testCloset.removeClothing(testCloset.getClothingByName("Levi Jeans"));
        assertEquals(testCloset.collectionSize(), 0);
        assertFalse(testCloset.containsClothing("Levi Jeans"));
        assertFalse(testCloset.containsClothing("H&M Shirt"));
    }

    @Test
    public void testGetNumberOfClothingEmpty() {
        assertEquals(testCloset.collectionSize(), 0);
    }

    @Test
    public void testGetNumberOfClothingFilledNoException() {
        try {
            testCloset.addClothing(clothing1);
        } catch (DuplicateClothingException e) {
            fail();
        }

        assertEquals(testCloset.collectionSize(), 1);

        try {
            testCloset.addClothing(clothing2);
        } catch (DuplicateClothingException e) {
            fail();
        }

        assertEquals(testCloset.collectionSize(), 2);
    }

    @Test
    public void testGetClosetByTypeNoExceptionExpected() {
        Closet shirtCloset = new Closet();
        Closet pantsCloset = new Closet();
        Closet socksCloset = new Closet();


        try {
            testCloset.addClothing(clothing1); //added shirt
            testCloset.addClothing(clothing2); //added pants
            testCloset.addClothing(clothing3); //added shirt
            testCloset.addClothing(clothing4); //added socks

            shirtCloset = testCloset.getClosetByType("shirt");
            pantsCloset = testCloset.getClosetByType("pants");
            socksCloset = testCloset.getClosetByType("socks");
        } catch (DuplicateClothingException e) {
            fail();
        }


        assertEquals(shirtCloset.collectionSize(), 2);
        assertTrue(shirtCloset.containsClothing(clothing1.getName()));
        assertTrue(shirtCloset.containsClothing(clothing3.getName()));

        assertEquals(pantsCloset.collectionSize(), 1);
        assertTrue(pantsCloset.containsClothing(clothing2.getName()));

        assertEquals(socksCloset.collectionSize(), 1);
        assertTrue(socksCloset.containsClothing(clothing4.getName()));

    }



}
