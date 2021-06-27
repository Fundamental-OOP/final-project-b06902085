package controller;

import model.Screen;

public class Game extends GameLoop  {
    private final Screen screen;
    private final Tracks[] tracks = new Tracks[4];

    public Game(Screen screen, Tracks tracks)   {
        this.screen = screen;
	this.tracks = Tracks;
    }

    public void clickTrack(int T_NUM) {
        Tracks[T_NUM].click();
    }

    public void releaseTrack(int T_NUM) {
        Tracks[T_NUM].release();
    }
    
    @Override
    protected Screen getScreen() {
        return screen;
    }
}
