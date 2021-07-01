package controller;

import model.Screen;
import track.Track;
import note.NoteDatabase;
import views.GameView;


public class Game extends GameLoop {
    private final Screen screen;
    private final Track t1, t2, t3, t4;
    private final NoteDatabase db;

    int borderWidth = 10;
    int startpos = (GameView.WIDTH - 144 * 4 - 5 * borderWidth) / 2;
    
    public Game(Screen screen, Track t1, Track t2, Track t3, Track t4)   {
        this.screen = screen;
	    this.t1 = t1;
	    this.t2 = t2;
	    this.t3 = t3;
	    this.t4 = t4;
        this.db = new NoteDatabase(screen,startpos,borderWidth);
        db.play(NoteDatabase.SHEET1);
    }

    public void clickTrack(int T_NUM) {
        Track track = getTrack(T_NUM);
        track.click();
        if(!track.checkHit(db.getNote(T_NUM)).equals("NULL")){
            db.removeNote(T_NUM);
        }
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
