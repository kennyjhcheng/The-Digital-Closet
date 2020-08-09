package gui.tabbedframe.closetpane;

import gui.mainmenu.MainMenu;
import model.Clothing;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class RemoveClothingPanel extends OptionPanelConstructor {
    JTextField removeTextField;

    JButton removeButton;

    JLabel removeRequest;
    JLabel removeLabel;

    private JLabel nameLabel;
    private JLabel typeLabel;
    private JLabel colorLabel;
    private JLabel sizeLabel;
    private JPanel viewClothingPanel;
    public static DefaultListModel<Clothing> removeClothingModel;
    private JList<Clothing> clothingJList;
    private JSplitPane viewAreaSplitPane;


    public RemoveClothingPanel() {
        super();
        this.getPanel().setBackground(Color.LIGHT_GRAY);
        makeFormTitleLabel();
        formTitle.setText("Remove Clothing Form");

        removeRequest = new JLabel("Which clothing would you like to remove from your Closet?"
                + " (Please type the name");
        removeRequest.setFont(REQUEST_FONT);
        removeRequest.setBounds(30, 50, 600, 50);
        this.getPanel().add(removeRequest);

        removeLabel = new JLabel("Remove:");
        removeLabel.setFont(LABEL_FONT);
        removeLabel.setBounds(30, 80, 100, 50);
        this.getPanel().add(removeLabel);


        removeTextField = new JTextField();
        removeTextField.setBounds(107, 92, 400, 30);
        this.getPanel().add(removeTextField);

        makeSplitPane();

    }

    private void makeSplitPane() {
        initializeSplitPaneFields();

        for (Clothing c : MainMenu.myCloset.getClothes()) {
            removeClothingModel.addElement(c);
        }
        clothingJList.setModel(removeClothingModel);
        clothingJList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Clothing c = clothingJList.getSelectedValue();
                nameLabel.setText("Name: " + c.getName());
                typeLabel.setText("Type: " + c.getType());
                colorLabel.setText("Color: " + c.getType());
                sizeLabel.setText("Size: " + c.getSize());
            }
        });

        viewClothingPanel.setLayout(new BoxLayout(viewClothingPanel, BoxLayout.PAGE_AXIS));
        addLabelsToViewClothingPanel();

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
        removeClothingModel = new DefaultListModel<>();
        clothingJList = new JList<>();
        viewAreaSplitPane = new JSplitPane();
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }
}
