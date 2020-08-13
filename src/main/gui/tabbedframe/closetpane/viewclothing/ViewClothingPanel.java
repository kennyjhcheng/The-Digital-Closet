package gui.tabbedframe.closetpane.viewclothing;


import exceptions.DuplicateClothingException;
import exceptions.EmptyClosetException;
import gui.mainmenu.MainMenu;
import gui.tabbedframe.closetpane.OptionPanelConstructor;
import model.Closet;
import model.Clothing;

import javax.swing.*;

import java.awt.*;

// The GUI for the panel allowing the user to view all clothing
public class ViewClothingPanel extends OptionPanelConstructor {
    JTabbedPane viewClothingTabbedPane;

    public static ViewSplitPane allSplitPane;
    public static ViewSplitPane shirtSplitPane;
    public static ViewSplitPane pantsSplitPane;
    public static ViewSplitPane shoesSplitPane;
    public static ViewSplitPane socksSplitPane;
    public static ViewSplitPane accessoriesSplitPane;

    public ViewClothingPanel() {
        super();
        this.getPanel().setBackground(Color.LIGHT_GRAY);
        makeFormTitleLabel();
        formTitle.setText("View Clothing");

        try {
            allSplitPane = new ViewSplitPane();
            allSplitPane.setCloset(MainMenu.myCloset);

            shirtSplitPane = new ViewSplitPane();
            shirtSplitPane.setCloset(MainMenu.myCloset.getClosetByType("shirt"));

            pantsSplitPane = new ViewSplitPane();
            pantsSplitPane.setCloset(MainMenu.myCloset.getClosetByType("pants"));

            socksSplitPane = new ViewSplitPane();
            socksSplitPane.setCloset(MainMenu.myCloset.getClosetByType("socks"));

            shoesSplitPane = new ViewSplitPane();
            shoesSplitPane.setCloset(MainMenu.myCloset.getClosetByType("shoes"));

            accessoriesSplitPane = new ViewSplitPane();
            accessoriesSplitPane.setCloset(MainMenu.myCloset.getClosetByType("accessories"));
        } catch (Exception e) {
            ;
        }

        makeViewClothingTabbedPane();
    }

    private void makeViewClothingTabbedPane() {
        viewClothingTabbedPane = new JTabbedPane();
        viewClothingTabbedPane.setBounds(50, 80, 700, 450);
        viewClothingTabbedPane.add("All        ", allSplitPane);
        viewClothingTabbedPane.add("Shirts     ", shirtSplitPane);
        viewClothingTabbedPane.add("Pants      ", pantsSplitPane);
        viewClothingTabbedPane.add("Shoes      ", shoesSplitPane);
        viewClothingTabbedPane.add("Socks      ", socksSplitPane);
        viewClothingTabbedPane.add("Accessories", accessoriesSplitPane);
        this.getPanel().add(viewClothingTabbedPane);
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }
}
