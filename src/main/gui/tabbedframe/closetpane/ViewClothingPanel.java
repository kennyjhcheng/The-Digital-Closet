package gui.tabbedframe.closetpane;


import gui.mainmenu.MainMenu;
import model.Closet;
import model.Clothing;

import javax.swing.*;

import java.awt.*;

public class ViewClothingPanel extends OptionPanelConstructor {
    JTabbedPane viewClothingTabbedPane;

    JSplitPane allSplitPane;
    JSplitPane shirtSplitPane;
    JSplitPane pantsSplitPane;
    JSplitPane shoesSplitPane;
    JSplitPane socksSplitPane;
    JSplitPane accessoriesSplitPane;

    JList<Clothing> allJList;
    JList<Clothing> shirtJList;
    JList<Clothing> pantsJList;
    JList<Clothing> socksJList;
    JList<Clothing> shoesJList;
    JList<Clothing> accessoriesJList;

    public static DefaultListModel<Clothing> allModel;
    public static DefaultListModel<Clothing> shirtModel;
    public static DefaultListModel<Clothing> pantsModel;
    public static DefaultListModel<Clothing> shoesModel;
    public static DefaultListModel<Clothing> socksModel;
    public static DefaultListModel<Clothing> accessoriesModel;

    JPanel allPanel;
    JPanel shirtPanel;
    JPanel shoePanel;
    JPanel pantsPanel;
    JPanel socksPanel;
    JPanel accessoriesPanel;

    JLabel allNameLabel = new JLabel();
    JLabel allTypeLabel = new JLabel();
    JLabel allColorLabel = new JLabel();
    JLabel allSizeLabel = new JLabel();

    JLabel shirtNameLabel = new JLabel();
    JLabel shirtTypeLabel = new JLabel();
    JLabel shirtColorLabel = new JLabel();
    JLabel shirtSizeLabel = new JLabel();

    JLabel pantsNameLabel = new JLabel();
    JLabel pantsTypeLabel = new JLabel();
    JLabel pantsColorLabel = new JLabel();
    JLabel pantsSizeLabel = new JLabel();

    JLabel shoesNameLabel = new JLabel();
    JLabel shoesTypeLabel = new JLabel();
    JLabel shoesColorLabel = new JLabel();
    JLabel shoesSizeLabel = new JLabel();

    JLabel socksNameLabel = new JLabel();
    JLabel socksTypeLabel = new JLabel();
    JLabel socksColorLabel = new JLabel();
    JLabel socksSizeLabel = new JLabel();

    JLabel accessoriesNameLabel = new JLabel();
    JLabel accessoriesTypeLabel = new JLabel();
    JLabel accessoriesColorLabel = new JLabel();
    JLabel accessoriesSizeLabel = new JLabel();

    public ViewClothingPanel() {
        super();
        this.getPanel().setBackground(Color.LIGHT_GRAY);
        makeFormTitleLabel();
        formTitle.setText("View Clothing");

        initializeFieldsForAll();
        initializeFieldsForShirt();
        initializeFieldsForPants();
        initializeFieldsForShoes();
        initializeFieldsForSocks();
        initializeFieldsForAccessories();

        allSplitFrame();
        typeSplitFrame(shirtNameLabel, shirtTypeLabel, shirtColorLabel, shirtSizeLabel, shirtModel, shirtPanel,
                shirtSplitPane, shirtJList, "shirt");
        typeSplitFrame(pantsNameLabel, pantsTypeLabel, pantsColorLabel, pantsSizeLabel, pantsModel, pantsPanel,
                pantsSplitPane, pantsJList, "pants");
        typeSplitFrame(shoesNameLabel, shoesTypeLabel, shoesColorLabel, shoesSizeLabel, shoesModel, shoePanel,
                shoesSplitPane, shoesJList, "shoes");
        typeSplitFrame(socksNameLabel, socksTypeLabel, socksColorLabel, socksSizeLabel, socksModel, socksPanel,
                socksSplitPane, socksJList, "socks");
        typeSplitFrame(accessoriesNameLabel, accessoriesTypeLabel, accessoriesColorLabel, accessoriesSizeLabel,
                accessoriesModel, accessoriesPanel, accessoriesSplitPane, accessoriesJList, "accessories");

        makeViewClothingTabbedPane();
    }

    private void initializeFieldsForAccessories() {
        accessoriesNameLabel = new JLabel();
        accessoriesTypeLabel = new JLabel();
        accessoriesColorLabel = new JLabel();
        accessoriesSizeLabel = new JLabel();
        accessoriesModel = new DefaultListModel<>();
        accessoriesPanel = new JPanel();
        accessoriesSplitPane = new JSplitPane();
        accessoriesJList = new JList<>();
    }

    private void initializeFieldsForSocks() {
        socksNameLabel = new JLabel();
        socksTypeLabel = new JLabel();
        socksNameLabel = new JLabel();
        socksSizeLabel = new JLabel();
        socksModel = new DefaultListModel<>();
        socksPanel = new JPanel();
        socksSplitPane = new JSplitPane();
        socksJList = new JList<>();
    }

