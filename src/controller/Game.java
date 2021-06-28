package controller;

import model.Screen;
import media.AudioPlayer;

public class Game extends GameLoop  {
    private final Screen screen;

    public Game(Screen screen)   {
        this.screen = screen;
    }
    @Override
    protected Screen getScreen() {
        return screen;
    }
    public void enterMenu() {
        System.out.println("enter menu");
        AudioPlayer.playSounds("Reflect");
    }

    // TODOs
    public void previousSong()  {
        System.out.println("previous song");
    }
    public void nextSong()  {
        System.out.println("next song");
    }
}
