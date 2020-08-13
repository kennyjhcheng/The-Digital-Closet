package gui.tabbedframe.closetpane;

import gui.mainmenu.MainMenu;
import gui.tabbedframe.TabbedPane;
import model.Clothing;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// The GUI for the panel allowing users to edit existing clothing in myCloset and edit their attributes
public class EditClothingPanel extends OptionPanelConstructor {
    JLabel editRequest;
    JLabel editLabel;
    public static JTextField editTextField;

    JLabel editAttributeRequest;
    JLabel editNameLabel;
    JLabel editTypeLabel;
    JLabel editColorLabel;
    JLabel editSizeLabel;
    JLabel typeSizeWarningLabel;

    public static JTextField editNameTextField;
    public static JTextField editTypeTextField;
    public static JTextField editColorTextField;
    public static JTextField editSizeTextField;

    JButton sizingChartButton;
    JButton editButton;
    EditClothingButtonListener editClothingButtonListener = new EditClothingButtonListener();

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

        makeEditRequestComponents();
        makeEditAttributeRequestAndLabels();
        makeEditAttributeTextFields();

        makeWarningLabel();

        makeSizingChartButton();

        makeEditButton();

        makeSplitPane();
    }

    private void makeSizingChartButton() {
        sizingChartButton = new JButton("Sizing Chart");
        sizingChartButton.setFont(new Font(TabbedPane.TABBED_PANE_FONT_STYLE, Font.PLAIN, 12));
        sizingChartButton.setBounds(475, 200, 125, 25);
        sizingChartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(TabbedPane.tabbedPaneFrame,
                        SHIRT_SIZING + "\n"
                                + PANTS_SIZING + "\n"
                                + SHOES_SIZING + "\n"
                                + SOCKS_SIZING + "\n"
                                + ACCESSORIES_SIZING);
            }
        });
        this.getPanel().add(sizingChartButton);
    }

    private void makeWarningLabel() {
        typeSizeWarningLabel = new JLabel("Ensure type and size attributes correspond to the Sizing Chart");
        typeSizeWarningLabel.setFont(REQUEST_FONT);
        typeSizeWarningLabel.setForeground(Color.RED);
        typeSizeWarningLabel.setBounds(30, 185, 500, 50);
        this.getPanel().add(typeSizeWarningLabel);
    }

    private void makeEditButton() {
        editButton = new JButton("Edit Clothing");
        editButton.setFont(new Font(TabbedPane.TABBED_PANE_FONT_STYLE, Font.BOLD, 20));
        editButton.setBounds(475, 75, 200, 50);
        editButton.addActionListener(editClothingButtonListener);
        this.getPanel().add(editButton);
    }

    private void makeEditAttributeTextFields() {
        editNameTextField = new JTextField("", 10);
        editNameTextField.setBounds(30 + 60, 50 + 2 * 25 + 30 + 15, 250, 25);
        this.getPanel().add(editNameTextField);

        editTypeTextField = new JTextField("", 10);
        editTypeTextField.setBounds(30 + 60 + 350, 50 + 2 * 25 + 30 + 15, 250, 25);
        this.getPanel().add(editTypeTextField);

        editColorTextField = new JTextField("", 10);
        editColorTextField.setBounds(30 + 60, 50 + 3 * 25 + 30 + 15, 250, 25);
        this.getPanel().add(editColorTextField);

        editSizeTextField = new JTextField("", 10);
        editSizeTextField.setBounds(30 + 60 + 350, 50 + 3 * 25 + 30 + 15, 250, 25);
        this.getPanel().add(editSizeTextField);
    }

    private void makeEditAttributeRequestAndLabels() {
        editAttributeRequest = new JLabel("Please change the attributes you wish to edit below.");
        editAttributeRequest.setFont(REQUEST_FONT);
        editAttributeRequest.setBounds(30, 50 + 25 + 30, 400, 50);
        this.getPanel().add(editAttributeRequest);

        editNameLabel = new JLabel("Name:");
        editNameLabel.setFont(LABEL_FONT);
        editNameLabel.setBounds(30, 50 + 2 * 25 + 30, 100, 50);
        this.getPanel().add(editNameLabel);

        editTypeLabel = new JLabel("Type:");
        editTypeLabel.setFont(LABEL_FONT);
        editTypeLabel.setBounds(30 + 350, 50 + 2 * 25 + 30, 100, 50);
        this.getPanel().add(editTypeLabel);

        editColorLabel = new JLabel("Color:");
        editColorLabel.setFont(LABEL_FONT);
        editColorLabel.setBounds(36, 50 + 3 * 25 + 30, 100, 50);
        this.getPanel().add(editColorLabel);

        editSizeLabel = new JLabel("Size:");
        editSizeLabel.setFont(LABEL_FONT);
        editSizeLabel.setBounds(36 + 350, 50 + 3 * 25 + 30, 100, 50);
        this.getPanel().add(editSizeLabel);
    }

    private void makeEditRequestComponents() {
        editRequest = new JLabel("Which Clothing would you like to edit? (Please click below)");
        editRequest.setFont(REQUEST_FONT);
        editRequest.setBounds(30, 50, 500, 50);
        this.getPanel().add(editRequest);

        editLabel = new JLabel("Edit:");
        editLabel.setFont(LABEL_FONT);
        editLabel.setBounds(30, 50 + 25, 100, 50);
        this.getPanel().add(editLabel);

        editTextField = new JTextField("", 10);
        editTextField.setBounds(30 + 45, 50 + 40, 300, 25);
        this.getPanel().add(editTextField);
    }

    private void makeSplitPane() {
        initializeSplitPaneFields();

        for (Clothing c : MainMenu.myCloset.getClothes()) {
            editClothingModel.addElement(c);
        }
        clothingJList.setModel(editClothingModel);
        makeListSelectionListener();

        viewClothingPanel.setLayout(new BoxLayout(viewClothingPanel, BoxLayout.PAGE_AXIS));
        addLabelsToViewClothingPanel();

        configureAndAddSplitPane();
    }

    private void makeListSelectionListener() {
        clothingJList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Clothing c = clothingJList.getSelectedValue();
                if (c != null) {
                    nameLabel.setText("Name: " + c.getName());
                    typeLabel.setText("Type: " + c.getType());
                    colorLabel.setText("Color: " + c.getColor());
                    sizeLabel.setText("Size: " + c.getSize());
                    editTextField.setText(c.getName());
                    editNameTextField.setText(c.getName());
                    editTypeTextField.setText(c.getType());
                    editColorTextField.setText(c.getColor());
                    editSizeTextField.setText("" + c.getSize());
                }
            }
        });
    }

    private void configureAndAddSplitPane() {
        viewAreaSplitPane.setLeftComponent(new JScrollPane(clothingJList));
        viewAreaSplitPane.setRightComponent(viewClothingPanel);
        viewAreaSplitPane.setBounds(30, 230, 700, 370);
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
