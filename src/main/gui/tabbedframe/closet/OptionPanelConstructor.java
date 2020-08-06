package gui.tabbedframe.closet;

import gui.tabbedframe.TabbedPane;

import javax.swing.*;
import java.awt.*;

public abstract class OptionPanelConstructor implements OptionPanel {
    protected JPanel panel;

    public OptionPanelConstructor() {
        panel = new JPanel();
        panel.setBackground(Color.BLUE);
        panel.setLayout(null);
        panel.setBounds(200, 0,
                TabbedPane.TABBED_PANE_WIDTH - TabbedPane.TABBED_PANE_WIDTH / 5, TabbedPane.TABBED_PANE_HEIGHT);
        panel.setVisible(false);
    }

}
