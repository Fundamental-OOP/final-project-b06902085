package controller;

import model.Screen;
import track.Track;

public class Game extends GameLoop  {
    private final Screen screen;
    private final Track t1, t2, t3, t4;
    
    public Game(Screen screen, Track t1, Track t2, Track t3, Track t4)   {
        this.screen = screen;
	    this.t1 = t1;
	    this.t2 = t2;
	    this.t3 = t3;
	    this.t4 = t4;
    }

    public void clickTrack(int T_NUM) {
    }

    public void releaseTrack(int T_NUM) {
    }
    
    @Override
    protected Screen getScreen() {
        return screen;
    }
}
