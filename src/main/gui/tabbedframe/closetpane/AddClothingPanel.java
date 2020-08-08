package gui.tabbedframe.closetpane;

import javax.swing.*;
import java.awt.*;


public class AddClothingPanel extends OptionPanelConstructor {
    private JLabel warningBeforeAdd;

    public static JTextField nameTextField;
    public static JComboBox<String> typeComboBox;
    public static JTextField colorTextField;
    public static JTextField sizeTextField;

    private JButton addClothingButton;
    //todo: clear all labels once add button is pressed
    private AddClothingButtonListener addClothingListener = new AddClothingButtonListener();

    public AddClothingPanel() {
        super();
        makeFormTitleLabel();
        formTitle.setText("Add Clothing Form");
        makeNameComponents();
        makeTypeComponents();
        makeColorComponents();
        makeSizeComponents();

        warningBeforeAdd = new JLabel("WARNING: Form will be cleared after adding, please double check the form!");
        warningBeforeAdd.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        warningBeforeAdd.setForeground(Color.RED);
        warningBeforeAdd.setBounds(30, 450, 800, 50);
        this.getPanel().add(warningBeforeAdd);
        addClothingButton = new JButton("Add Clothing to Closet");
        addClothingButton.setBounds(30, 500, 400, 50);
        addClothingButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        addClothingButton.addActionListener(addClothingListener);
        this.getPanel().add(addClothingButton);

    }

    private void makeSizeComponents() {
        makeRequestLabel("What size is your new Clothing? (Please ensure it corresponds with our "
                + "sizing chart)", 3);
        makeAttributeLabel("Size:", 3);
        sizeTextField = makeAttributeTextField(3);
        makeSizingChart();
    }

    private void makeColorComponents() {
        makeRequestLabel("What is the color of your Clothing? (Describe or type a color)",
                2);
        makeAttributeLabel("Color:", 2);
        colorTextField = makeAttributeTextField(2);
    }

    private void makeTypeComponents() {
        makeRequestLabel("What type of Clothing will you be dding to your Closet?",
                1);
        makeAttributeLabel("Type:", 1);
        makeTypeComboBox();
    }

    private void makeNameComponents() {
        makeRequestLabel("Please give your new Clothing a name!", 0);
        makeAttributeLabel("Name:", 0);
        nameTextField = makeAttributeTextField(0);
    }

    private void makeSizingChart() {
        JLabel shirtSizingChart = new JLabel("Shirt Sizes: "
                + "0.0 = xSmall, 1.0 = small, 2.0 = medium, 3.0 = large, 4.0 = xLarge");
        shirtSizingChart.setBounds(30, 300, 800, 30);
        shirtSizingChart.setFont(REQUEST_FONT);

        JLabel pantsSizingChart = new JLabel("Pants Sizes: waist length in inches (e.g. 32.0 or 27.5)");
        pantsSizingChart.setBounds(30, 330, 800, 30);
        pantsSizingChart.setFont(REQUEST_FONT);

        JLabel shoesSizingChart = new JLabel("Shoe sizes: US Sizing (e.g. 9.0 or 5.5)");
        shoesSizingChart.setBounds(30, 360, 800, 30);
        shoesSizingChart.setFont(REQUEST_FONT);

        JLabel socksSizingChart = new JLabel("Sock Sizes: 0.0 = xSmall, 1.0 = small, 2.0 = medium,"
                + " 3.0 = large, 4.0 = xLarge");
        socksSizingChart.setBounds(30, 390, 800, 30);
        socksSizingChart.setFont(REQUEST_FONT);

        JLabel accessoriesSizingChart = new JLabel("Accessories are not sized -> please input 1.0");
        accessoriesSizingChart.setBounds(30, 420, 800, 30);
        accessoriesSizingChart.setFont(REQUEST_FONT);

        this.getPanel().add(shirtSizingChart);
        this.getPanel().add(pantsSizingChart);
        this.getPanel().add(shoesSizingChart);
        this.getPanel().add(socksSizingChart);
        this.getPanel().add(accessoriesSizingChart);
    }

    private void makeTypeComboBox() {
        typeComboBox = new JComboBox<>(TYPES);
        typeComboBox.setBounds(90, 154, 400, 25);
        this.getPanel().add(typeComboBox);
    }

    private void makeRequestLabel(String request, int incrementScale) {
        JLabel theLabel = new JLabel(request);
        theLabel.setFont(REQUEST_FONT);
        theLabel.setBounds(30, 60 + incrementScale * COMPONENT_DISTANCE_INCREMENT, 800, 30);
        this.getPanel().add(theLabel);

    }

    private void makeAttributeLabel(String attributeWithColon, int incrementScale) {
        JLabel theLabel = new JLabel(attributeWithColon);
        theLabel.setFont(LABEL_FONT);
        theLabel.setBounds(30, 90 + incrementScale * COMPONENT_DISTANCE_INCREMENT, 100, 30);
        this.getPanel().add(theLabel);
    }

    private JTextField makeAttributeTextField(int incrementScale) {
        JTextField theTextField = new JTextField("", 10);
        theTextField.setBounds(90, 94 + incrementScale * COMPONENT_DISTANCE_INCREMENT, 400, 25);
        this.getPanel().add(theTextField);
        return theTextField;
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }
}
