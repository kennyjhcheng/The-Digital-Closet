package gui.tabbedframe.closetpane;

import exceptions.DuplicateClothingException;
import gui.PlayButtonSound;
import gui.mainmenu.MainMenu;
import gui.tabbedframe.TabbedPane;
import gui.tabbedframe.closetpane.viewclothing.ViewClothingPanel;
import model.Clothing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static gui.tabbedframe.closetpane.AddClothingPanel.nameTextField;
import static gui.tabbedframe.closetpane.AddClothingPanel.typeComboBox;
import static gui.tabbedframe.closetpane.AddClothingPanel.colorTextField;
import static gui.tabbedframe.closetpane.AddClothingPanel.sizeTextField;

// Action Listener for Add Clothing Button adding the clothing specified by the user to myCloset and all JLists
public class AddClothingButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameTextField.getText();
        String type = (String) typeComboBox.getSelectedItem();
        String color = colorTextField.getText();

        name = name.toLowerCase();
        color = color.toLowerCase();

        try {
            PlayButtonSound.playSound("button_click.wav");

            double size = Double.parseDouble(sizeTextField.getText());
            Clothing c = new Clothing(name, type, color, size);
            MainMenu.myCloset.addClothing(c);
            RemoveClothingPanel.removeClothingModel.addElement(c);
            addToViewClothingModels(c);
            EditClothingPanel.editClothingModel.addElement(c);
            resetForm();
            JOptionPane.showMessageDialog(TabbedPane.tabbedPaneFrame, "Successfully Added\n" + "\t" + name
                    + " to your Closet!");
        } catch (DuplicateClothingException duplicateClothingException) {
            JOptionPane.showMessageDialog(TabbedPane.tabbedPaneFrame, "Your Closet already contains "
                    + "this clothing\n");
            duplicateClothingException.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(TabbedPane.tabbedPaneFrame, "Please input a valid size\n");
        }

    }

    private void addToViewClothingModels(Clothing c) {
        ViewClothingPanel.allSplitPane.getTypeModel().addElement(c);
        switch (c.getType()) {
            case "shirt":
                ViewClothingPanel.shirtSplitPane.getTypeModel().addElement(c);
                break;
            case "pants":
                ViewClothingPanel.pantsSplitPane.getTypeModel().addElement(c);
                break;
            case "shoes":
                ViewClothingPanel.shoesSplitPane.getTypeModel().addElement(c);
                break;
            case "socks":
                ViewClothingPanel.socksSplitPane.getTypeModel().addElement(c);
                break;
            case "accessories":
                ViewClothingPanel.accessoriesSplitPane.getTypeModel().addElement(c);
                break;
        }
    }

    private void resetForm() {
        nameTextField.setText(null);
        typeComboBox.setSelectedItem(null);
        colorTextField.setText(null);
        sizeTextField.setText(null);
    }
}
