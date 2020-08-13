package ui;

import com.fasterxml.jackson.databind.JsonNode;
import exceptions.DuplicateClothingException;

import exceptions.InvalidOutfitException;
import model.Closet;
import model.Clothing;
import model.Outfit;
import model.StyleBoard;
import persistence.Json;
import persistence.Registration;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

// Closet Application user interface
public class ClosetApp {
    private Scanner input;
    private Closet myCloset = new Closet();
    private StyleBoard myStyleBoard = new StyleBoard();

    // EFFECTS: runs Closet application
    public ClosetApp() {
        runLogin();
    }

    // Runs the login interface allowing the user to register, login, delete account, or quit the app
    private void runLogin() {
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);

        try {
            Json.userList = Json.parseUserInfo("User");
        } catch (IOException e) {
            System.out.println("Could not retrieve user data");
        }

        while (keepGoing) {
            displayLogin();
            command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else if (command.equals("r")) {
                processLoginCommand(command);
                System.out.println("Please re-run the app to load user login data");
                keepGoing = false;
            } else {
                processLoginCommand(command);
            }
        }
    }

    // prints the login menu options
    private void displayLogin() {
        System.out.println("\nWelcome to your Digital Closet!!!");
        System.out.println("\nSelect from:");
        System.out.println("\tl -> login");
        System.out.println("\tr -> register");
        System.out.println("\td -> delete an account");
        System.out.println("\tq -> quit");
    }

    // directs the user input command to the correct method for login, registration, or removing an account
    private void processLoginCommand(String command) {
        switch (command) {
            case "l":
                doLogin();
                break;
            case "r":
                doRegistration();
                break;
            case "d":
                doDeleteAccount();

        }
    }

    // allows the user to login using their credentials, if credentials do not match an account, login fails
    private void doLogin() {
        String usernameCommand;
        String passwordCommand;
        Registration account;
        boolean successfulLogin = false;

        System.out.println("Username and Password are not capital-sensitive");
        System.out.println("\nEnter the username of the account your account");
        System.out.print("Username: ");
        usernameCommand = input.nextLine();
        usernameCommand = usernameCommand.toLowerCase();

        System.out.println("\nEnter the password of your account");
        System.out.print("Password: ");
        passwordCommand = input.nextLine();
        passwordCommand = passwordCommand.toLowerCase();

        account = new Registration(usernameCommand, passwordCommand);
        JsonNode accountNode = Json.toJson(account);
        successfulLogin = Json.userListContains(accountNode);

        if (successfulLogin) {
            System.out.println("Login Successful!");
            runClosetApp(accountNode);
        } else {
            System.out.println("Login Unsuccessful");
        }


    }

    // MODIFIES: UserInfo.json
    // EFFECTS: register a username and password of user to the UserInfo json file
    private void doRegistration() {
        input = new Scanner(System.in);
        String usernameCommand;
        String passwordCommand;
        Registration account;

        try {
            System.out.println("Username and Password are not capital-sensitive");
            System.out.println("\nEnter your desired username");
            System.out.print("Username: ");
            usernameCommand = input.nextLine();
            usernameCommand = usernameCommand.toLowerCase();

            System.out.println("Enter your desired password");
            System.out.print("Password: ");
            passwordCommand = input.nextLine();
            passwordCommand = passwordCommand.toLowerCase();

            account = new Registration(usernameCommand, passwordCommand);
            JsonNode accountNode = Json.toJson(account);
            Json.writeRegistrationToFile(accountNode, "User");
        } catch (Exception e) {
            System.out.println("Could not complete registration");
        }
    }

    // MODIFIES: UserInfo.json
    // EFFECTS: removes the account specified by the user from UserInfo.json
    private void doDeleteAccount() {
        String usernameCommand;
        String passwordCommand;
        Registration account;

        System.out.println("Username and Password are not capital-sensitive");
        System.out.println("\nEnter the username of the account you wish to delete");
        System.out.print("Username: ");
        usernameCommand = input.nextLine();
        usernameCommand = usernameCommand.toLowerCase();

        System.out.println("\nEnter the password of the account you wish to delete");
        System.out.print("Password: ");
        passwordCommand = input.nextLine();
        passwordCommand = passwordCommand.toLowerCase();

        account = new Registration(usernameCommand, passwordCommand);
        JsonNode accountNode = Json.toJson(account);
        try {
            Json.removeRegistrationFromFile(accountNode, "User");
        } catch (IOException e) {
            System.out.println("Could not register user");
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: runs the app until quit is inputted by the user
    private void runClosetApp(JsonNode accountNode) {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);
        String username = accountNode.get("username").asText();

        loadUser(username);

        while (keepGoing) {
            displayMenu(username);
            command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        saveUserToFile(username);
        System.out.println("\nGoodbye!");
    }

    private void loadUser(String username) {

        try {
            File tmpDir = new File("./data/" + username + "Logged.json");
            if (tmpDir.exists() || Json.getDefaultObjectMapper().readValue(new File("./data/"
                    + username + "Logged.json"), boolean.class)) {
                this.myCloset = Json.parseUserCloset(username);
                this.myStyleBoard = Json.parseUserStyleBoard(username);
            }
        } catch (IOException e) {
            System.out.println("Could not load user");
            e.printStackTrace();
        }

    }

    private void saveUserToFile(String username) {
        try {
            Json.writer.writeValue(new File("./data/" + username + "Closet.json"), myCloset);
            Json.writer.writeValue(new File("./data/" + username + "StyleBoard.json"), myStyleBoard);
            Json.writer.writeValue(new File("./data/" + username + "Logged.json"), true);
        } catch (IOException e) {
            System.out.println("Could not save user to file");
        }
    }

    // REQUIRES: command matches the cases in the switch statement
    // EFFECTS: based on user input, determine whether to go to closet or styleboard
    private void processCommand(String command) {
        switch (command) {
            case "c":
                doCloset();
                break;
            case "s":
                doStyleBoard();
                break;
        }
    }

    // prints the main menu options
    private void displayMenu(String username) {
        System.out.println("\nWelcome to your Digital Closet " + username + "!!!");
        System.out.println("Tip: The closet is where you can add and remove clothing");
        System.out.println("\tand the Style Board is where you can create and remove outfits");
        System.out.println("\nSelect from:");
        System.out.println("\tc -> open closet");
        System.out.println("\ts -> open your Style Board");
        System.out.println("\tq -> save and signout");
    }

    // STYLE BOARD FUNCTIONS ------------------------------------------------------------------------

    // asks for user input to determine which style board function to run
    private void doStyleBoard() {
        String styleBoardChoice;
        boolean keepGoing = true;

        while (keepGoing) {
            displayStyleBoardOptions();
            styleBoardChoice = input.nextLine();
            styleBoardChoice = styleBoardChoice.toLowerCase();

            if (styleBoardChoice.equals("b")) {
                keepGoing = false;
            } else {
                try {
                    processStyleBoardChoice(styleBoardChoice);
                } catch (InvalidOutfitException e) {
                    System.out.println("\nPlease input a valid Outfit name from your StyleBoard\n");
                } catch (DuplicateClothingException e) {
                    System.out.println("\nThat Clothing is already exists\n");
                }
            }
        }


    }

    // REQUIRES: the styleBoardChoice is one of the switch statement cases
    // processes the correct style board method depending on the user input
    private void processStyleBoardChoice(String styleBoardChoice) throws InvalidOutfitException,
            DuplicateClothingException {
        switch (styleBoardChoice) {
            case "c":
                doCreateOutfit();
                break;
            case "r":
                doRemoveOutfit();
                break;
            case "e":
                doEditOutfit();
                break;
            case "v":
                doViewOutfit();
                break;
        }
    }

    // prints out all outfits names and the clothing content for user to see
    private void doViewOutfit() {
        System.out.println("These are all your outfits:");
        for (Outfit o : myStyleBoard.getStyleBoard()) {
            System.out.println("\tOutfit Name: " + o.getName());
            printAllClothingInOutfit(o);
        }
    }

    // prints all clothing in an outfit
    private void printAllClothingInOutfit(Outfit o) {
        for (Clothing c : o.getClothes()) {
            System.out.println("\t\t" + "Name :" + c.getName() + "\n\t\t\tType: " + c.getType() + "\n\t\t\tColor: "
                    + c.getColor() + "\n\t\t\tSize: " + c.getSize());
        }
    }

    // asks the user which outfit they wish to edit in the Style Board
    private void doEditOutfit() throws InvalidOutfitException, DuplicateClothingException {
        String outfitToEdit;

        System.out.println("Which outfit would you like to edit?");
        printAllOutfitNamesInStyleBoard();
        System.out.print("Edit: ");
        outfitToEdit = input.nextLine();
        outfitToEdit = outfitToEdit.toLowerCase();

        if (myStyleBoard.containsOutfit(outfitToEdit)) {
            editOutfit(outfitToEdit);
        } else {
            System.out.println("Could not find the outfit. Please double check your StyleBoard!");
        }
    }

    // REQUIRES: user input matches one of options: a, r
    // asks the user what they would like to do to the outfit chosen from Style Board
    private void editOutfit(String outfitToEdit) throws InvalidOutfitException, DuplicateClothingException {
        String editChoice;
        System.out.println("How would you like to edit your outfit '" + outfitToEdit + "'?");
        System.out.println("\ta -> add clothing");
        System.out.println("\tr -> remove clothing");
        System.out.println("\tn -> change outfit name");
        System.out.println("\tc -> cancel");

        editChoice = input.nextLine();
        editChoice = editChoice.toLowerCase();

        switch (editChoice) {
            case "a":
                doEditAddClothingToOutfit(outfitToEdit);
                break;
            case "r":
                doEditRemoveClothingFromOutfit(outfitToEdit);
                break;
            case "n":
                doEditClothingName(outfitToEdit);
                break;
            case "c":
                break;
        }
    }

    // edits the clothing name specified by the user
    private void doEditClothingName(String outfitToEdit) throws InvalidOutfitException {
        String nameChange;
        System.out.println("what would you like to change " + outfitToEdit + " to?");
        System.out.print("Name: ");
        nameChange = input.nextLine();
        nameChange = nameChange.toLowerCase();

        myStyleBoard.getOutfit(outfitToEdit).changeName(nameChange);
    }

    // takes user input to edit by adding another clothing to an outfit in style board
    private void doEditAddClothingToOutfit(String outfitToEdit) throws InvalidOutfitException,
            DuplicateClothingException {
        String addClothing;
        System.out.println("These are the clothes currently in this outfit:");
        for (Clothing c : myStyleBoard.getOutfit(outfitToEdit).getClothes()) {
            System.out.println("\t" + "Name :" + c.getName() + "\n\t\tType: " + c.getType() + "\n\t\tColor: "
                    + c.getColor() + "\n\t\tSize: " + c.getSize());
        }

        System.out.println("What clothing would you like to add to " + outfitToEdit);
        System.out.println("Please type the name of the clothing you wish to add");
        printAllClothingInCloset();
        System.out.print("Add: ");
        addClothing = input.nextLine();
        addClothing = addClothing.toLowerCase();

        myStyleBoard.getOutfit(outfitToEdit).addClothing(myCloset.getClothingByName(addClothing));
        System.out.println("A new clothing has been added to " + outfitToEdit + "!!!");
    }

    // takes user input to edit by removing another clothing from an outfit in style board
    private void doEditRemoveClothingFromOutfit(String outfitToEdit) throws InvalidOutfitException {
        String removeClothing;
        System.out.println("These are the clothes currently in this outfit:");
        for (Clothing c : myStyleBoard.getOutfit(outfitToEdit).getClothes()) {
            System.out.println("\t" + "Name :" + c.getName() + "\n\t\tType: " + c.getType() + "\n\t\tColor: "
                    + c.getColor() + "\n\t\tSize: " + c.getSize());
        }
        System.out.println("Which clothing would you like to remove from this outfit?");
        System.out.println("Please type the name of the clothing you wish to remove");
        System.out.print("Remove: ");
        removeClothing = input.nextLine();
        removeClothing = removeClothing.toLowerCase();

        myStyleBoard.getOutfit(outfitToEdit)
                .removeClothing(myStyleBoard.getOutfit(outfitToEdit).getClothingByName(removeClothing));

        System.out.println("You have remove " + removeClothing + " from " + outfitToEdit + "!!!");
    }

    // gets user input to remove an outfit from style board
    private void doRemoveOutfit() throws InvalidOutfitException {
        String removeOutfit;

        System.out.println("Which outfit would you like to remove?");
        printAllOutfitNamesInStyleBoard();
        System.out.print("Remove: ");
        removeOutfit = input.nextLine();
        removeOutfit = removeOutfit.toLowerCase();

        myStyleBoard.removeOutfit(myStyleBoard.getOutfit(removeOutfit));

        System.out.println(removeOutfit + " has been removed!");

    }

    // prints all outfit names in style board
    private void printAllOutfitNamesInStyleBoard() {
        for (Outfit o : myStyleBoard.getStyleBoard()) {
            System.out.println("\tName: " + o.getName());
        }
    }

    // takes user input to being outfit creation -> asks for Outfit Name
    private void doCreateOutfit() throws DuplicateClothingException {

        String outfitName;
        Outfit newOutfit;

        System.out.println("Give your Outfit a name");
        System.out.print("Name: ");
        outfitName = input.nextLine();
        outfitName = outfitName.toLowerCase();

        newOutfit = new Outfit(outfitName);

        addClothingToOutfit(newOutfit);
    }

    // continues outfit creation allowing user to add clothing from closet to outfit
    private void addClothingToOutfit(Outfit newOutfit) throws DuplicateClothingException {
        String clothingName;

        System.out.println("Please type the name of the clothing you want to add to this outfit");
        System.out.println("These are the clothing currently in your closet");
        printAllClothingInCloset();
        System.out.print("Add: ");
        clothingName = input.nextLine();
        clothingName = clothingName.toLowerCase();
        newOutfit.addClothing(myCloset.getClothingByName(clothingName));

        askAddAnotherClothing(newOutfit);
    }

    // prints all clothing in closet with all the clothing's fields
    private void printAllClothingInCloset() {
        for (Clothing c : myCloset.getClothes()) {
            System.out.println("\t" + "Name :" + c.getName() + "\n\t\tType: " + c.getType() + "\n\t\tColor: "
                    + c.getColor() + "\n\t\tSize: " + c.getSize());
        }
    }

    // after adding 1 item to an outfit, loop to ask user if they wish to add subsequent clothings to the outfit
    private void askAddAnotherClothing(Outfit newOutfit) throws DuplicateClothingException {
        String addAnother;
        boolean answer;
        System.out.println("Would you like to add another piece of clothing to " + newOutfit.getName());
        System.out.println("\tyes or no");
        System.out.print("Answer: ");
        addAnother = input.nextLine();
        addAnother = addAnother.toLowerCase();
        answer = addAnotherToBoolean(addAnother);

        while (answer) {
            addAnotherClothingToOutfit(newOutfit);
            System.out.println("Would you like to add another piece of clothing to " + newOutfit.getName());
            System.out.println("\tyes or no");
            System.out.print("Answer: ");
            addAnother = input.nextLine();
            addAnother = addAnother.toLowerCase();
            answer = addAnotherToBoolean(addAnother);
        }

        myStyleBoard.addOutfit(newOutfit);

        System.out.println("Your new outfit has been created!\n");
    }

    // adds subsequent clothing to new created outfit
    private void addAnotherClothingToOutfit(Outfit newOutfit) throws DuplicateClothingException {
        String clothingName;

        System.out.println("Please type the name of the clothing you want to add to this outfit");
        System.out.println("These are the clothing currently in your closet");
        printAllClothingInCloset();
        System.out.print("Add: ");

        clothingName = input.nextLine();
        clothingName = clothingName.toLowerCase();
        newOutfit.addClothing(myCloset.getClothingByName(clothingName));
    }

    // determine if the user input semantically means that they wish to add another clothing to an outfit
    private boolean addAnotherToBoolean(String input) {
        return input.equals("yes");
    }

    // prints the options for user to do in style board
    private void displayStyleBoardOptions() {
        System.out.println("Welcome to your StyleBoard!");
        System.out.println("What would you like to do?");
        System.out.println("\tc -> create an outfit");
        System.out.println("\tr -> remove an outfit");
        System.out.println("\te -> edit an outfit");
        System.out.println("\tv -> view your StyleBoard");
        System.out.println("\tb -> go back to the main menu");
    }

    // CLOSET FUNCTIONS ------------------------------------------------------------------------

    // receives user input to determine what clothing method to run
    private void doCloset() {
        String closetChoice;
        boolean keepGoing = true;

        while (keepGoing) {
            displayClosetOptions();
            closetChoice = input.nextLine();
            closetChoice = closetChoice.toLowerCase();

            if (closetChoice.equals("b")) {
                keepGoing = false;
            } else {
                try {
                    processClosetChoice(closetChoice);
                } catch (DuplicateClothingException e) {
                    System.out.println("This clothing is already in your closet!");
                }
            }
        }
    }

    // prints the options for user input to call closet methods
    private void displayClosetOptions() {
        System.out.println("What would you like to do?");
        System.out.println("\ta -> add clothing");
        System.out.println("\tr -> remove clothing");
        System.out.println("\tv -> view clothing");
        System.out.println("\te -> edit clothing");
        System.out.println("\tb -> go back to main menu");
    }

    // REQUIRES: closetChoice matches on of switch cases
    // runs the clothing method based on user choice
    private void processClosetChoice(String closetChoice) throws DuplicateClothingException {
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

    // gets user input to choose a Clothing they'd like to edit
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

    // REQUIRES: the field matches one of the valid fields: name, type, color, or size
    // gets user input for which field of the clothing they wish to edit
    private void askAttribute(String edit) {
        String attribute;
        System.out.println("What attribute would you like to edit?");
        System.out.println("\t(name, type, color, size");
        System.out.print("Edit: ");

        attribute = input.nextLine();
        attribute = attribute.toLowerCase();

        completeEdit(edit, attribute);
    }

    // REQUIRES: the field matches one of the valid fields: name, type, color, or size
    // determines which attribute edit method to call based on attribute
    private void completeEdit(String edit, String attribute) {
        switch (attribute) {
            case "name":
                editName(edit);
                break;
            case "type":
                editType(edit);
                break;
            case "color":
                editColor(edit);
                break;
            case "size":
                editSize(edit);
                break;
        }
    }

    // REQUIRES: name of clothing is valid clothing in Closet
    // edits the name of the clothing specified by user
    private void editName(String edit) {
        String change;

        System.out.println("The current name is: " + edit);
        System.out.println("What would you like to change it to?");

        System.out.print("Change: ");
        change = input.nextLine();
        change = change.toLowerCase();

        myCloset.getClothingByName(edit).changeName(change);
    }

    // REQUIRES: name of clothing is valid clothing in Closet
    // edits the Type and Size of the clothing specified by user
    private void editType(String edit) {
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
            System.out.print("Size: ");
            displaySizingForType(changeType);
            changeSize = input.nextLine();
            changeSize = changeSize.toLowerCase();
        }

        myCloset.getClothingByName(edit).changeTypeAndSize(changeType, Double.parseDouble(changeSize));
    }

    // REQUIRES: name of clothing is valid clothing in Closet
    // edits the color of the clothing specified by user
    private void editColor(String edit) {
        String change;

        System.out.println("The current color of '" + edit + "' is: "
                + myCloset.getClothingByName(edit).getColor());
        System.out.println("What would you like to change it to?");

        System.out.print("Change: ");
        change = input.nextLine();
        change = change.toLowerCase();

        myCloset.getClothingByName(edit).changeColor(change);
    }

    // REQUIRES: name of clothing is valid clothing in Closet
    // edits the size of the clothing specified by user
    private void editSize(String edit) {
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

    // REQUIRES: user input is one of cases in switch statement
    // Gets user input for how to view the clothing in their closet: all or by type
    private void doViewClothing() throws DuplicateClothingException {
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

    // prints options for user input for how to view clothing
    private void displayViewClothingOption() {
        System.out.println("Please select which types of clothing you would like to view");
        System.out.println("\tshirt -> I would like to see my shirts");
        System.out.println("\tpants -> I would like to see my pants");
        System.out.println("\tsocks -> I would like to see my socks");
        System.out.println("\tshoes -> I would like to see my shoes");
        System.out.println("\taccessories -> I would like to see my accessories");
        System.out.println("\tall -> I would like to see all my clothing");
    }

    // prints all the clothes of specified type in the closet including each clothing's fields
    private void viewByType(String type) throws DuplicateClothingException {
        if (type.equals("shirt")) {
            System.out.println("These are all the " + type + "s in your closet:");
        } else {
            System.out.println("These are all the " + type + " in your closet:");
        }

        Closet filteredCloset = myCloset.getClosetByType(type);
        if (filteredCloset.collectionSize() == 0) {
            System.out.println("this closet is empty!");
        } else {
            for (Clothing c : filteredCloset.getClothes()) {
                System.out.println("\t" + "Name :" + c.getName() + "\n\t\tType: " + c.getType()
                        + "\n\t\tColor: " + c.getColor() + "\n\t\tSize: " + c.getSize());
            }
        }
    }

    // prints all clothing in the closet with their fields
    private void viewAll() {
        System.out.println("These are all the clothes in your closet:");
        printAllClothingInCloset();
    }

    // takes user input to remove a clothing in closet
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
        System.out.println("You have removed " + remove + "  from your Closet.");
    }

    // Begins the form to add a clothing to the closet -> asks clothing name
    private void doAddClothing() throws DuplicateClothingException {
        String name;

        System.out.println("Give your clothing a name!");
        System.out.print("Name: ");
        name = input.nextLine();
        name = name.toLowerCase();

        completeAddClothingDetermineType(name);

    }

    // REQUIRES: type is one of: shirt, pants, shoes, socks, or accessories
    // continues adding new clothing form -> asks type and calls the correct method depending on type
    //      to complete adding the clothing
    private void completeAddClothingDetermineType(String name) throws DuplicateClothingException {
        String type;
        System.out.println("What type of clothing are you adding?");
        System.out.println("\t(shirt, pants, shoes, socks, or accessories)");
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

    // REQUIRES: size is one of the options for shirt size:
    //                0.0 = xSmall, 1.0 = small, 2.0 = medium, 3.0 = large, 4.0 = xLarge
    // completes the add clothing form by asking user for color and shirt size
    private void completeAddClothingForShirt(String name, String type) throws DuplicateClothingException {
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
        System.out.println("\nYour clothing has been added!\n");
    }

    // REQUIRES: size is a valid pants size
    //                waist length in inches e.g. 32.0 or 27.5
    // completes the add clothing form by asking user for color and pant size
    private void completeAddClothingForPants(String name, String type) throws DuplicateClothingException {
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
        System.out.println("\nYour clothing has been added!\n");
    }

    // REQUIRES: size is a valid shoe size
    //                Shoe size (US) e.g. 9.0 or 5.5
    // completes the add clothing form by asking user for color and shoe size
    private void completeAddClothingForShoes(String name, String type) throws DuplicateClothingException {
        String color;
        double size;

        System.out.println("What color are your shoes?");
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
        System.out.println("\nYour clothing has been added!\n");
    }

    // REQUIRES: size is a valid sock size
    //                0.0 = xSmall, 1.0 = small, 2.0 = medium, 3.0 = large, 4.0 = xLarge
    // completes the add clothing form by asking user for color and sock size
    private void completeAddClothingForSocks(String name, String type) throws DuplicateClothingException {
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
        System.out.println("\nYour clothing has been added!\n");
    }

    // completes the add clothing form by asking user for color of accessory
    private void completeAddClothingForAccessories(String name, String type) throws DuplicateClothingException {
        String color;
        double size;

        System.out.println("What color is your accessory?");
        System.out.println("\tIf multiple colors, choose the one that stands out or a name for the combination");
        System.out.print("Color: ");
        color = input.nextLine();
        color = color.toLowerCase();

        size = 1.0;

        myCloset.addClothing(new Clothing(name, type, color, size));
        System.out.println("\nYour clothing has been added!\n");
    }

    // REQUIRES input string matches one of switch cases
    // prints out the correct sizing specification depending on the type inputted
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


}
