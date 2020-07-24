package model;

import java.util.ArrayList;

// Represents a collection of Outfits
public class StyleBoard {
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

    // REQUIRES: Outfit parameter is in styleBoard
    // MODIFIES: this
    // EFFECTS: removes Outfit from styleBoard
    public void removeOutfit(Outfit outfit) {
        this.styleBoard.remove(outfit);
    }

    // REQUIRES: outfit name is a valid name of an outfit in styleBoard
    // EFFECTS: returns the outfit with the inputted name
    public Outfit getOutfit(String outfitName) {
        Outfit theOutfit = new Outfit("");
        for (Outfit o: styleBoard) {
            if (o.getName().equals(outfitName)) {
                theOutfit = o;
                break;
            }
        }

        return theOutfit; //stub
    }

    // EFFECTS: produced true if outfitName matches the name of an Outfit in styleBoard
    public boolean containsOutfit(String outfitName) {
        boolean answer = false;
        for (Outfit o: styleBoard) {
            if (o.getName().equals(outfitName)) {
                answer = true;
                break;
            }
        }
        return answer; //stub
    }


    // EFFECTS: returns the number of outfits in styleBoard
    public int getNumberOfOutfits() {
        return styleBoard.size(); //stub
    }
}
