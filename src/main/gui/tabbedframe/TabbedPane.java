package gui.tabbedframe;

import gui.tabbedframe.closet.ClosetPane;

import javax.swing.*;
import java.awt.*;

public class TabbedPane extends JFrame {
    public static final int TABBED_PANE_WIDTH = 1000;
    public static final int TABBED_PANE_HEIGHT = 666;

    ExitPane exitPane = new ExitPane();
    StyleBoardPane styleBoardPane = new StyleBoardPane();
    ClosetPane closetPane = new ClosetPane();

    JTabbedPane tabbedPane = new JTabbedPane();

    public static void main(String[] args) {
        TabbedPane tp = new TabbedPane();
    }

    public TabbedPane() {
        makeFrame();
        makeTabbedPane();

        this.setVisible(true);

    }

    private void makeFrame() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(TABBED_PANE_WIDTH, TABBED_PANE_HEIGHT);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        int frameXPos = (dim.width / 2) - (this.getWidth() / 2);
        int frameYPos = (dim.height / 2) - (this.getHeight() / 2);
        this.setLocation(frameXPos, frameYPos);
        this.setResizable(false);
    }

    private void makeTabbedPane() {
        tabbedPane.add("Closet           ", closetPane.getClosetPanel());
        tabbedPane.add("StyleBoard       ", styleBoardPane.getStyleBoardPanel());
        tabbedPane.add("Save and Quit    ", exitPane.getExitPanel());
        this.add(tabbedPane);
    }
}
