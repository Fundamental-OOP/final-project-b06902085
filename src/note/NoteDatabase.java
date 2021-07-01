package note;

import model.Screen;
import FileHandler.FileHandler;
import controller.Game;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class NoteDatabase extends Thread {
    
    public static final String SHEET1 = "REFLECT";
    public static final int maxCombo = 678;
    private final Screen screen;
    private int LineSize;
    private final int startpos;
    private final int borderWidth;
    private final int trackSize = 4;
    private final List<List<Integer>> NoteIDList;
    private final List<List<Note>> NoteList;
    private final Game game;

    private int bpn = 0;

    public void run() {
        for(int i = 0;i < LineSize;i++){
            if(NoteIDList.get(0).get(i) == 1){
                Note newNote = new Note(new Point(startpos + borderWidth, 0),this,0);
                NoteList.get(0).add(newNote);
                screen.addSprite(newNote);
            }
            
            if(NoteIDList.get(1).get(i) == 1){
                Note newNote = new Note(new Point(startpos + 154 * 1 + borderWidth, 0),this,1);
                NoteList.get(1).add(newNote);
                screen.addSprite(newNote);
            }
            
            if(NoteIDList.get(2).get(i) == 1){
                Note newNote = new Note(new Point(startpos +154 * 2 + borderWidth, 0),this,2);
                NoteList.get(2).add(newNote);
                screen.addSprite(newNote);
            }
            
            if(NoteIDList.get(3).get(i) == 1){
                Note newNote = new Note(new Point(startpos + 154 * 3 + borderWidth, 0),this,3);
                NoteList.get(3).add(newNote);
                screen.addSprite(newNote);
            }

            try{
                Thread.sleep(60000/(bpn*4));
            } catch(InterruptedException e) {
                System.out.println("Thread Execution stopped unexpectedly");
                return;
            }

        }
        game.finishGame();
    }

    public NoteDatabase(Game game, Screen screen,int startpos,int borderWidth){
        this.screen = screen;
        this.NoteIDList = new ArrayList<List<Integer>>(trackSize);
        this.NoteList = new ArrayList<List<Note>>(trackSize);
        for(int i = 0;i < trackSize;i++) {
            NoteIDList.add(new ArrayList<Integer>());
            NoteList.add(new ArrayList<Note>());
        }
        this.startpos = startpos;
        this.borderWidth = borderWidth;
        this.game = game;
    }

    public void play(Object FileName) {
        bpn = FileHandler.process(FileName,NoteIDList);
        LineSize = NoteIDList.get(0).size();
        start();
    }

    public Note getNote(int T_NUM) {
        return (NoteList.get(T_NUM).isEmpty())?null:NoteList.get(T_NUM).get(0);
    }

    public int getMaxCombo()    {
        return maxCombo;
    }

    public void removeNote(int T_NUM) {
        if(!NoteList.get(T_NUM).isEmpty()) {
            screen.removeSprite(NoteList.get(T_NUM).get(0));
            NoteList.get(T_NUM).remove(0);
        }
    }
}
