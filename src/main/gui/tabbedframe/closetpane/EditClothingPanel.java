package gui.tabbedframe.closetpane;

import gui.mainmenu.MainMenu;
import model.Clothing;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class EditClothingPanel extends OptionPanelConstructor {
    JLabel editRequest;
    JLabel editLabel;
    JTextField editTextField;

    JLabel editNameLabel;
    JLabel editTypeLabel;
    JLabel editColorLabel;
    JLabel editSizeLabel;


    private JLabel nameLabel;
    private JLabel typeLabel;
    private JLabel colorLabel;
    private JLabel sizeLabel;
    private JPanel viewClothingPanel;
    public static DefaultListModel<Clothing> editClothingModel;
    public static JList<Clothing> clothingJList;
    private JSplitPane viewAreaSplitPane;

    public EditClothingPanel() {
        super();
        this.getPanel().setBackground(Color.LIGHT_GRAY);
        makeFormTitleLabel();
        formTitle.setText("Edit Clothing Form");



        makeSplitPane();
    }

    private void makeSplitPane() {
        initializeSplitPaneFields();

        for (Clothing c : MainMenu.myCloset.getClothes()) {
            editClothingModel.addElement(c);
        }
        clothingJList.setModel(editClothingModel);
        clothingJList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Clothing c = clothingJList.getSelectedValue();
                if (c != null) {
                    nameLabel.setText("Name: " + c.getName());
                    typeLabel.setText("Type: " + c.getType());
                    colorLabel.setText("Color: " + c.getType());
                    sizeLabel.setText("Size: " + c.getSize());
                    editTextField.setText(c.getName());
                }
            }
        });

        viewClothingPanel.setLayout(new BoxLayout(viewClothingPanel, BoxLayout.PAGE_AXIS));
        addLabelsToViewClothingPanel();

        setSplitPane();
    }

    private void setSplitPane() {
        viewAreaSplitPane.setLeftComponent(new JScrollPane(clothingJList));
        viewAreaSplitPane.setRightComponent(viewClothingPanel);
        viewAreaSplitPane.setBounds(30, 150, 700, 400);
        this.getPanel().add(viewAreaSplitPane);
    }

    private void addLabelsToViewClothingPanel() {
        viewClothingPanel.add(nameLabel);
        viewClothingPanel.add(typeLabel);
        viewClothingPanel.add(colorLabel);
        viewClothingPanel.add(sizeLabel);
    }

    private void initializeSplitPaneFields() {
        nameLabel = new JLabel();
        typeLabel = new JLabel();
        colorLabel = new JLabel();
        sizeLabel = new JLabel();
        viewClothingPanel = new JPanel();
        editClothingModel = new DefaultListModel<>();
        clothingJList = new JList<>();
        viewAreaSplitPane = new JSplitPane();
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }
}
