package model;


import java.util.Objects;

//Represents a piece of clothing with a name, a type, a color, and size
public class Clothing {
    private String name;
    private String type;
    private String color;
    private double size;
    private boolean needsWashing;


    // REQUIRES: name is not empty string, type is a valid type, color is a valid color, and size corresponds to type
    //               valid types: Shirt, Pants, Shoes, Socks, Accessories
    //               valid size for...
    //                   Shirt: 0.0 = xSmall, 1.0 = small, 2.0 = medium, 3.0 = large, 4.0 = xLarge
    //                   Pants: waist length in inches
    //                   Shoes: Shoe size (US)
    //                   Socks: 0.0 = xSmall, 1.0 = small, 2.0 = medium, 3.0 = large, 4.0 = xLarge
    //                   Accessories: default 1.0 (no sizing)
    // EFFECTS: constructs a piece of clothing with a name, a type, a color, and size corresponding to the type
    public Clothing(String name, String type, String color, double size) {
        this.name = name;
        this.type = type;
        this.color = color;
        this.size = size;
        this.needsWashing = false;
    }

    public Clothing() {

    }

    // REQUIRES: valid size input corresponding to the type
    // MODIFIES: this
    // EFFECTS: changes the type of the clothing item and also the size
    public void changeTypeAndSize(String type, double size) {
        this.type = type;
        this.size = size;
    }

    // REQUIRES: name input is not an empty String
    // MODIFIES: this
    // EFFECTS: changes the name of the clothing item
    public void changeName(String name) {
        this.name = name;
    }

    // REQUIRES: valid color input
    // MODIFIES: this
    // EFFECTS: change the color of the clothing item
    public void changeColor(String color) {
        this.color = color;
    }

    // REQUIRES: valid size input corresponding to the type
    // MODIFIES: this
    // EFFECTS: changes the size of the clothing
    public void changeSize(double size) {
        this.size = size;
    }

    // MODIFIES: this
    // EFFECTS: indicates that the piece of clothing requires washing
    //          false = does not need washing (default)
    //          true = needs washing
    public void setNeedWash(boolean needsWashing) {
        this.needsWashing = needsWashing;
    }

    // EFFECTS: getter
    public String getColor() {
        return color;
    }

    // EFFECTS: getter
    public double getSize() {
        return size;
    }

    // EFFECTS: getter
    public String getName() {
        return name;
    }

    // EFFECTS: getter
    public String getType() {
        return type;
    }

    // EFFECTS: getter
    public boolean isNeedsWashing() {
        return needsWashing;
    }

    // EFFECTS: produces true if two piece of clothing have the same fields except needsWashing
    public boolean equalClothing(Clothing other) {
        return this.name.equals(other.name)
                && this.type.equals(other.type)
                && this.color.equals(other.color)
                && this.size == other.size;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Clothing clothing = (Clothing) o;
        return Objects.equals(name, clothing.name);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
