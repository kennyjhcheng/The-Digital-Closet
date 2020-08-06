package gui.tabbedframe.closet;

import javax.swing.*;
import java.awt.*;

public class RemoveClothingPanel extends OptionPanelConstructor {
    public RemoveClothingPanel() {
        super();
        this.panel.setBackground(Color.GRAY);
        this.panel.setVisible(false);
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }
}
