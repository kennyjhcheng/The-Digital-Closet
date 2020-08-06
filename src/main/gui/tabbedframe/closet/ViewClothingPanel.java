package gui.tabbedframe.closet;

import javax.swing.*;
import java.awt.*;

public class ViewClothingPanel extends OptionPanelConstructor {
    public ViewClothingPanel() {
        super();
        this.panel.setBackground(Color.DARK_GRAY);
        this.panel.setVisible(false);
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }
}
