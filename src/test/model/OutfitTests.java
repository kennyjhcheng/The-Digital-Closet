package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OutfitTests {
    Outfit outfit1, outfit2;
    Clothing clothing1, clothing2, clothing3;


    @BeforeEach
    public void setup() {
        outfit1 = new Outfit("Test Outfit");
        outfit2 = new Outfit("Outfit 2");

        clothing1 = new Clothing("H&M Shirt", "Shirt", "Black", 2.0);
        clothing2 = new Clothing("Levi Jeans", "Pants", "Blue", 32.0);
        clothing3 = new Clothing(" Gucci Sunglasses", "Accessories", "Black", 1.0);

    }

    @Test
    public void testConstructor() {
        assertEquals(outfit1.getNumberOfClothing(), 0);
        assertFalse(outfit1.isFavorite());
    }

    @Test
    public void testSetFavorite(){
        assertFalse(outfit1.isFavorite());

        outfit1.setFavorite(true);

        assertTrue(outfit1.isFavorite());

    }

    @Test
    public void testChangeName(){
         assertEquals(outfit1.getName(), "Test Outfit");

         outfit1.changeName("Cool Outfit");

         assertEquals(outfit1.getName(), "Cool Outfit");
    }

    @Test
    public void testEqualOutfitBothEmpty() {
        assertFalse(outfit1.equalOutfit(outfit2));
    }

    @Test
    public void testEqualOutfitMismatch() {
        outfit1.addClothing(clothing1);
        outfit1.addClothing(clothing2);
        outfit2.addClothing(clothing2);
        outfit2.addClothing(clothing3);

        assertFalse(outfit1.equalOutfit(outfit2));
    }

}
