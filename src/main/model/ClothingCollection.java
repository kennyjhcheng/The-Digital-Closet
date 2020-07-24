package model;

import java.util.HashSet;

public abstract class ClothingCollection {
    protected HashSet<Clothing> clothes;

    public ClothingCollection() {
        this.clothes = new HashSet<Clothing>();
    }

    // MODIFIES: this
    // EFFECTS: adds a Clothing to the ClothingCollection
    protected void addClothing(Clothing clothing) {
        this.clothes.add(clothing);
    }

    // MODIFIES: this
    // EFFECTS: removes a Clothing from the ClothingCollection
    protected void removeClothing(Clothing clothing) {
        this.clothes.remove(clothing);
    }

    // EFFECTS: returns the number of Clothing objects in the closet
    protected int getNumberOfClothing() {
        return this.clothes.size();
    }

    // REQUIRES: clothingName is a valid name of a clothing in closet
    // todo: make an exception for ^ requires
    // EFFECTS: returns the Clothing given the clothing's name
    protected Clothing getClothing(String clothingName) {
        Clothing foundClothing = new Clothing("", "", "", 0);
        for (Clothing c: this.clothes) {
            if (c.getName() == clothingName) {
                foundClothing = c;
            }
        }
        return foundClothing;
    }

    // EFFECTS: returns true if name matches the name of a clothing in closet
    protected boolean containsClothing(String name) {
        boolean isFound = false;
        for (Clothing c: clothes) {
            if (c.getName() == name) {
                isFound = true;
            }
        }
        return isFound;
    }


}
