package gui.tabbedframe.closetpane;

import javax.swing.*;
import java.awt.*;

public class RemoveClothingPanel extends OptionPanelConstructor {
    public RemoveClothingPanel() {
        super();
        this.getPanel().setBackground(Color.GRAY);
        makeFormTitleLabel();
        formTitle.setText("Remove Clothing Form");

    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }
}
