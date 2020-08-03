package model;


import java.util.Iterator;

// Represent an Outfit -> a collection of clothing of different types
public class Outfit extends ClothingCollection {
    private boolean favorite;
    private String name;

    // EFFECTS: creates an Outfit composed of clothes with a name for the Outfit
    public Outfit(String name) {
        super();
        this.name = name;
        this.favorite = false;
    }

    public Outfit() {

    }

    // EFFECTS: setter
    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    // EFFECTS: getter
    public boolean isFavorite() {
        return favorite;
    }

    // EFFECTS: getter
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: changes the outfits name
    public void changeName(String name) {
        this.name = name;
    }

    // EFFECTS: produces true if 2 Outfits are the same
    public boolean equalOutfit(Outfit other) {
        return this.equals(other);
    }

}
