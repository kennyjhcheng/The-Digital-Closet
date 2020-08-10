package gui;

import gui.mainmenu.MainMenu;

public class Main {


    public static void main(String[] args) {
        PlayButtonSound.playButtonSound("startup_music.wav");
        new MainMenu();
    }
}
