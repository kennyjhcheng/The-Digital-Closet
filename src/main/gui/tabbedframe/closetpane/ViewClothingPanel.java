package gui.tabbedframe.closetpane;


import model.Clothing;

import javax.swing.*;

import java.awt.*;

public class ViewClothingPanel extends OptionPanelConstructor {
    public static JComboBox<String> viewOptions;
    JLabel viewLabel;
    JButton viewButton;

    JSplitPane viewAreaSplitPane;
    public static JList<Clothing> clothingJList;
    public static DefaultListModel<Clothing> viewClothingModel;
    JPanel viewClothingPanel;
    JLabel nameLabel;
    JLabel typeLabel;
    JLabel colorLabel;
    JLabel sizeLabel;

    ViewClothingButtonListener viewClothingButtonListener = new ViewClothingButtonListener();

    public ViewClothingPanel() {
        super();
        this.getPanel().setBackground(Color.LIGHT_GRAY);
        makeFormTitleLabel();
        formTitle.setText("View Clothing");

        makeViewOptionComponents();

        makeSplitPane();

    }

    private void makeSplitPane() {
        initializeSplitPaneFields();

        clothingJList.setModel(viewClothingModel);
        clothingJList.getSelectionModel().addListSelectionListener(e -> {
            Clothing c = clothingJList.getSelectedValue();
            nameLabel.setText("Name: " + c.getName());
            typeLabel.setText("Type: " + c.getType());
            colorLabel.setText("Color: " + c.getColor());
            sizeLabel.setText("Size: " + c.getSize());
            clothingJList.clearSelection();
        });

        viewClothingPanel.setLayout(new BoxLayout(viewClothingPanel, BoxLayout.PAGE_AXIS));
        viewClothingPanel.add(nameLabel);
        viewClothingPanel.add(typeLabel);
        viewClothingPanel.add(colorLabel);
        viewClothingPanel.add(sizeLabel);

        viewAreaSplitPane.setLeftComponent(new JScrollPane(clothingJList));
        viewAreaSplitPane.setRightComponent(viewClothingPanel);
        viewAreaSplitPane.setBounds(45, 150, 700, 400);
        this.getPanel().add(viewAreaSplitPane);
    }

    private void initializeSplitPaneFields() {
        nameLabel = new JLabel();
        typeLabel = new JLabel();
        colorLabel = new JLabel();
        sizeLabel = new JLabel();
        viewClothingPanel = new JPanel();
        viewClothingModel = new DefaultListModel<>();
        clothingJList = new JList<>();
        viewAreaSplitPane = new JSplitPane();
    }

    private void makeViewOptionComponents() {
        viewLabel = new JLabel("View:");
        viewLabel.setFont(LABEL_FONT);
        viewLabel.setBounds(100, 75, 400, 50);
        this.getPanel().add(viewLabel);

        viewOptions = new JComboBox<>(TYPES_WITH_ALL);
        viewOptions.setBounds(150, 87, 400, 30);
        viewOptions.setSelectedItem(null);
        this.getPanel().add(viewOptions);

        viewButton = new JButton("View");
        viewButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        viewButton.setBounds(560, 75, 100, 50);
        viewButton.addActionListener(viewClothingButtonListener);
        this.getPanel().add(viewButton);
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }
}
