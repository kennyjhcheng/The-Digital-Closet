package model;


import exceptions.DuplicateClothingException;

// Represents a Closet with all clothing
public class Closet extends ClothingCollection {

    // EFFECTS: constructor for a closet -> hashset of Clothing
    //          Set is empty
    public Closet() {
        super();
    }

    // exception added -> REQUIRES: all clothing names in closet must be distinct
    // MODIFIES: this
    // EFFECTS: adds a Clothing to the ClothingCollection
    @Override
    public void addClothing(Clothing clothing) throws DuplicateClothingException {
        if (this.clothes.contains(clothing)) {
            throw new DuplicateClothingException();
        } else {
            this.clothes.add(clothing);
        }

    }

    // added exception -> REQUIRES: Closet is not empty
    // EFFECTS: returns a filtered Closet containing clothes of specified type
    public Closet getClosetByType(String type) throws DuplicateClothingException {
        Closet filteredCloset = new Closet();
        for (Clothing c : this.getClothes()) {
            if (c.getType().equals(type)) {
                filteredCloset.addClothing(c);
            }
        }

        return filteredCloset;
    }


}
