package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClothingTests {
    Clothing testClothing;

    @BeforeEach
    public void setup() {
        testClothing = new Clothing("H&M Shirt", "Shirt", "Black", 2.0);

    }

    @Test
    public void testConstructor() {
        assertEquals(testClothing.getName(), "H&M Shirt");
        assertEquals(testClothing.getType(), "Shirt");
        assertEquals(testClothing.getColor(), "Black");
        assertEquals(testClothing.getSize(), 2.0);
        assertFalse(testClothing.isNeedsWashing());
    }

    @Test
    public void testChangeTypeAndSize() {
        testClothing.changeTypeAndSize("Pants", 32.0);

        assertEquals(testClothing.getType(), "Pants");
        assertEquals(testClothing.getSize(), 32.0);
    }

    @Test
    public void testChangeName() {
        testClothing.changeName("Uniqlo T-Shirt");

        assertEquals(testClothing.getName(), "Uniqlo T-Shirt");

    }

    @Test
    public void testChangeColor() {
        testClothing.changeColor("Blue");

        assertEquals(testClothing.getColor(), "Blue");

    }

    @Test
    public void testChangeSize() {
        testClothing.changeSize(1.0);

        assertEquals(testClothing.getSize(), 1.0);

    }

    @Test
    public void testSetNeedWash() {
        testClothing.setNeedWash(true);

        assertTrue(testClothing.isNeedsWashing());
    }

    @Test
    public void testEqualClothing() {
        Clothing other = new Clothing("H&M Shirt", "Shirt", "Black", 2.0);

        assertTrue(testClothing.equalClothing(other));
        assertFalse(testClothing.equalClothing(new Clothing("Test", "Shirt", "Black", 3.0)));
        assertFalse(testClothing.equalClothing(new Clothing("H&M Shirt", "Pants", "Black", 3.0)));
        assertFalse(testClothing.equalClothing(new Clothing("H&M Shirt", "Shirt", "Red", 3.0)));
        assertFalse(testClothing.equalClothing(new Clothing("H&M Shirt", "Shirt", "Black", 3.0)));
    }

    @Test
    public void testToString() {
        assertEquals(testClothing.toString(), "H&M Shirt");
    }
}