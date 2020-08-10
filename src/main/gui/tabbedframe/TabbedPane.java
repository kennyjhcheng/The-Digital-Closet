package gui.tabbedframe;

import gui.PlayButtonSound;
import gui.tabbedframe.closetpane.ClosetPane;
import gui.tabbedframe.saveandquitpane.SaveAndQuitPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
        tabbedPaneFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        tabbedPaneFrame.setSize(TABBED_PANE_WIDTH, TABBED_PANE_HEIGHT);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        int frameXPos = (dim.width / 2) - (tabbedPaneFrame.getWidth() / 2);
        int frameYPos = (dim.height / 2) - (tabbedPaneFrame.getHeight() / 2);
        tabbedPaneFrame.setLocation(frameXPos, frameYPos);
        tabbedPaneFrame.setResizable(false);
        tabbedPaneFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });
    }

    private void makeTabbedPane() {
        tabbedPane.add("Closet           ", closetPane.getClosetPanel());
        tabbedPane.add("StyleBoard       ", styleBoardPane.getStyleBoardPanel());
        tabbedPane.add("Save and Quit    ", exitPane.getExitPanel());
        tabbedPaneFrame.add(tabbedPane);
    }

    private void exit() {
        int a = JOptionPane.showConfirmDialog(null, "Would you like to quit without saving?",
                "Quit Confirmation", JOptionPane.YES_NO_OPTION);

        if (a == JOptionPane.YES_OPTION) {
            PlayButtonSound.playButtonSound("shutdown.wav");
            tabbedPaneFrame.dispose();
        }
    }
}
