package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClosetTests {

    Closet testCloset;
    Clothing clothing1;
    Clothing clothing2;

    @BeforeEach
    public void setup() {
        testCloset = new Closet();
        clothing1 = new Clothing("H&M Shirt", "Shirt", "Black", 2.0);
        clothing2 = new Clothing("Levi Jeans", "Pants", "Blue", 32.0);
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
        assertTrue(testCloset.getClothing("H&M Shirt").equalClothing(clothing1));

        testCloset.addClothing(clothing2);

        assertEquals(testCloset.getNumberOfClothing(), 2);
        assertTrue(testCloset.containsClothing("Levi Jeans"));
        assertTrue(testCloset.getClothing("Levi Jeans").equalClothing(clothing2));



    }

    @Test
    public void testRemoveClothingMultiple() {
        testCloset.addClothing(clothing1);
        testCloset.addClothing(new Clothing("Levi Jeans", "Pants", "Blue", 32.0));

        testCloset.removeClothing(clothing1);
        assertEquals(testCloset.getNumberOfClothing(), 1);
        assertTrue(testCloset.containsClothing("Levi Jeans"));
        assertFalse(testCloset.containsClothing("H&M Shirt"));

        testCloset.removeClothing(testCloset.getClothing("Levi Jeans"));
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


}
