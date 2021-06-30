package note;

import model.Screen;
import FileHandler.FileHandler;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class GeneratorNote extends Thread {
    
    public static final String SHEET1 = "example";
    private final Screen screen;
    private int LineSize;
    private final int startpos;
    private final int borderWidth;
    private final List<Integer> NoteT1;
    private final List<Integer> NoteT2;
    private final List<Integer> NoteT3;
    private final List<Integer> NoteT4;


    public void run() {
        for(int i = 0;i < LineSize;i++){
            if(NoteT1.get(i) == 1){
                screen.addSprite(new Note(new Point(startpos + borderWidth, 0)));
            }
            
            if(NoteT2.get(i) == 1){
                screen.addSprite(new Note(new Point(startpos + 154 * 1 + borderWidth, 0)));
            }
            
            if(NoteT3.get(i) == 1){
                screen.addSprite(new Note(new Point(startpos +154 * 2 + borderWidth, 0)));
            }
            
            if(NoteT4.get(i) == 1){
                screen.addSprite(new Note(new Point(startpos + 154 * 3 + borderWidth, 0)));
            }

            try{
                Thread.sleep(2000);
            } catch(InterruptedException e) {
                System.out.println("Thread Execution stopped unexpectedly");
                return;
            }
        }
    }

    public GeneratorNote(Screen screen,int startpos,int borderWidth){
        this.screen = screen;
        this.NoteT1 = new ArrayList<>();
        this.NoteT2 = new ArrayList<>();
        this.NoteT3 = new ArrayList<>();
        this.NoteT4 = new ArrayList<>();
        this.startpos = startpos;
        this.borderWidth = borderWidth;
    }

    public void play(Object FileName) {
        FileHandler.process(FileName,NoteT1,NoteT2,NoteT3,NoteT4);
        LineSize = NoteT1.size();
        start();
    }

}
