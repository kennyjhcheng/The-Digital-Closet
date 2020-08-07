package gui.tabbedframe;

import javax.swing.*;

public class SaveAndQuitPane {
    JPanel exitPanel;
    JLabel exitLabel;

    public SaveAndQuitPane() {
        this.exitPanel = new JPanel();
        exitLabel = new JLabel("Save and Quit");
        exitPanel.add(exitLabel);
    }

    public JPanel getExitPanel() {
        return exitPanel;
    }

}
