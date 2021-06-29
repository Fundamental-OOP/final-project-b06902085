package controller;

import model.Screen;
import track.Track;
import note.Note;
import views.GameView;

import java.awt.*;

public class Game extends GameLoop  {
    private final Screen screen;
    private final Track t1, t2, t3, t4;

    int borderWidth = 10;
    int startpos = (GameView.WIDTH - 144 * 4 - 5 * borderWidth) / 2;
    private Note note = new Note(new Point(startpos + borderWidth, 0));
    //private Note note2 = new Note(new Point(startpos + 154 + borderWidth, 0));
    
    public Game(Screen screen, Track t1, Track t2, Track t3, Track t4)   {
        this.screen = screen;
	    this.t1 = t1;
	    this.t2 = t2;
	    this.t3 = t3;
	    this.t4 = t4;
        screen.addSprite(note);
    }

    public void dropNote()  {
        note.update();
    }

    public void clickTrack(int T_NUM) {
        getTrack(T_NUM).click();
    }

    public void releaseTrack(int T_NUM) {
        getTrack(T_NUM).release();        
    }

    private Track getTrack(int T_NUM){
        switch(T_NUM) {
            case 0:
                return t1;
            case 1:
                return t2;
            case 2:
                return t3;
            case 3:
                return t4;
        }
        return null;
    }
    
    @Override
    protected Screen getScreen() {
        return screen;
    }
}
