package model;

import java.util.HashSet;

// An abstract class for collections of clothing
public abstract class ClothingCollection {
    protected HashSet<Clothing> clothes;

    public ClothingCollection() {
        this.clothes = new HashSet<Clothing>();
    }

    // MODIFIES: this
    // EFFECTS: adds a Clothing to the ClothingCollection
    public void addClothing(Clothing clothing) {
        this.clothes.add(clothing);
    }

    // MODIFIES: this
    // EFFECTS: removes a Clothing from the ClothingCollection
    public void removeClothing(Clothing clothing) {
        this.clothes.remove(clothing);
    }

    // EFFECTS: returns the number of Clothing objects in the closet
    public int getNumberOfClothing() {
        return this.clothes.size();
    }

    // REQUIRES: clothingName is a valid name of a clothing in closet
    // todo: make an exception for ^ requires
    // EFFECTS: returns the Clothing given the clothing's name
    public Clothing getClothingByName(String clothingName) {
        Clothing foundClothing = new Clothing("", "", "", 0);
        for (Clothing c: this.clothes) {
            if (c.getName().equals(clothingName)) {
                foundClothing = c;
            }
        }
        return foundClothing;
    }

    // EFFECTS: returns true if name matches the name of a clothing in closet
    public boolean containsClothing(String name) {
        boolean isFound = false;
        for (Clothing c: clothes) {
            if (c.getName() == name) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    public HashSet<Clothing> getClothes() {
        return clothes;
    }
}
