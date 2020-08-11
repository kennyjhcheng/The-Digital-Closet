package gui.tabbedframe;

import javax.swing.*;

// The Pane housing all StyleBoard options
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
