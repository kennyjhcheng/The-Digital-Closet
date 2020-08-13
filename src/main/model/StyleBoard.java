package model;

import exceptions.InvalidOutfitException;

import java.util.ArrayList;
import java.util.Iterator;

// Represents a collection of Outfits
public class StyleBoard implements Iterable<Outfit> {
    ArrayList<Outfit> styleBoard;

    // REQUIRES: Outfit names are distinct in styleBoard
    // EFFECTS: constructor for StyleBoard -> an Array of Outfits
    public StyleBoard() {
        this.styleBoard = new ArrayList<Outfit>();
    }

    // MODIFIES: this
    // EFFECTS: adds Outfit to styleBoard
    public void addOutfit(Outfit outfit) {
        this.styleBoard.add(outfit);
    }

    // Exception added -> REQUIRES: Outfit parameter is in styleBoard
    // MODIFIES: this
    // EFFECTS: removes Outfit from styleBoard
    public void removeOutfit(Outfit outfit) throws InvalidOutfitException {
        if (this.styleBoard.contains(outfit)) {
            this.styleBoard.remove(outfit);
        } else {
            throw new InvalidOutfitException();
        }

    }

    // Exception added -> REQUIRES: outfit name is a valid name of an outfit in styleBoard
    // EFFECTS: returns the outfit with the inputted name
    public Outfit getOutfit(String outfitName) throws InvalidOutfitException {
        Outfit theOutfit = new Outfit("");
        if (this.containsOutfit(outfitName)) {
            for (Outfit o : this) {
                if (o.getName().equals(outfitName)) {
                    theOutfit = o;
                }
            }
        } else {
            throw new InvalidOutfitException();
        }


        return theOutfit; //stub
    }

    // EFFECTS: produced true if outfitName matches the name of an Outfit in styleBoard
    public boolean containsOutfit(String outfitName) {
        boolean answer = false;
        for (Outfit o : this) {
            if (o.getName().equals(outfitName)) {
                answer = true;
                break;
            }
        }
        return answer; //stub
    }


    // EFFECTS: returns the number of outfits in styleBoard
    public int styleBoardSize() {
        return styleBoard.size();
    }

    public ArrayList<Outfit> getStyleBoard() {
        return styleBoard;
    }

    @Override
    public Iterator<Outfit> iterator() {
        return styleBoard.iterator();
    }
}
