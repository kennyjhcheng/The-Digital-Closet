package gui.tabbedframe.closet;

import javax.swing.*;
import java.awt.*;

public class EditClothingPanel extends OptionPanelConstructor {
    public EditClothingPanel() {
        super();
        this.panel.setBackground(Color.RED);
        this.panel.setVisible(false);
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }
}
