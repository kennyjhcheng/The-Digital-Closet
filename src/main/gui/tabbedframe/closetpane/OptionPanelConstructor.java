package gui.tabbedframe.closetpane;

import gui.tabbedframe.TabbedPane;

import javax.swing.*;
import java.awt.*;

// Abstract Class containing Title and Panel constructor for all Closet Option Panels
public abstract class OptionPanelConstructor implements OptionPanel {
    protected static final Font TITLE_FONT = new Font(TabbedPane.TABBED_PANE_FONT_STYLE, Font.BOLD, 30);
    protected static final Font REQUEST_FONT = new Font(TabbedPane.TABBED_PANE_FONT_STYLE, Font.PLAIN, 15);
    protected static final Font LABEL_FONT = new Font(TabbedPane.TABBED_PANE_FONT_STYLE, Font.BOLD, 18);
    protected static final String SHIRT_SIZING = "Shirt Sizes: "
            + "0.0 = xSmall, 1.0 = small, 2.0 = medium, 3.0 = large, 4.0 = xLarge";
    protected static final String PANTS_SIZING = "Pants Sizes: waist length in inches (e.g. 32.0 or 27.5)";
    protected static final String SHOES_SIZING = "Shoe sizes: US Sizing (e.g. 9.0 or 5.5)";
    protected static final String SOCKS_SIZING = "Sock Sizes: 0.0 = xSmall, 1.0 = small, 2.0 = medium,"
            + " 3.0 = large, 4.0 = xLarge";
    protected static final String ACCESSORIES_SIZING = "Accessories are not sized -> please input 1.0";

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
        formTitle.setBounds(255, 10, 400, 50);
        formTitle.setFont(TITLE_FONT);
        this.getPanel().add(formTitle);
    }

}
