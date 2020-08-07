package gui.tabbedframe.closetpane;

import exceptions.DuplicateClothingException;
import gui.mainmenu.MainMenu;
import gui.tabbedframe.TabbedPane;
import model.Clothing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static gui.tabbedframe.closetpane.AddClothingPanel.nameTextField;
import static gui.tabbedframe.closetpane.AddClothingPanel.typeComboBox;
import static gui.tabbedframe.closetpane.AddClothingPanel.colorTextField;
import static gui.tabbedframe.closetpane.AddClothingPanel.sizeTextField;


public class AddClothingButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameTextField.getText();
        String type = typeComboBox.getEditor().getItem().toString();
        String color = colorTextField.getText();

        try {
            double size = Double.parseDouble(sizeTextField.getText());
            MainMenu.myCloset.addClothing(new Clothing(name, type, color, size));
            JOptionPane.showMessageDialog(TabbedPane.tabbedPaneFrame, "Successfully Added\n" + "\t" + name
                    + " to your Closet!");
        } catch (DuplicateClothingException duplicateClothingException) {
            JOptionPane.showMessageDialog(TabbedPane.tabbedPaneFrame, "Your Closet already contains "
                    + "this clothing\n");
            duplicateClothingException.printStackTrace();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(TabbedPane.tabbedPaneFrame, "Please input a size\n");

        }


    }
}
