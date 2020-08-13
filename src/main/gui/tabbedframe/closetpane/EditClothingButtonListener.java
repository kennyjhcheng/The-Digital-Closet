package gui.tabbedframe.closetpane;

import gui.mainmenu.MainMenu;
import gui.tabbedframe.TabbedPane;
import gui.tabbedframe.closetpane.viewclothing.ViewClothingPanel;
import gui.tabbedframe.closetpane.viewclothing.ViewSplitPane;
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
        double newSize = Double.parseDouble(EditClothingPanel.editSizeTextField.getText());

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
        JOptionPane.showMessageDialog(TabbedPane.tabbedPaneFrame,
                "Edit Success! Please click on the clothing to see changes");
        clearTextFields();
    }

    private void clearTextFields() {
        EditClothingPanel.editNameTextField.setText(null);
        EditClothingPanel.editTypeTextField.setText(null);
        EditClothingPanel.editColorTextField.setText(null);
        EditClothingPanel.editSizeTextField.setText(null);

        EditClothingPanel.editTextField.setText(null);
    }

    private void editInViewModel(String selectedName, Clothing editedClothing) {
        int indexAll = getIndexOfClothingInModel(selectedName, ViewClothingPanel.allSplitPane.getTypeModel());
        if (indexAll != -1) {
            ViewClothingPanel.allSplitPane.getTypeModel().remove(indexAll);
            ViewClothingPanel.allSplitPane.getTypeModel().add(indexAll, editedClothing);
        } else {
            JOptionPane.showMessageDialog(TabbedPane.tabbedPaneFrame, "Edit update in View All Pane failed");
        }

        removeFromOldTypePane(selectedName);
        addToNewTypePanel(editedClothing);
    }

    private void addToNewTypePanel(Clothing editedClothing) {
        switch (editedClothing.getType()) {
            case "shirt":
                ViewClothingPanel.shirtSplitPane.getTypeModel().addElement(editedClothing);
                break;
            case "pants":
                ViewClothingPanel.pantsSplitPane.getTypeModel().addElement(editedClothing);
                break;
            case "shoes":
                ViewClothingPanel.shoesSplitPane.getTypeModel().addElement(editedClothing);
                break;
            case "socks":
                ViewClothingPanel.socksSplitPane.getTypeModel().addElement(editedClothing);
                break;
            case "accessories":
                ViewClothingPanel.accessoriesSplitPane.getTypeModel().addElement(editedClothing);
                break;
        }
    }

    private void removeFromOldTypePane(String selectedName) {

        switch (MainMenu.myCloset.getClothingByName(selectedName).getType()) {
            case "shirt":
                doRemove(selectedName, ViewClothingPanel.shirtSplitPane);
                break;
            case "pants":
                doRemove(selectedName, ViewClothingPanel.pantsSplitPane);
                break;
            case "shoes":
                doRemove(selectedName, ViewClothingPanel.shoesSplitPane);
                break;
            case "socks":
                doRemove(selectedName, ViewClothingPanel.socksSplitPane);
                break;
            case "accessories":
                doRemove(selectedName, ViewClothingPanel.accessoriesSplitPane);;
                break;
        }
    }

    private void doRemove(String selectedName, ViewSplitPane typeSplitPane) {
        int indexOldType = getIndexOfClothingInModel(selectedName, typeSplitPane.getTypeModel());
        typeSplitPane.getTypeModel().remove(indexOldType);
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
