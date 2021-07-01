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
    private NoteDatabase db = null;
    private static int songIndex;
    private AudioPlayer musicPlayer;
    public static int cummulativeScore = 0;
    public static int currentCombo = 0;

    

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
    }

    public void clickTrack(int T_NUM) {
        Track track = getTrack(T_NUM);
        track.click();
        String hitStatus = track.checkHit(db.getNote(T_NUM));
        if(!hitStatus.equals("NULL")){
            if (!hitStatus.equals("MISS"))    {
                int maxCombo = db.getMaxCombo();
                cummulativeScore += 100000 / (maxCombo*(maxCombo - 1) / 2) * currentCombo;
                cummulativeScore += 900000 / maxCombo * (hitStatus.equals("PERFECT")? 1 : 0.5);
                currentCombo++;
            }
            else    {
                currentCombo = 0;
            }
            System.out.printf("combo = %d, score = %d\n", currentCombo, cummulativeScore);
            db.removeNote(T_NUM);
        }
    }

    public void play(Object name){
        db = new NoteDatabase(this,screen,startpos,borderWidth);
        addFileByFilePath(NoteDatabase.SHEET1, new File("assets/song/reflect/sheet.out"));
        addFileByFilePath(NoteDatabase.SHEET2, new File("assets/song/country_road/example.out"));
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
        this.musicPlayer.playSounds("TITLE",true);
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
        soundEffectPlayer.playSounds("A", false);
        this.musicPlayer.stopSounds();
        songIndex = 0;
        String songName = songNames.get(songIndex);
        this.musicPlayer.playSounds(songName, true);
        return songName;
    }

    public void result() {
       //Failed to implement
    }
    public String previousSong()  {
        AudioPlayer soundEffectPlayer = null;
        try {
            soundEffectPlayer = new AudioPlayer();
        }
        catch (LineUnavailableException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        soundEffectPlayer.playSounds("A", false);
        songIndex--;
        songIndex = (songIndex + songNames.size()) % songNames.size();
        String songName = songNames.get(songIndex);
        this.musicPlayer.stopSounds();
        this.musicPlayer.playSounds(songName, true);
        return songName;
    }
    public String nextSong()  {
        clickSoundEffect();
        songIndex = (songIndex + 1) % songNames.size();
        String songName = songNames.get(songIndex);
        this.musicPlayer.stopSounds();
        this.musicPlayer.playSounds(songName, true);
        return songName;
    }

    public String currentSong() {
        clickSoundEffect();
        String songName = songNames.get(songIndex);
        this.musicPlayer.stopSounds();
        this.musicPlayer.playSounds(songName, false);
        return songName;
    }
    public void clickSoundEffect()  {
        AudioPlayer soundEffectPlayer = null;
        try {
            soundEffectPlayer = new AudioPlayer();
        }
        catch (LineUnavailableException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        soundEffectPlayer.playSounds("A", false);
    }

    public void finishGame() {
        GameView.state = "ENDING";
        screen.removeSprites();
        if(db != null) {
            db.interrupt();
        }
        this.musicPlayer.stopSounds();
    }

}
