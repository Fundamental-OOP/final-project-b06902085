package controller;

import model.Screen;
import track.*;
import note.*;
import views.GameView;
import menu.Intro;
import media.AudioPlayer;
import javax.sound.sampled.LineUnavailableException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.ArrayList;

import static FileHandler.FileHandler.addFileByFilePath;
import java.awt.*;
import java.io.File;
import java.util.Arrays;


public class Game extends GameLoop {
    private final Screen screen;
    private Intro intro;
    public  ArrayList<String> songNames;
    private ArrayList<String> buttonNames = new ArrayList<String>(Arrays.asList("D", "F", "J", "K"));
    private ArrayList<TrackButton> trackButtons = new ArrayList<TrackButton>();
    private ArrayList<Track> tracks = new ArrayList<Track>();
    private ArrayList<Border> borders = new ArrayList<Border>();
    private static int songIndex;
    private AudioPlayer musicPlayer;

    private final NoteDatabase db;

    int borderWidth = 10;
    int startpos = (GameView.WIDTH - 144 * 4 - 5 * borderWidth) / 2;
    
    public Game(Screen screen)   {
        this.screen = screen;
        this.intro = new Intro();
        screen.addSprite(this.intro);

        this.songNames = new ArrayList<String>();
        try {
            this.musicPlayer = new AudioPlayer();
        }
        catch (LineUnavailableException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        addSong();
        this.db = new NoteDatabase(screen,startpos,borderWidth);  
    }

    public void clickTrack(int T_NUM) {
        Track track = getTrack(T_NUM);
        track.click();
        if(!track.checkHit(db.getNote(T_NUM)).equals("NULL")){
            db.removeNote(T_NUM);
        }
    }

    public void play(Object name){
        addFileByFilePath(NoteDatabase.SHEET1, new File("assets/song/reflect/sheet.out"));

        for(int i = 0;i < 4;i++) {
            tracks.add(new track.Track(0, new Point(startpos + 154 * i + borderWidth, 0)));
            screen.addSprite(tracks.get(i));
        }

        for(int i = 0;i < 5;i++) {
            borders.add(new Border(new Point(startpos + 154 * i, 0), new Color(0,0,0), (float) 0.6, borderWidth, GameView.HEIGHT));
            screen.addSprite(borders.get(i));
        }
        
        for (int i = 0; i < 4; i++) {
            trackButtons.add(new TrackButton(new Point(startpos + 154 * i + borderWidth, 725), buttonNames.get(i), 145, 145));
            screen.addSprite(trackButtons.get(i));
        }

        db.play(name);  
    }

    public void releaseTrack(int T_NUM) {
        getTrack(T_NUM).release();        
    }

    private void addSong(){
        songNames.add("REFLECT");
        songNames.add("COUNTRY_ROADS");
    }

    private Track getTrack(int T_NUM){
        switch(T_NUM) {
            case 0:
                return tracks.get(0);
            case 1:
                return tracks.get(1);
            case 2:
                return tracks.get(2);
            case 3:
                return tracks.get(3);
        }
        return null;
    }
    
    @Override
    protected Screen getScreen() {
        return screen;
    }
    public void titleMusic() {
        this.musicPlayer.playSounds("TITLE");
    }
    

    // todo: encapsulate common behaviors
    public String enterMenu()   {
        this.screen.removeSprite(this.intro);
        AudioPlayer soundEffectPlayer = null;
        try {
            soundEffectPlayer = new AudioPlayer();
        }
        catch (LineUnavailableException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        soundEffectPlayer.playSounds("A");
        this.musicPlayer.stopSounds();
        songIndex = 0;
        String songName = songNames.get(songIndex);
        this.musicPlayer.playSounds(songName);
        return songName;
    }

    public String previousSong()  {
        AudioPlayer soundEffectPlayer = null;
        try {
            soundEffectPlayer = new AudioPlayer();
        }
        catch (LineUnavailableException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        soundEffectPlayer.playSounds("A");
        songIndex--;
        songIndex = (songIndex + songNames.size()) % songNames.size();
        String songName = songNames.get(songIndex);
        this.musicPlayer.stopSounds();
        this.musicPlayer.playSounds(songName);
        return songName;
    }
    public String nextSong()  {
        AudioPlayer soundEffectPlayer = null;
        try {
            soundEffectPlayer = new AudioPlayer();
        }
        catch (LineUnavailableException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        soundEffectPlayer.playSounds("A");
        songIndex = (songIndex + 1) % songNames.size();
        String songName = songNames.get(songIndex);
        this.musicPlayer.stopSounds();
        this.musicPlayer.playSounds(songName);
        return songName;
    }

    public String currentSong() {
        String songName = songNames.get(songIndex);
        this.musicPlayer.stopSounds();
        this.musicPlayer.playSounds(songName);
        return songName;
    }

}
