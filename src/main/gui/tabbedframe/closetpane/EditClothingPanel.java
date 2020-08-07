package gui.tabbedframe.closetpane;

import javax.swing.*;
import java.awt.*;

public class EditClothingPanel extends OptionPanelConstructor {
    public EditClothingPanel() {
        super();
        this.getPanel().setBackground(Color.RED);
        makeFormTitleLabel();
        formTitle.setText("Edit Clothing Form");

    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }
}
