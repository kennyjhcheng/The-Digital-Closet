package gui.tabbedframe.closetpane;

import gui.tabbedframe.TabbedPane;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class ClosetPane {
    public static final Font SIDE_PANEL_FONT = new Font("Comic Sans MS", Font.BOLD, 14);
    public static final int SIDE_PANEL_BUTTON_X = 5;
    public static final int SIDE_PANEL_BUTTON_INITIAL_Y = 50;
    public static final int SIDE_PANEL_BUTTON_INCREMENT_Y = 100;
    public static final int SIDE_PANEL_BUTTON_WIDTH = 190;
    public static final int SIDE_PANEL_BUTTON_HEIGHT = 70;
    JPanel closetPanel;
    JPanel sidePanel;
    public static OptionPanel addClothingPanel = new AddClothingPanel();
    public static OptionPanel removeClothingPanel = new RemoveClothingPanel();
    public static OptionPanel editClothingPanel = new EditClothingPanel();
    public static OptionPanel viewClothingPanel = new ViewClothingPanel();

    JLabel sidePanelLabel;

    public static JButton addClothingButton;
    public static JButton removeClothingButton;
    public static JButton viewClothingButton;
    public static JButton editClothingButton;

    ClosetSidePanelListener sidePanelListener = new ClosetSidePanelListener();

    public ClosetPane() {
        makeClosetPanel();
        makeSidePanel();

        addClothingButton = makeSidePanelButton("Add Clothing", 0);
        removeClothingButton = makeSidePanelButton("Remove Clothing", 1);
        viewClothingButton = makeSidePanelButton("View Clothing", 2);
        editClothingButton = makeSidePanelButton("Edit Clothing", 3);

        buttonUI();
    }

    private void buttonUI() {
        Component[] components = sidePanel.getComponents();

        for (Component component : components) {
            if (component instanceof JButton) {
                ((JButton) component).setUI(new BasicButtonUI());
            }
        }
    }

    private JButton makeSidePanelButton(String label, int incrementScale) {
        JButton sidePanelButton = new JButton(label);
        sidePanelButton.setFont(SIDE_PANEL_FONT);
        sidePanelButton.setBounds(SIDE_PANEL_BUTTON_X,
                incrementScale * SIDE_PANEL_BUTTON_INCREMENT_Y + SIDE_PANEL_BUTTON_INITIAL_Y,
                SIDE_PANEL_BUTTON_WIDTH, SIDE_PANEL_BUTTON_HEIGHT);
        sidePanelButton.setOpaque(false);
        sidePanelButton.setForeground(Color.WHITE);
        sidePanelButton.addActionListener(sidePanelListener);
        sidePanel.add(sidePanelButton);

        return sidePanelButton;
    }

    private void makeClosetPanel() {
        closetPanel = new JPanel();
        closetPanel.setLayout(null);
        closetPanel.add(addClothingPanel.getPanel());
        closetPanel.add(removeClothingPanel.getPanel());
        closetPanel.add(editClothingPanel.getPanel());
        closetPanel.add(viewClothingPanel.getPanel());
    }

    private void makeSidePanel() {
        sidePanel = new JPanel();
        sidePanelLabel = new JLabel("What would you like to do?");

        sidePanelLabel.setBounds(10, 13, 200, 20);
        sidePanelLabel.setFont(SIDE_PANEL_FONT);
        sidePanelLabel.setForeground(Color.WHITE);

        sidePanel.setBackground(Color.DARK_GRAY);
        sidePanel.setBounds(0, 0, TabbedPane.TABBED_PANE_WIDTH / 5, TabbedPane.TABBED_PANE_HEIGHT);
        sidePanel.setLayout(null);
        sidePanel.add(sidePanelLabel);
        closetPanel.add(sidePanel);
    }

    public JPanel getClosetPanel() {
        return closetPanel;
    }
}
