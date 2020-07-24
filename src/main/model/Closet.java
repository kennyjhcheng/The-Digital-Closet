package model;

import java.util.HashSet;


// Represents a Closet with all clothing
public class Closet extends ClothingCollection {


    // EFFECTS: constructor for a closet -> hashset of Clothing
    //          Set is empty
    public Closet() {
        super();
    }

    // REQUIRES: all clothing names in closet must be distinct
    // todo: ^ add exception for this to print out message for user if they add two clothing with same name
    // MODIFIES: this
    // EFFECTS: adds a Clothing to the ClothingCollection
    @Override
    protected void addClothing(Clothing clothing) {
        this.clothes.add(clothing);
    }


//    public int numberSameClothing();


}
