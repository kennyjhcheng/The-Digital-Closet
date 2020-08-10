package gui.tabbedframe.closetpane;

import gui.PlayButtonSound;
import gui.mainmenu.MainMenu;
import gui.tabbedframe.TabbedPane;
import model.Clothing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveClothingButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String removeName = RemoveClothingPanel.removeTextField.getText();
        removeName = removeName.toLowerCase();

        PlayButtonSound.playButtonSound("button_click.wav");
        if (MainMenu.myCloset.containsClothing(removeName)) {
            int a = JOptionPane.showConfirmDialog(null, "Are you sure you would like to remove "
                    + "this clothing?", "Remove Confirmation", JOptionPane.YES_NO_OPTION);
            if (a == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(TabbedPane.tabbedPaneFrame, "Successfully removed " + removeName);
                MainMenu.myCloset.removeClothing(MainMenu.myCloset.getClothingByName(removeName));
                for (int i = 0; i < RemoveClothingPanel.removeClothingModel.size(); i++) {
                    if (RemoveClothingPanel.removeClothingModel.getElementAt(i)
                            .equalClothing(MainMenu.myCloset.getClothingByName(removeName))) {
                        RemoveClothingPanel.removeClothingModel.remove(i);
                        break;
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(TabbedPane.tabbedPaneFrame, "This item of clothing does not exist");
        }
    }
}
