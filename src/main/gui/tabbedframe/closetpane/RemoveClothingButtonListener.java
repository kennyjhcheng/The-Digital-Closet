package gui.tabbedframe.closetpane;

import gui.PlayButtonSound;
import gui.mainmenu.MainMenu;
import gui.tabbedframe.TabbedPane;
import model.Clothing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Action Listener for the Button that removes the user-specified clothing from myCloset and all JLists viewing Clothing
public class RemoveClothingButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String removeName = RemoveClothingPanel.removeTextField.getText();
        removeName = removeName.toLowerCase();
        String removeType = MainMenu.myCloset.getClothingByName(removeName).getType();

        PlayButtonSound.playSound("button_click.wav");
        if (MainMenu.myCloset.containsClothing(removeName)) {
            int a = JOptionPane.showConfirmDialog(null, "Are you sure you would like to remove "
                    + "this clothing?", "Remove Confirmation", JOptionPane.YES_NO_OPTION);
            if (a == JOptionPane.YES_OPTION) {
                DefaultListModel<Clothing> model =
                        (DefaultListModel<Clothing>) RemoveClothingPanel.clothingJList.getModel();
                int selectedIndex = RemoveClothingPanel.clothingJList.getSelectedIndex();
                if (selectedIndex != -1) {
                    model.remove(selectedIndex);
                }
                removeFromViewPane(removeName, removeType);
                removeFromIndex(removeName, EditClothingPanel.editClothingModel);
                MainMenu.myCloset.removeClothing(MainMenu.myCloset.getClothingByName(removeName));
                JOptionPane.showMessageDialog(TabbedPane.tabbedPaneFrame, "Successfully removed " + removeName);
            }
        } else {
            JOptionPane.showMessageDialog(TabbedPane.tabbedPaneFrame, "This item of clothing does not exist");
        }
    }

    private void removeFromViewPane(String removeName, String removeType) {
        removeFromIndex(removeName, ViewClothingPanel.allModel);
        switch (removeType) {
            case "shirt":
                removeFromIndex(removeName, ViewClothingPanel.shirtModel);
                break;
            case "pants":
                removeFromIndex(removeName, ViewClothingPanel.pantsModel);
                break;
            case "shoes":
                removeFromIndex(removeName, ViewClothingPanel.shoesModel);
                break;
            case "socks":
                removeFromIndex(removeName, ViewClothingPanel.socksModel);
                break;
            case "accessories":
                removeFromIndex(removeName, ViewClothingPanel.accessoriesModel);
                break;
        }
    }

    private void removeFromIndex(String removeName, DefaultListModel<Clothing> typeModel) {
        for (int i = 0; i < typeModel.size(); i++) {
            if (typeModel.getElementAt(i)
                    .equalClothing(MainMenu.myCloset.getClothingByName(removeName))) {
                typeModel.remove(i);
                break;
            }
        }
    }
}