    private void initializeFieldsForShoes() {
        shoesNameLabel = new JLabel();
        shoesTypeLabel = new JLabel();
        shoesColorLabel = new JLabel();
        shoesSizeLabel = new JLabel();
        shoesModel = new DefaultListModel<>();
        shoePanel = new JPanel();
        shoesSplitPane = new JSplitPane();
        shoesJList = new JList<>();
    }

    private void initializeFieldsForPants() {
        pantsNameLabel = new JLabel();
        pantsTypeLabel = new JLabel();
        pantsColorLabel = new JLabel();
        pantsSizeLabel = new JLabel();
        pantsModel = new DefaultListModel<>();
        pantsPanel = new JPanel();
        pantsSplitPane = new JSplitPane();
        pantsJList = new JList<>();
    }

    private void initializeFieldsForShirt() {
        shirtNameLabel = new JLabel();
        shirtTypeLabel = new JLabel();
        shirtColorLabel = new JLabel();
        shirtSizeLabel = new JLabel();
        shirtModel = new DefaultListModel<>();
        shirtPanel = new JPanel();
        shirtSplitPane = new JSplitPane();
        shirtJList = new JList<>();
    }

    private void initializeFieldsForAll() {
        allNameLabel = new JLabel();
        allTypeLabel = new JLabel();
        allColorLabel = new JLabel();
        allSizeLabel = new JLabel();
        allModel = new DefaultListModel<>();
        allPanel = new JPanel();
        allSplitPane = new JSplitPane();
        allJList = new JList<>();
    }

    private void allSplitFrame() {
        allNameLabel = new JLabel();
        allTypeLabel = new JLabel();
        allColorLabel = new JLabel();
        allSizeLabel = new JLabel();
        allModel = new DefaultListModel<>();
        allPanel = new JPanel();
        allSplitPane = new JSplitPane();
        allJList = new JList<>();

        addToModel(MainMenu.myCloset, allModel, allJList);
        createListenerForJList(allJList, allNameLabel, allTypeLabel, allColorLabel, allSizeLabel);
        addComponentsToPane(allPanel, allNameLabel, allTypeLabel, allColorLabel, allSizeLabel);
        makeSplitPane(allJList, allPanel, allSplitPane);
    }

    private void typeSplitFrame(JLabel nameLabel, JLabel typeLabel, JLabel colorLabel, JLabel sizeLabel,
                                 DefaultListModel<Clothing> typeModel, JPanel typePanel, JSplitPane typeSplitPane,
                                 JList<Clothing> typeJList, String type) {

        try {
            addToModel(MainMenu.myCloset.getClosetByType(type), typeModel, typeJList);
            createListenerForJList(typeJList, nameLabel, sizeLabel, colorLabel, sizeLabel);
            addComponentsToPane(typePanel, nameLabel, sizeLabel, colorLabel, sizeLabel);
            makeSplitPane(typeJList, typePanel, typeSplitPane);
        } catch (Exception exception) {
            ;
        }
    }

    private void makeViewClothingTabbedPane() {
        viewClothingTabbedPane = new JTabbedPane();
        viewClothingTabbedPane.setBounds(50, 80, 700, 450);
        viewClothingTabbedPane.add("All        ", allSplitPane);
        viewClothingTabbedPane.add("Shirts     ", shirtSplitPane);
        viewClothingTabbedPane.add("Pants      ", pantsSplitPane);
        viewClothingTabbedPane.add("Shoes      ", shoesSplitPane);
        viewClothingTabbedPane.add("Socks      ", socksSplitPane);
        viewClothingTabbedPane.add("Accessories", accessoriesSplitPane);
        this.getPanel().add(viewClothingTabbedPane);
    }

    private void makeSplitPane(JList<Clothing> typeJList, JPanel typePanel, JSplitPane typeSplitPane) {
        typeSplitPane.setLeftComponent(new JScrollPane(typeJList));
        typeSplitPane.setRightComponent(typePanel);
    }

    private void addComponentsToPane(JPanel typePanel, JLabel nameLabel, JLabel typeLabel,
                                     JLabel colorLabel, JLabel sizeLabel) {
        typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.PAGE_AXIS));
        typePanel.add(nameLabel);
        typePanel.add(typeLabel);
        typePanel.add(colorLabel);
        typePanel.add(sizeLabel);
    }

    private void createListenerForJList(JList<Clothing> listType, JLabel nameLabel, JLabel typeLabel,
                                        JLabel colorLabel, JLabel sizeLabel) {
        listType.getSelectionModel().addListSelectionListener(e -> {
            Clothing c = listType.getSelectedValue();
            if (c != null) {
                nameLabel.setText("Name: " + c.getName());
                typeLabel.setText("Type: " + c.getType());
                colorLabel.setText("Color: " + c.getColor());
                sizeLabel.setText("Size: " + c.getSize());
            }
        });
    }

    private void addToModel(Closet closetByType, DefaultListModel<Clothing> modelType, JList<Clothing> typeJList) {
        typeJList.setModel(modelType);
        for (Clothing c : closetByType.getClothes()) {
            modelType.addElement(c);
        }
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }
}
