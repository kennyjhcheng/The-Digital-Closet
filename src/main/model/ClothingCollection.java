package model;

import exceptions.DuplicateClothingException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// An abstract class for collections of clothing
public abstract class ClothingCollection {
    protected List<Clothing> clothes;

    public ClothingCollection() {
        this.clothes = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a Clothing to the ClothingCollection
    public void addClothing(Clothing clothing) throws DuplicateClothingException {
        this.clothes.add(clothing);
    }

    // MODIFIES: this
    // EFFECTS: removes a Clothing from the ClothingCollection
    public void removeClothing(Clothing clothing) {
        this.clothes.remove(clothing);
    }

    // EFFECTS: returns the number of Clothing objects in the closet
    public int getCollectionSize() {
        return this.clothes.size();
    }

    // REQUIRES: clothingName is a valid name of a clothing in closet
    // todo: make an exception for ^ requires
    // EFFECTS: returns the Clothing given the clothing's name
    public Clothing getClothingByName(String clothingName) {
        Clothing foundClothing = new Clothing("", "", "", 0);
        for (Clothing c : this.clothes) {
            if (c.getName().equals(clothingName)) {
                foundClothing = c;
            }
        }
        return foundClothing;
    }

    // EFFECTS: returns true if name matches the name of a clothing in closet
    public boolean containsClothing(String name) {
        boolean isFound = false;
        for (Clothing c : clothes) {
            if (c.getName().equals(name)) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    public List<Clothing> getClothes() {
        return clothes;
    }
}
