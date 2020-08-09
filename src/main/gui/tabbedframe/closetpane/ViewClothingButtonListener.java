package gui.tabbedframe.closetpane;

import exceptions.DuplicateClothingException;
import gui.mainmenu.MainMenu;
import gui.tabbedframe.TabbedPane;
import model.Closet;
import model.Clothing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewClothingButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        String type = (String) ViewClothingPanel.viewOptions.getSelectedItem();
        try {
            ViewClothingPanel.viewClothingModel.clear();
            if (type.equals("all")) {
                showClothing(MainMenu.myCloset);
            } else {
                showClothing(MainMenu.myCloset.getClosetByType(type));
            }
        } catch (DuplicateClothingException duplicateClothingException) {
            JOptionPane.showMessageDialog(TabbedPane.tabbedPaneFrame, "You do not have any clothes of "
                    + "the type " + type + "in your Closet");
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(TabbedPane.tabbedPaneFrame, "Please select what you wish to view");
            exception.printStackTrace();
        }
    }

    private void showClothing(Closet closetByType) {
        for (Clothing c : closetByType.getClothes()) {
            ViewClothingPanel.viewClothingModel.addElement(c);
        }
    }

}
