package gui.tabbedframe.closetpane;

import gui.tabbedframe.TabbedPane;

import javax.swing.*;
import java.awt.*;

public abstract class OptionPanelConstructor implements OptionPanel {
    protected static final Font TITLE_FONT = new Font("Comic Sans MS", Font.BOLD, 30);
    protected static final Font REQUEST_FONT = new Font("Comic Sans MS", Font.PLAIN, 15);
    protected static final Font LABEL_FONT = new Font("Comic Sans MS", Font.BOLD, 18);
    protected static final int COMPONENT_DISTANCE_INCREMENT = 60;
    public static final String[] TYPES = { "shirts", "pants", "shoes", "socks", "accessories" };

    protected JPanel panel;
    protected JLabel formTitle;

    public OptionPanelConstructor() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(200, 0,
                TabbedPane.TABBED_PANE_WIDTH - TabbedPane.TABBED_PANE_WIDTH / 5, TabbedPane.TABBED_PANE_HEIGHT);
        panel.setVisible(false);
    }

    public void makeFormTitleLabel() {
        formTitle = new JLabel();
        formTitle.setBounds(255,10,400,50);
        formTitle.setFont(TITLE_FONT);
        this.getPanel().add(formTitle);
    }

}
