package gui;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;


public class PlayButtonSound {
    public static void playButtonSound(String soundPath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("data/" + soundPath)
                    .getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error with playing audio sound");
            e.printStackTrace();
        }
    }
}
