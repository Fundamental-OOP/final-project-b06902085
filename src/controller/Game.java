package controller;

import model.Screen;
import model.Sprite;
import track.*;
import note.*;
import pause.Pause;
import views.GameView;
import menu.Intro;
import media.AudioPlayer;
import Effect.Grade;

import javax.sound.sampled.LineUnavailableException;

import Effect.Grade;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.ArrayList;

import static FileHandler.FileHandler.addFileByFilePath;
import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;


public class Game extends GameLoop {
    private final Screen screen;
    private Intro intro;
    public  ArrayList<String> songNames;
    private ArrayList<String> buttonNames = new ArrayList<String>(Arrays.asList("D", "F", "J", "K"));
    private ArrayList<TrackButton> trackButtons = new ArrayList<TrackButton>();
    private ArrayList<Track> tracks = new ArrayList<Track>();
    private ArrayList<Border> borders = new ArrayList<Border>();
    private List<Sprite> currentSprites = new ArrayList<Sprite>();
    private NoteDatabase db = null;
    private static int songIndex;
    private AudioPlayer musicPlayer;
    private static int cummulativeScore = 0;
    private static int currentCombo = 0;
    private static String finalRank;
    private NumberSprite comboSprite;
    private Pause pausePage;
    public static boolean threadSuspended = false;
    private Grade grader;
    

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
        grader.setGradeStatus(hitStatus);
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
            this.screen.removeSprite(this.comboSprite);
            this.comboSprite = new NumberSprite(new Point(GameView.WIDTH / 2 - 30, GameView.HEIGHT / 2 - 100), currentCombo);
            this.screen.addSprite(this.comboSprite);
            db.removeNote(T_NUM);
        }
    }

    public void play(Object name){
        this.db = new NoteDatabase(this,screen,startpos,borderWidth);
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
        this.grader = new Grade(new Point(0,0));
        screen.addSprite(grader);
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
        if (cummulativeScore >= 900000)    {
            finalRank = "S";
        }
        else if (cummulativeScore > 800000) {
            finalRank = "A";
        }
        else if (cummulativeScore > 700000) {
            finalRank = "B";
        }
        else if (cummulativeScore > 600000) {
            finalRank = "C";
        }
        else    {
            finalRank = "F";
        }
        AudioPlayer soundEffectPlayer = null;
        try {
            soundEffectPlayer = new AudioPlayer();
        }
        catch (LineUnavailableException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        soundEffectPlayer.playSounds("ENDING", false);
    }

    public String previousSong()  {
        clickSoundEffect();
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

    public String currentSong(boolean newGame) {
        clickSoundEffect();
        String songName = songNames.get(songIndex);
        this.musicPlayer.stopSounds();
        this.musicPlayer.playSounds(songName, false);
        if (newGame){
            this.comboSprite = new NumberSprite(new Point(GameView.WIDTH / 2 - 30, GameView.HEIGHT / 2 - 100), currentCombo);
            screen.addSprite(this.comboSprite);
        }
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
        stopGame();
        result();
    }

    public String pauseGame() {
        threadSuspended = !threadSuspended;
        if(!threadSuspended) {
            screen.addSprites(currentSprites.toArray(Sprite[]::new));
            screen.removeSprite(pausePage);
            db.resumeNote();
            this.musicPlayer.resumeSound();
            return "GAME";
        } else {
            currentSprites = new ArrayList<>(screen.getSprites());
            screen.removeSprites();
            pausePage = new Pause(new Point(0, 0));
            screen.addSprite(pausePage);
            db.suspendNote();
            this.musicPlayer.pauseSound();
            return "PAUSE";
        }

    }

    public void stopGame() {
        threadSuspended = false;
        if(!currentSprites.isEmpty()) {
            currentSprites.clear();
        }
        clearScreen();
        screen.removeSprite(this.comboSprite);
        screen.removeSprites();
        if(db != null) {
            db.resumeNote();
            db.interrupt();
        }
        this.musicPlayer.stopSounds();
    }

    public void clearScreen(){
        screen.removeSprites();
    }
}
