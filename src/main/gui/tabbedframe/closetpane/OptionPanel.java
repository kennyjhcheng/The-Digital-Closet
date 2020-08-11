package gui.tabbedframe.closetpane;

import javax.swing.*;

// An interface forcing all subclasses to implement the getPanel and makeFormTitleLabel methods
public interface OptionPanel {

    JPanel getPanel();

    void makeFormTitleLabel();
}
