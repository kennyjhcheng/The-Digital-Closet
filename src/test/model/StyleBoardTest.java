package model;

import exceptions.DuplicateClothingException;
import exceptions.InvalidOutfitException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StyleBoardTest {
    StyleBoard testStyleBoard;

    Outfit outfit1, outfit2, outfit3;

    Clothing clothing1, clothing2, clothing3;

    @BeforeEach
    public void setup() throws DuplicateClothingException {
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
        assertEquals(testStyleBoard.getStyleBoardSize(), 0 );

    }

    @Test
    public void testAddOutfitMultiple() throws InvalidOutfitException {
        testStyleBoard.addOutfit(outfit1);

        assertEquals(testStyleBoard.getOutfit("Outfit 1"), outfit1);
        assertTrue(testStyleBoard.containsOutfit("Outfit 1"));
        assertEquals(testStyleBoard.getStyleBoardSize(), 1);

        testStyleBoard.addOutfit(outfit2);

        assertEquals(testStyleBoard.getOutfit("Outfit 1"), outfit1);
        assertEquals(testStyleBoard.getOutfit("Outfit 2"), outfit2);
        assertTrue(testStyleBoard.containsOutfit("Outfit 1"));
        assertTrue(testStyleBoard.containsOutfit("Outfit 2"));
        assertEquals(testStyleBoard.getStyleBoardSize(), 2);

        testStyleBoard.addOutfit(outfit3);

        assertEquals(testStyleBoard.getOutfit("Outfit 1"), outfit1);
        assertEquals(testStyleBoard.getOutfit("Outfit 2"), outfit2);
        assertEquals(testStyleBoard.getOutfit("Outfit 3"), outfit3);
        assertTrue(testStyleBoard.containsOutfit("Outfit 1"));
        assertTrue(testStyleBoard.containsOutfit("Outfit 2"));
        assertTrue(testStyleBoard.containsOutfit("Outfit 3"));
        assertEquals(testStyleBoard.getStyleBoardSize(), 3);

    }

    @Test
    public void testRemoveOutfitMultipleNoException() {
        testStyleBoard.addOutfit(outfit1);
        testStyleBoard.addOutfit(outfit2);
        testStyleBoard.addOutfit(outfit3);

        try {
            testStyleBoard.removeOutfit(outfit1);

            assertEquals(testStyleBoard.getStyleBoardSize(), 2);
            assertEquals(testStyleBoard.getOutfit("Outfit 2"), outfit2);
            assertEquals(testStyleBoard.getOutfit("Outfit 3"), outfit3);
            assertTrue(testStyleBoard.containsOutfit("Outfit 2"));
            assertTrue(testStyleBoard.containsOutfit("Outfit 3"));

            testStyleBoard.removeOutfit(outfit3);

            assertEquals(testStyleBoard.getStyleBoardSize(), 1);
            assertEquals(testStyleBoard.getOutfit("Outfit 2"), outfit2);
            assertTrue(testStyleBoard.containsOutfit("Outfit 2"));

            testStyleBoard.removeOutfit(outfit2);
            assertEquals(testStyleBoard.getStyleBoardSize(), 0);
        } catch (InvalidOutfitException e) {
            fail();
        }




    }

    @Test
    public void testRemoveOutfitMultipleExpectInvalidOutfitException() throws InvalidOutfitException {
        testStyleBoard.addOutfit(outfit1);
        testStyleBoard.addOutfit(outfit2);

        try {
            testStyleBoard.removeOutfit(outfit3);
            fail("Exception should've been thrown");
        } catch (InvalidOutfitException e) {

        }

        assertEquals(testStyleBoard.getStyleBoardSize(), 2);
        assertEquals(testStyleBoard.getOutfit("Outfit 2"), outfit2);
        assertEquals(testStyleBoard.getOutfit("Outfit 1"), outfit1);
        assertTrue(testStyleBoard.containsOutfit("Outfit 2"));
        assertTrue(testStyleBoard.containsOutfit("Outfit 1"));
    }



    @Test
    public void testGetOutfit() throws InvalidOutfitException {
        testStyleBoard.addOutfit(outfit1);
        testStyleBoard.addOutfit(outfit2);
        testStyleBoard.addOutfit(outfit3);

        assertTrue(testStyleBoard.getOutfit("Outfit 1").equalOutfit(outfit1));
        assertTrue(testStyleBoard.getOutfit("Outfit 2").equalOutfit(outfit2));
        assertTrue(testStyleBoard.getOutfit("Outfit 3").equalOutfit(outfit3));

    }

    @Test
    public void testGetOutfitEmptyStyleBoardExpectException() {
        try {
            testStyleBoard.getOutfit("Outfit 1");
            fail("Exception expected but method ran");
        } catch (InvalidOutfitException e) {
            System.out.println("Exception thrown");
        }

    }

    @Test
    public void testGetOutfitNothingThrown() {
        testStyleBoard.addOutfit(outfit1);
        testStyleBoard.addOutfit(outfit2);
        testStyleBoard.addOutfit(outfit3);
        Outfit testOutfit = new Outfit("");

        try {
            testOutfit = testStyleBoard.getOutfit("Outfit 1");
        } catch (InvalidOutfitException e) {
            fail("Exception should not have been thrown");
        }

        assertTrue(testOutfit.equalOutfit(outfit1));
    }

    @Test
    public void testGetOutfitExpectInvalidOutfitException() {
        testStyleBoard.addOutfit(outfit1);
        testStyleBoard.addOutfit(outfit2);
        testStyleBoard.addOutfit(outfit3);
        Outfit testOutfit = new Outfit("");

        try {
            testOutfit = testStyleBoard.getOutfit(" Outfit 4");
            fail("Method should not have ran");
        } catch (InvalidOutfitException e) {
            System.out.println("Exception thrown");
        }

        assertEquals(testOutfit.getName(), "");
        assertFalse(testOutfit.isFavorite());
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
        assertEquals(testStyleBoard.getStyleBoardSize(), 0);
    }

    @Test
    public void testGetNumberOfOutfitsNotEmpty() {
        testStyleBoard.addOutfit(outfit1);

        assertEquals(testStyleBoard.getStyleBoardSize(), 1);

        testStyleBoard.addOutfit(outfit2);

        assertEquals(testStyleBoard.getStyleBoardSize(), 2);

        testStyleBoard.addOutfit(outfit3);

        assertEquals(testStyleBoard.getStyleBoardSize(), 3);
    }

    @Test
    public void testGetStyleBoard() {
        ArrayList<Outfit> outfits = testStyleBoard.getStyleBoard();
        assertEquals(outfits.size(), 0);

        testStyleBoard.addOutfit(outfit1);
        testStyleBoard.addOutfit(outfit2);
        testStyleBoard.addOutfit(outfit3);

        outfits = testStyleBoard.getStyleBoard();
        assertEquals(outfits.size(), 3);
        assertTrue(outfit1.equalOutfit(outfits.get(0)));
        assertTrue(outfit2.equalOutfit(outfits.get(1)));
        assertTrue(outfit3.equalOutfit(outfits.get(2)));
    }

}
