package model;

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
        assertEquals(testCloset.getNumberOfClothing(), 0);

    }

    @Test
    public void testAddClothingMultiple() {
        testCloset.addClothing(clothing1);

        assertEquals(testCloset.getNumberOfClothing(), 1);
        assertTrue(testCloset.containsClothing("H&M Shirt"));
        assertTrue(testCloset.getClothingByName("H&M Shirt").equalClothing(clothing1));

        testCloset.addClothing(clothing2);

        assertEquals(testCloset.getNumberOfClothing(), 2);
        assertTrue(testCloset.containsClothing("Levi Jeans"));
        assertTrue(testCloset.getClothingByName("Levi Jeans").equalClothing(clothing2));



    }

    @Test
    public void testRemoveClothingMultiple() {
        testCloset.addClothing(clothing1);
        testCloset.addClothing(new Clothing("Levi Jeans", "Pants", "Blue", 32.0));

        testCloset.removeClothing(clothing1);
        assertEquals(testCloset.getNumberOfClothing(), 1);
        assertTrue(testCloset.containsClothing("Levi Jeans"));
        assertFalse(testCloset.containsClothing("H&M Shirt"));

        testCloset.removeClothing(testCloset.getClothingByName("Levi Jeans"));
        assertEquals(testCloset.getNumberOfClothing(), 0);
        assertFalse(testCloset.containsClothing("Levi Jeans"));
        assertFalse(testCloset.containsClothing("H&M Shirt"));
    }

    @Test
    public void testGetNumberOfClothingEmpty() {
        assertEquals(testCloset.getNumberOfClothing(), 0);
    }

    @Test
    public void testGetNumberOfClothingFilled() {
        testCloset.addClothing(clothing1);

        assertEquals(testCloset.getNumberOfClothing(), 1);

        testCloset.addClothing(clothing2);

        assertEquals(testCloset.getNumberOfClothing(), 2);
    }

    @Test
    public void testGetClosetByType() {
        Closet shirtCloset;
        Closet pantsCloset;
        Closet socksCloset;

        testCloset.addClothing(clothing1); //added shirt
        testCloset.addClothing(clothing2); //added pants
        testCloset.addClothing(clothing3); //added shirt
        testCloset.addClothing(clothing4); //added socks

        shirtCloset = testCloset.getClosetByType("shirt");
        pantsCloset = testCloset.getClosetByType("pants");
        socksCloset = testCloset.getClosetByType("socks");

        assertEquals(shirtCloset.getNumberOfClothing(), 2);
        assertTrue(shirtCloset.containsClothing(clothing1.getName()));
        assertTrue(shirtCloset.containsClothing(clothing3.getName()));

        assertEquals(pantsCloset.getNumberOfClothing(), 1);
        assertTrue(pantsCloset.containsClothing(clothing2.getName()));

        assertEquals(socksCloset.getNumberOfClothing(), 1);
        assertTrue(socksCloset.containsClothing(clothing4.getName()));



    }


}
