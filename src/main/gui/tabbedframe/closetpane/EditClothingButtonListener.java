package gui.tabbedframe.closetpane;

import gui.mainmenu.MainMenu;
import gui.tabbedframe.TabbedPane;
import jdk.nashorn.internal.scripts.JO;
import model.Clothing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Action Listener for the Edit Clothing Button which updates the specified changes in myCloset and all JLists
public class EditClothingButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedIndex = EditClothingPanel.clothingJList.getSelectedIndex();
        String selectedName = EditClothingPanel.editClothingModel.get(selectedIndex).getName();
        String newName = EditClothingPanel.editNameTextField.getText();
        newName = newName.toLowerCase();
        String newType = EditClothingPanel.editTypeTextField.getText();
        newType = newType.toLowerCase();
        String newColor = EditClothingPanel.editColorTextField.getText();
        newColor = newColor.toLowerCase();
        double newSize = Double.parseDouble(EditClothingPanel. editSizeTextField.getText());

        Clothing editedClothing = new Clothing(newName, newType, newColor, newSize);

        if (selectedIndex != -1) {
            EditClothingPanel.editClothingModel.remove(selectedIndex);
            EditClothingPanel.editClothingModel.add(selectedIndex, editedClothing);
        }

        editInRemoveModel(selectedName, editedClothing);
        editInViewModel(selectedName, editedClothing);

        MainMenu.myCloset.getClothingByName(selectedName).changeColor(newColor);
        MainMenu.myCloset.getClothingByName(selectedName).changeTypeAndSize(newType, newSize);
        MainMenu.myCloset.getClothingByName(selectedName).changeName(newName);
    }

    private void editInViewModel(String selectedName, Clothing editedClothing) {
        int indexAll = getIndexOfClothingInModel(selectedName, ViewClothingPanel.allModel);
        if (indexAll != -1) {
            ViewClothingPanel.allModel.remove(indexAll);
            ViewClothingPanel.allModel.add(indexAll, editedClothing);
        } else {
            JOptionPane.showMessageDialog(TabbedPane.tabbedPaneFrame, "Edit update in View All Pane failed");
        }

        removeFromOldTypePane(selectedName);
        addToNewTypePanel(editedClothing);
    }

    private void addToNewTypePanel(Clothing editedClothing) {
        switch (editedClothing.getType()) {
            case "shirt":
                ViewClothingPanel.shirtModel.addElement(editedClothing);
                break;
            case "pants":
                ViewClothingPanel.pantsModel.addElement(editedClothing);
                break;
            case "shoes":
                ViewClothingPanel.shoesModel.addElement(editedClothing);
                break;
            case "socks":
                ViewClothingPanel.socksModel.addElement(editedClothing);
                break;
            case "accessories":
                ViewClothingPanel.accessoriesModel.addElement(editedClothing);
                break;
        }
    }

    private void removeFromOldTypePane(String selectedName) {
        int indexOldType;
        switch (MainMenu.myCloset.getClothingByName(selectedName).getType()) {
            case "shirt":
                indexOldType = getIndexOfClothingInModel(selectedName, ViewClothingPanel.shirtModel);
                ViewClothingPanel.shirtModel.remove(indexOldType);
                break;
            case "pants":
                indexOldType = getIndexOfClothingInModel(selectedName, ViewClothingPanel.pantsModel);
                ViewClothingPanel.pantsModel.remove(indexOldType);
                break;
            case "shoes":
                indexOldType = getIndexOfClothingInModel(selectedName, ViewClothingPanel.shoesModel);
                ViewClothingPanel.shoesModel.remove(indexOldType);
                break;
            case "socks":
                indexOldType = getIndexOfClothingInModel(selectedName, ViewClothingPanel.socksModel);
                ViewClothingPanel.socksModel.remove(indexOldType);
                break;
            case "accessories":
                indexOldType = getIndexOfClothingInModel(selectedName, ViewClothingPanel.accessoriesModel);
                ViewClothingPanel.accessoriesModel.remove(indexOldType);
                break;
        }
    }

    private void editInRemoveModel(String selectedName, Clothing editedClothing) {
        int index = getIndexOfClothingInModel(selectedName, RemoveClothingPanel.removeClothingModel);
        if (index != -1) {
            RemoveClothingPanel.removeClothingModel.remove(index);
            RemoveClothingPanel.removeClothingModel.add(index, editedClothing);
        } else {
            JOptionPane.showMessageDialog(TabbedPane.tabbedPaneFrame, "Edit update in Remove Pane failed");
        }
    }

    private int getIndexOfClothingInModel(String clothingName, DefaultListModel<Clothing> model) {
        int index = -1;
        for (int i = 0; i < model.size(); i++) {
            if (model.getElementAt(i)
                    .equalClothing(MainMenu.myCloset.getClothingByName(clothingName))) {
                index = i;
                break;
            }
        }

        return index;
    }
}
