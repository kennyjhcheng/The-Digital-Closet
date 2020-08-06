package gui.tabbedframe;

import javax.swing.*;

public class StyleBoardPane {
    JPanel styleBoardPanel;
    JLabel styleBoardPaneLabel;

    public StyleBoardPane() {
        styleBoardPanel = new JPanel();
        styleBoardPaneLabel = new JLabel("StyleBoard");
        styleBoardPanel.add(styleBoardPaneLabel);
    }

    public JPanel getStyleBoardPanel() {
        return styleBoardPanel;
    }
}
