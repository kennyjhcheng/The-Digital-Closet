package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StyleBoardTest {
    StyleBoard testStyleBoard;

    Outfit outfit1, outfit2, outfit3;

    Clothing clothing1, clothing2, clothing3;

    @BeforeEach
    public void setup() {
        testStyleBoard = new StyleBoard();

        outfit1 = new Outfit("Outfit 1");
        outfit2 = new Outfit("Outfit 2");
        outfit3 = new Outfit("Outfit 3");

        clothing1 = new Clothing("H&M Shirt", "Shirt", "Black", 2.0);
        clothing2 = new Clothing("Levi Jeans", "Pants", "Blue", 32.0);
        clothing3 = new Clothing(" Gucci Sunglasses", "Accessories", "Black", 1.0);

        outfit1.addClothing(clothing1);
        outfit1.addClothing(clothing2);

        outfit2.addClothing(clothing2);

        outfit3.addClothing(clothing1);
        outfit3.addClothing(clothing2);
        outfit3.addClothing(clothing3);
    }

    @Test
    public void testConstructor() {
        assertEquals(testStyleBoard.getNumberOfOutfits(), 0 );

    }

    @Test
    public void testAddOutfitMultiple() {
        testStyleBoard.addOutfit(outfit1);

        assertEquals(testStyleBoard.getOutfit("Outfit 1"), outfit1);
        assertTrue(testStyleBoard.containsOutfit("Outfit 1"));
        assertEquals(testStyleBoard.getNumberOfOutfits(), 1);

        testStyleBoard.addOutfit(outfit2);

        assertEquals(testStyleBoard.getOutfit("Outfit 1"), outfit1);
        assertEquals(testStyleBoard.getOutfit("Outfit 2"), outfit2);
        assertTrue(testStyleBoard.containsOutfit("Outfit 1"));
        assertTrue(testStyleBoard.containsOutfit("Outfit 2"));
        assertEquals(testStyleBoard.getNumberOfOutfits(), 2);

        testStyleBoard.addOutfit(outfit3);

        assertEquals(testStyleBoard.getOutfit("Outfit 1"), outfit1);
        assertEquals(testStyleBoard.getOutfit("Outfit 2"), outfit2);
        assertEquals(testStyleBoard.getOutfit("Outfit 3"), outfit3);
        assertTrue(testStyleBoard.containsOutfit("Outfit 1"));
        assertTrue(testStyleBoard.containsOutfit("Outfit 2"));
        assertTrue(testStyleBoard.containsOutfit("Outfit 3"));
        assertEquals(testStyleBoard.getNumberOfOutfits(), 3);

    }

    @Test
    public void testRemoveOutfitMultiple() {
        testStyleBoard.addOutfit(outfit1);
        testStyleBoard.addOutfit(outfit2);
        testStyleBoard.addOutfit(outfit3);

        testStyleBoard.removeOutfit(outfit1);

        assertEquals(testStyleBoard.getNumberOfOutfits(), 2);
        assertEquals(testStyleBoard.getOutfit("Outfit 2"), outfit2);
        assertEquals(testStyleBoard.getOutfit("Outfit 3"), outfit3);
        assertTrue(testStyleBoard.containsOutfit("Outfit 2"));
        assertTrue(testStyleBoard.containsOutfit("Outfit 3"));

        testStyleBoard.removeOutfit(outfit3);

        assertEquals(testStyleBoard.getNumberOfOutfits(), 1);
        assertEquals(testStyleBoard.getOutfit("Outfit 2"), outfit2);
        assertTrue(testStyleBoard.containsOutfit("Outfit 2"));

        testStyleBoard.removeOutfit(outfit2);
        assertEquals(testStyleBoard.getNumberOfOutfits(), 0);


    }

    @Test
    public void testGetOutfit() {
        testStyleBoard.addOutfit(outfit1);
        testStyleBoard.addOutfit(outfit2);
        testStyleBoard.addOutfit(outfit3);

        assertTrue(testStyleBoard.getOutfit("Outfit 1").equalOutfit(outfit1));
        assertTrue(testStyleBoard.getOutfit("Outfit 2").equalOutfit(outfit2));
        assertTrue(testStyleBoard.getOutfit("Outfit 3").equalOutfit(outfit3));

    }

    @Test
    public void testContainsOutfitEmptyStyleBoard() {
        assertFalse(testStyleBoard.containsOutfit("Outfit 1"));
    }

    @Test
    public void testContainsOutfitDoesntContain() {
        testStyleBoard.addOutfit(outfit1);
        testStyleBoard.addOutfit(outfit2);


        assertFalse(testStyleBoard.containsOutfit("Outfit 3"));

    }

    @Test
    public void testContainsOutfitContains() {
        testStyleBoard.addOutfit(outfit1);
        testStyleBoard.addOutfit(outfit2);
        testStyleBoard.addOutfit(outfit3);

        assertTrue(testStyleBoard.containsOutfit("Outfit 1"));
        assertTrue(testStyleBoard.containsOutfit("Outfit 2"));
        assertTrue(testStyleBoard.containsOutfit("Outfit 3"));
    }

    @Test
    public void testGetNumberOfOutfitsEmpty() {
        assertEquals(testStyleBoard.getNumberOfOutfits(), 0);
    }

    @Test
    public void testGetNumberOfOutfitsNotEmpty() {
        testStyleBoard.addOutfit(outfit1);

        assertEquals(testStyleBoard.getNumberOfOutfits(), 1);

        testStyleBoard.addOutfit(outfit2);

        assertEquals(testStyleBoard.getNumberOfOutfits(), 2);

        testStyleBoard.addOutfit(outfit3);

        assertEquals(testStyleBoard.getNumberOfOutfits(), 3);
    }

}
