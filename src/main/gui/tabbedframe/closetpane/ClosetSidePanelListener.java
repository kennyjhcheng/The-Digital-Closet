package gui.tabbedframe.closetpane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Action Listener for side panel which allows users to select which Closet option they would like to use
public class ClosetSidePanelListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(ClosetPane.addClothingButton)) {
            ClosetPane.addClothingPanel.getPanel().setVisible(true);
            ClosetPane.removeClothingPanel.getPanel().setVisible(false);
            ClosetPane.viewClothingPanel.getPanel().setVisible(false);
            ClosetPane.editClothingPanel.getPanel().setVisible(false);

        } else if (e.getSource().equals(ClosetPane.removeClothingButton)) {
            ClosetPane.addClothingPanel.getPanel().setVisible(false);
            ClosetPane.removeClothingPanel.getPanel().setVisible(true);
            ClosetPane.viewClothingPanel.getPanel().setVisible(false);
            ClosetPane.editClothingPanel.getPanel().setVisible(false);

        } else if (e.getSource().equals(ClosetPane.viewClothingButton)) {
            ClosetPane.addClothingPanel.getPanel().setVisible(false);
            ClosetPane.removeClothingPanel.getPanel().setVisible(false);
            ClosetPane.viewClothingPanel.getPanel().setVisible(true);
            ClosetPane.editClothingPanel.getPanel().setVisible(false);

        } else if (e.getSource().equals(ClosetPane.editClothingButton)) {
            ClosetPane.addClothingPanel.getPanel().setVisible(false);
            ClosetPane.removeClothingPanel.getPanel().setVisible(false);
            ClosetPane.viewClothingPanel.getPanel().setVisible(false);
            ClosetPane.editClothingPanel.getPanel().setVisible(true);

        }
    }
}
