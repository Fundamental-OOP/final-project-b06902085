package controller;

import model.Screen;
import track.*;
import note.*;
import views.GameView;

import media.AudioPlayer;
import javax.sound.sampled.LineUnavailableException;
import static FileHandler.FileHandler.addFileByFilePath;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.awt.*;
import java.io.File;
import java.util.Arrays;


public class Game extends GameLoop {
    private final Screen screen;

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
        try {
            this.musicPlayer = new AudioPlayer();
        }
        catch (LineUnavailableException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.songNames = new ArrayList<>();
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

    public void play(){
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

        db.play(NoteDatabase.SHEET1);  
    }

    public void releaseTrack(int T_NUM) {
        getTrack(T_NUM).release();        
    }

    private void addSong(){
        songNames.add("REFLECT");
        songNames.add("COUNTRY_ROADS");
    }

    public void titleMusic() {
        this.musicPlayer.playSounds("TITLE");
    }

    public String enterMenu()   {
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
        songIndex--;
        songIndex = (songIndex + songNames.size()) % songNames.size();
        String songName = songNames.get(songIndex);
        this.musicPlayer.stopSounds();
        this.musicPlayer.playSounds(songName);
        return songName;
    }
    public String nextSong()  {
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
}
