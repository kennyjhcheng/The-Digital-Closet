package gui.tabbedframe.closet;

import gui.tabbedframe.TabbedPane;

import javax.swing.*;
import java.awt.*;


public class AddClothingPanel extends OptionPanelConstructor {

    public AddClothingPanel() {
        super();
        this.panel.setBackground(Color.BLUE);
        this.panel.setVisible(false);
    }


    @Override
    public JPanel getPanel() {
        return this.panel;
    }
}
