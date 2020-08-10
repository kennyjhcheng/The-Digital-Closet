package gui.tabbedframe.saveandquitpane;

import gui.mainmenu.MainMenu;
import gui.tabbedframe.TabbedPane;
import persistence.Json;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class SaveAndQuitButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = MainMenu.usernameInfo.getText();
        saveUserToFile(username);

        TabbedPane.tabbedPaneFrame.dispose();

    }

    private void saveUserToFile(String username) {
        try {
            Json.writer.writeValue(new File("./data/" + username + "Closet.json"), MainMenu.myCloset);
            Json.writer.writeValue(new File("./data/" + username + "StyleBoard.json"), MainMenu.myStyleBoard);
            Json.writer.writeValue(new File("./data/" + username + "Logged.json"), true);
        } catch (IOException e) {
            System.out.println("Could not save user to file");
        }
    }
}
