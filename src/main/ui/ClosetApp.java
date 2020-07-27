package ui;

import model.Closet;
import model.Clothing;

import java.util.Scanner;

public class ClosetApp {
    private Scanner input;
    private Closet myCloset = new Closet();

    public ClosetApp() {
        runClosetApp();
    }

    private void runClosetApp() {
//        boolean loggedIn = false;
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        //todo: load account

        while (keepGoing) {
            displayMenu();
            command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    private void processCommand(String command) {
        switch (command) {
            case "c":
                doCloset();
                break;
            case "s":
                // todo: finish styleboard
//                doStyleBoard();
                break;
//            case "l":
//                //
//                break;
//            case "r":
//                doRegistration();
//                break;
        }
    }

    private void displayMenu() {
        System.out.println("Welcome to your Digital Closet!!!");
        System.out.println("Tip: The closet is where you can add and remove clothing");
        System.out.println("\tand the Style Board is where you can create and remove outfits");
        System.out.println("\nSelect from:");
        System.out.println("\tc -> open closet");
        System.out.println("\ts -> open your Style Board");
        System.out.println("\tq -> quit");
    }

    private void doCloset() {
        String closetChoice;

        displayClosetOptions();

        closetChoice = input.nextLine();
        closetChoice = closetChoice.toLowerCase();

        processClosetChoice(closetChoice);

    }

    private void processClosetChoice(String closetChoice) {
        switch (closetChoice) {
            case "a":
                doAddClothing();
                break;
            case "r":
                doRemoveClothing();
                break;
            case "v":
                doViewClothing();
                break;
            case "e":
                doEditClothing();
                break;
        }
    }

    private void doEditClothing() {
        String edit;
        System.out.println("Please type the name of the clothing you would like to edit");
        System.out.println("These are the clothes currently in your closet");
        for (Clothing c : myCloset.getClothes()) {
            System.out.println("\tName: " + c.getName() + "\n\t\t Type: " + c.getType()
                    + "\n\t\t Color: " + c.getColor() + "\n\t\t Size: " + c.getSize());
        }

        System.out.print("Edit: ");
        edit = input.nextLine();
        edit = edit.toLowerCase();

        askAttribute(edit);
    }

    private void askAttribute(String edit) {
        String attribute;
        System.out.println("What attribute would you like to edit?");
        System.out.println("\t(name, type, color, size");

        attribute = input.nextLine();
        attribute = attribute.toLowerCase();

        completeEdit(edit, attribute);
    }

    private void completeEdit(String edit, String attribute) {
        switch (attribute) {
            case "name":
                editName(edit, attribute);
                break;
            case "type":
                editType(edit, attribute);
                break;
            case "color":
                editColor(edit, attribute);
                break;
            case "size":
                editSize(edit, attribute);
                break;
        }
    }

    private void editName(String edit, String attribute) {
        String change;

        System.out.println("The current name is: " + edit);
        System.out.println("What would you like to change it to?");

        System.out.print("Change: ");
        change = input.nextLine();
        change = change.toLowerCase();

        myCloset.getClothingByName(edit).changeName(change);
    }

    private void editType(String edit, String attribute) {
        String changeType;
        String changeSize;

        System.out.println("The current type of '" + edit + "' is: "
                + myCloset.getClothingByName(edit).getType());
        System.out.println("To change the type, you must also change the size");
        System.out.println("What would you like to change the type to?");
        System.out.println("\t(shirt, pants, shoes, socks, accessories)");
        System.out.print("Change Type to: ");
        changeType = input.nextLine();
        changeType = changeType.toLowerCase();

        if (changeType.equals("accessories")) {
            changeSize = "1.0";
        } else {
            System.out.println("What would you like to change the size to?");
            displaySizingForType(changeType);
            changeSize = input.nextLine();
            changeSize = changeSize.toLowerCase();
        }

        myCloset.getClothingByName(edit).changeTypeAndSize(changeType, Double.parseDouble(changeSize));
    }

    private void editColor(String edit, String attribute) {
        String change;

        System.out.println("The current color of '" + edit + "' is: "
                + myCloset.getClothingByName(edit).getColor());
        System.out.println("What would you like to change it to?");

        System.out.print("Change: ");
        change = input.nextLine();
        change = change.toLowerCase();

        myCloset.getClothingByName(edit).changeColor(change);
    }

    private void editSize(String edit, String attribute) {
        String change;
        String type;

        type = myCloset.getClothingByName(edit).getType();

        System.out.println("The current size of '" + edit + "' is: "
                + myCloset.getClothingByName(edit).getSize());
        System.out.println("What would you like to change it to?");
        displaySizingForType(type);

        System.out.print("Change: ");
        change = input.nextLine();
        change = change.toLowerCase();

        myCloset.getClothingByName(edit).changeSize(Double.parseDouble(change));
    }

    private void displayClosetOptions() {
        System.out.println("What would you like to do?");
        System.out.println("\ta -> add clothing");
        System.out.println("\tr -> remove clothing");
        System.out.println("\tv -> view clothing");
        System.out.println("\te -> edit clothing");
    }

    private void doViewClothing() {
        String view;
        displayViewClothingOption();

        System.out.print("I would like to see my...: ");
        view = input.nextLine();
        view = view.toLowerCase();

        switch (view) {
            case "shirt":
            case "pants":
            case "socks":
            case "shoes":
            case "accessories":
                viewByType(view);
                break;
            case "all":
                viewAll();
                break;
        }

    }

    private void viewByType(String type) {
        if (type.equals("shirt")) {
            System.out.println("These are all the " + type + "s in your closet:");
        } else {
            System.out.println("These are all the " + type + " in your closet:");
        }

        Closet filteredCloset = myCloset.getClosetByType(type);
        for (Clothing c : filteredCloset.getClothes()) {
            System.out.println("\t" + "Name :" + c.getName() + "\n\t\tType: " + c.getType()
                    + "\n\t\tColor: " + c.getColor() + "\n\t\tSize: " + c.getSize());
        }

    }

    private void viewAll() {
        System.out.println("These are all the clothes in your closet:");
        for (Clothing c : myCloset.getClothes()) {
            System.out.println("\t" + "Name :" + c.getName() + "\n\t\tType: " + c.getType() + "\n\t\tColor: "
                    + c.getColor() + "\n\t\tSize: " + c.getSize());
        }

    }

    private void displayViewClothingOption() {
        System.out.println("Please select which types of clothing you would like to view");
        System.out.println("\tshirt -> I would like to see my shirts");
        System.out.println("\tpants -> I would like to see my pants");
        System.out.println("\tsocks -> I would like to see my socks");
        System.out.println("\tshoes -> I would like to see my shoes");
        System.out.println("\tacc -> I would like to see my accessories");
        System.out.println("\tall -> I would like to see all my clothing");
    }

    private void doRemoveClothing() {
        String remove;

        System.out.println("Which clothing would you like to remove?");
        System.out.println("\t Type the name of the clothing you want to remove");

        for (Clothing c : myCloset.getClothes()) {
            System.out.println("\tName: " + c.getName() + "\n\t\t Type: " + c.getType());
        }
        System.out.print("Remove: ");
        remove = input.nextLine();
        remove = remove.toLowerCase();

        myCloset.removeClothing(myCloset.getClothingByName(remove));
        System.out.println(myCloset.getNumberOfClothing());
    }

    private void doAddClothing() {
        String name;

        System.out.println("Give your clothing a name!");
        System.out.print("Name: ");
        name = input.nextLine();
        name = name.toLowerCase();

        completeAddClothingDetermineType(name);

    }

    private void completeAddClothingDetermineType(String name) {
        String type;
        System.out.println("What type of clothing are you adding?");
        System.out.println("\t(Shirt, Pants, Shoes, Socks, or Accessories)");
        System.out.print("Type: ");
        type = input.nextLine();
        type = type.toLowerCase();

        switch (type) {
            case "shirt":
                completeAddClothingForShirt(name, type);
                break;
            case "pants":
                completeAddClothingForPants(name, type);
                break;
            case "shoes":
                completeAddClothingForShoes(name, type);
                break;
            case "socks":
                completeAddClothingForSocks(name, type);
                break;
            case "accessories":
                completeAddClothingForAccessories(name, type);
                break;
        }

    }

    private void completeAddClothingForShirt(String name, String type) {
        String color;
        double size;

        System.out.println("What color is your shirt?");
        System.out.println("\tIf multiple colors, choose the one that stands out or a name for the combination");
        System.out.print("Color: ");
        color = input.nextLine();
        color = color.toLowerCase();

        System.out.println("\nWhat size is your shirt?");
        // todo: exception for shirt size
        System.out.println("\t0.0 = xSmall, 1.0 = small, 2.0 = medium, 3.0 = large, 4.0 = xLarge");
        System.out.print("Size: ");
        size = Double.parseDouble(input.nextLine());

        myCloset.addClothing(new Clothing(name, type, color, size));
    }

    private void completeAddClothingForPants(String name, String type) {
        String color;
        double size;

        System.out.println("What color are your pants?");
        System.out.println("\tIf multiple colors, choose the one that stands out or a name for the combination");
        System.out.print("Color: ");
        color = input.nextLine();
        color = color.toLowerCase();

        System.out.println("What size are your pants?");
        // todo: exception for shirt size
        System.out.println("\twaist length in inches e.g. 32.0 or 27.5");
        System.out.print("Size :");
        size = Double.parseDouble(input.nextLine());

        myCloset.addClothing(new Clothing(name, type, color, size));
    }

    private void completeAddClothingForShoes(String name, String type) {
        String color;
        double size;

        System.out.println("What color are your pants?");
        System.out.println("\tIf multiple colors, choose the one that stands out or a name for the combination");
        System.out.print("Color: ");
        color = input.nextLine();
        color = color.toLowerCase();

        System.out.println("What size are your shoes?");
        // todo: exception for shirt size
        System.out.println("\tShoe size (US) e.g. 9.0 or 5.5");
        System.out.print("Size :");
        size = Double.parseDouble(input.nextLine());

        myCloset.addClothing(new Clothing(name, type, color, size));
    }

    private void completeAddClothingForSocks(String name, String type) {
        String color;
        double size;

        System.out.println("What color are your socks?");
        System.out.println("\tIf multiple colors, choose the one that stands out or a name for the combination");
        System.out.print("Color: ");
        color = input.nextLine();
        color = color.toLowerCase();

        System.out.println("What size are your socks?");
        // todo: exception for shirt size
        System.out.println("\t0.0 = xSmall, 1.0 = small, 2.0 = medium, 3.0 = large, 4.0 = xLarge");
        System.out.print("Size :");
        size = Double.parseDouble(input.nextLine());

        myCloset.addClothing(new Clothing(name, type, color, size));
    }

    private void completeAddClothingForAccessories(String name, String type) {
        String color;
        double size;

        System.out.println("What color is your accessory?");
        System.out.println("\tIf multiple colors, choose the one that stands out or a name for the combination");
        System.out.print("Color: ");
        color = input.nextLine();
        color = color.toLowerCase();

        size = 1.0;

        myCloset.addClothing(new Clothing(name, type, color, size));
    }

    public void displaySizingForType(String type) {
        switch (type) {
            case "shirt":
            case "socks":
                System.out.println("\t0.0 = xSmall, 1.0 = small, 2.0 = medium, 3.0 = large, 4.0 = xLarge");
                break;
            case "pants":
                System.out.println("\twaist length in inches e.g. 32.0 or 27.5");
                break;
            case "shoes":
                System.out.println("\tShoe size (US) e.g. 9.0 or 5.5");
                break;
        }
    }

//    private void displayLogin() {
//        System.out.println("Welcome to your Digital Closet!!!");
//        System.out.println("\nSelect from:");
//        System.out.println("\tl -> login");
//        System.out.println("\tr -> register");
//        System.out.println("\tq -> quit");
//    }
//
//    private void doRegistration() {
//        try {
//            input = new Scanner(System.in);
//            String usernameCommand = null;
//            String passwordCommand = null;
//            Register account;
//
//            System.out.println("Username and Password are not capital-sensitive");
//            System.out.println("\nEnter your desired username");
//            System.out.print("Username: ");
//            usernameCommand = input.next();
//            usernameCommand = usernameCommand.toLowerCase();
//
//            System.out.println("Enter your desired password");
//            System.out.print("Password: ");
//            passwordCommand = input.next();
//            passwordCommand = passwordCommand.toLowerCase();
//
//            account = new Register(usernameCommand, passwordCommand);
//            JsonNode accountNode = Json.toJson(account);
//            Json.writeRegistrationToFile(usernameCommand, accountNode);
//
//            System.out.println(accountNode.get("username").asText());
//            System.out.println(accountNode.get("password").asText());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void doLogin() {
//        try {
//            input = new Scanner(System.in);
//            String usernameCommand = null;
//            String passwordCommand = null;
//        }
//    }

}
