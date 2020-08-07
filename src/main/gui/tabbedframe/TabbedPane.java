package gui.tabbedframe;

import gui.tabbedframe.closetpane.ClosetPane;

import javax.swing.*;
import java.awt.*;

public class TabbedPane extends JFrame {
    public static final int TABBED_PANE_WIDTH = 1000;
    public static final int TABBED_PANE_HEIGHT = 666;

    public static JFrame tabbedPaneFrame;

    SaveAndQuitPane exitPane = new SaveAndQuitPane();
    StyleBoardPane styleBoardPane = new StyleBoardPane();
    ClosetPane closetPane = new ClosetPane();

    JTabbedPane tabbedPane = new JTabbedPane();

    public static void main(String[] args) {
        TabbedPane tp = new TabbedPane();
    }

    public TabbedPane() {
        makeFrame();
        makeTabbedPane();

        tabbedPaneFrame.setVisible(true);

    }

    private void makeFrame() {
        tabbedPaneFrame = new JFrame();
        tabbedPaneFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tabbedPaneFrame.setSize(TABBED_PANE_WIDTH, TABBED_PANE_HEIGHT);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        int frameXPos = (dim.width / 2) - (tabbedPaneFrame.getWidth() / 2);
        int frameYPos = (dim.height / 2) - (tabbedPaneFrame.getHeight() / 2);
        tabbedPaneFrame.setLocation(frameXPos, frameYPos);
        tabbedPaneFrame.setResizable(false);
    }

    private void makeTabbedPane() {
        tabbedPane.add("Closet           ", closetPane.getClosetPanel());
        tabbedPane.add("StyleBoard       ", styleBoardPane.getStyleBoardPanel());
        tabbedPane.add("Save and Quit    ", exitPane.getExitPanel());
        tabbedPaneFrame.add(tabbedPane);
    }
}
