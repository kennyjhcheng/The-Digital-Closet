package gui.tabbedframe.saveandquitpane;

import javax.swing.*;
import java.awt.*;

public class SaveAndQuitPane {
    JPanel exitPanel;
    JLabel exitLabel;
    JButton saveAndQuitButton;

    SaveAndQuitButtonListener saveAndQuitButtonListener = new SaveAndQuitButtonListener();

    public SaveAndQuitPane() {
        this.exitPanel = new JPanel();
        exitPanel.setLayout(null);
        exitLabel = new JLabel("Save and Quit to main menu?");
        exitLabel.setBounds(350,200,600,50);
        exitLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        this.exitPanel.add(exitLabel);

        saveAndQuitButton = new JButton("Save and Quit");
        saveAndQuitButton.setBounds(380, 250, 200, 50);
        saveAndQuitButton.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
        saveAndQuitButton.addActionListener(saveAndQuitButtonListener);
        this.exitPanel.add(saveAndQuitButton);
    }

    public JPanel getExitPanel() {
        return exitPanel;
    }

}
