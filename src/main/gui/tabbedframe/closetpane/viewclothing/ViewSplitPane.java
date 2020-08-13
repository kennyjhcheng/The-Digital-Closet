package gui.tabbedframe.closetpane.viewclothing;

import model.Closet;
import model.Clothing;

import javax.swing.*;

public class ViewSplitPane extends JSplitPane {
    JLabel nameLabel;
    JLabel typeLabel;
    JLabel colorLabel;
    JLabel sizeLabel;

    JPanel viewAttributesPanel;

    DefaultListModel<Clothing> typeModel = new DefaultListModel<Clothing>();

    JList<Clothing> typeList = new JList<>();


    public ViewSplitPane() {
        this.nameLabel = new JLabel();
        this.typeLabel = new JLabel();
        this.colorLabel = new JLabel();
        this.sizeLabel = new JLabel();

        this.viewAttributesPanel = new JPanel();

        viewAttributesPanel.setLayout(new BoxLayout(viewAttributesPanel, BoxLayout.PAGE_AXIS));
        viewAttributesPanel.add(nameLabel);
        viewAttributesPanel.add(typeLabel);
        viewAttributesPanel.add(colorLabel);
        viewAttributesPanel.add(sizeLabel);

        createJListListener();

        this.setLeftComponent(new JScrollPane(typeList));
        this.setRightComponent(viewAttributesPanel);
    }


    public void setCloset(Closet closetByType) {
        this.typeList.setModel(typeModel);
        for (Clothing c : closetByType.getClothes()) {
            typeModel.addElement(c);
        }
    }

    private void createJListListener() {
        typeList.getSelectionModel().addListSelectionListener(e -> {
            Clothing c = typeList.getSelectedValue();
            if (c != null) {
                nameLabel.setText("Name: " + c.getName());
                typeLabel.setText("Type: " + c.getType());
                colorLabel.setText("Color: " + c.getColor());
                sizeLabel.setText("Size: " + c.getSize());
            }
        });
    }

    public DefaultListModel<Clothing> getTypeModel() {
        return typeModel;
    }
}
