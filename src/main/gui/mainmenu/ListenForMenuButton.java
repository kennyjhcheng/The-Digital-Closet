package gui.mainmenu;

import com.fasterxml.jackson.databind.JsonNode;
import persistence.Json;
import persistence.Registration;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class ListenForMenuButton implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (MainMenu.loginButton.equals(source)) {
            doLoginProcess();
        } else if (MainMenu.registerButton.equals(source)) {
            doRegisterProcess();
        } else if (MainMenu.deleteButton.equals(source)) {
            doDeleteProcess();
        } else if (MainMenu.quitButton.equals(source)) {
            doQuitProcess();
        }
    }

    private void doLoginProcess() {
        String username = MainMenu.theMenu.getUsernameInfo().getText();
        String password = MainMenu.theMenu.getPasswordInfo().getText();
        username = username.toLowerCase();
        password = password.toLowerCase();
        boolean successfulLogin = false;

        JsonNode accountNode = Json.toJson(new Registration(username, password));
        successfulLogin = Json.userListContains(accountNode);
        if (successfulLogin) {
            JOptionPane.showMessageDialog(MainMenu.menuFrame, "Login Successful!\n");
            // todo: open new jframe when login success
        } else {
            JOptionPane.showMessageDialog(MainMenu.menuFrame, "Login Unsuccessful!\n");
        }
    }

    private void doRegisterProcess() {
        String username = MainMenu.theMenu.getUsernameInfo().getText();
        String password = MainMenu.theMenu.getPasswordInfo().getText();
        username = username.toLowerCase();
        password = password.toLowerCase();

        Registration account = new Registration(username, password);
        JsonNode accountNode = Json.toJson(account);

        boolean successfulRegistration;
        try {
            successfulRegistration = Json.writeRegistrationToFile(accountNode, "User");
            if (successfulRegistration) {
                JOptionPane.showMessageDialog(MainMenu.menuFrame, "Registration Success!\n username: "
                        + username + "\npassword: " + password + "\n has been successfully registered!");
            } else {
                JOptionPane.showMessageDialog(MainMenu.menuFrame,"User already registered\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(MainMenu.menuFrame,"Registration Unsuccessful!\n");
        }

    }

    private void doDeleteProcess() {
        String username = MainMenu.theMenu.getUsernameInfo().getText();
        String password = MainMenu.theMenu.getPasswordInfo().getText();
        username = username.toLowerCase();
        password = password.toLowerCase();
        boolean foundUser = false;

        Registration account = new Registration(username, password);
        JsonNode accountNode = Json.toJson(account);

        try {
            foundUser = Json.removeRegistrationFromFile(accountNode, "User");
            if (foundUser) {
                JOptionPane.showMessageDialog(MainMenu.menuFrame, "username: "
                        + username + "\npassword: " + password + "\n has been successfully removed!");
            } else {
                JOptionPane.showMessageDialog(MainMenu.menuFrame, "Could not remove user "
                        + "\n Error 404: User was not found");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(MainMenu.menuFrame, "User removal unsuccessful");
            e.printStackTrace();
        }

    }

    private void doQuitProcess() {
        MainMenu.menuFrame.dispatchEvent(new WindowEvent(MainMenu.menuFrame, WindowEvent.WINDOW_CLOSING));
    }


}
