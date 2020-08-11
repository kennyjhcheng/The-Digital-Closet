package gui;

import gui.mainmenu.MainMenu;

// Runs the GUI
public class Main {


    public static void main(String[] args) {
        PlayButtonSound.playSound("startup_music.wav");
        new MainMenu();
    }
}
