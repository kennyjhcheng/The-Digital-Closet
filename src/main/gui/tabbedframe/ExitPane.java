package gui.tabbedframe;

import javax.swing.*;

public class ExitPane {
    JPanel exitPanel;
    JLabel exitLabel;

    public ExitPane() {
        this.exitPanel = new JPanel();
        exitLabel = new JLabel("Save and Quit");
        exitPanel.add(exitLabel);
    }

    public JPanel getExitPanel() {
        return exitPanel;
    }

}
