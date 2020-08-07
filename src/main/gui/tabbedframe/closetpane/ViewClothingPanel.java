package gui.tabbedframe.closetpane;

import javax.swing.*;
import java.awt.*;

public class ViewClothingPanel extends OptionPanelConstructor {
    public ViewClothingPanel() {
        super();
        this.getPanel().setBackground(Color.DARK_GRAY);
        makeFormTitleLabel();
        formTitle.setText("View Clothing");

    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }
}
