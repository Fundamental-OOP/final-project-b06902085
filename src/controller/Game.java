package controller;

import model.Screen;
import media.AudioPlayer;
import javax.sound.sampled.LineUnavailableException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.ArrayList;

public class Game extends GameLoop  {
    private final Screen screen;
    private ArrayList<String> songNames;
    private static int songIndex;
    private AudioPlayer musicPlayer;

    public Game(Screen screen)   {
        this.screen = screen;
        this.songNames = new ArrayList<String>();
        try {
            this.musicPlayer = new AudioPlayer();
        }
        catch (LineUnavailableException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        songNames.add("REFLECT");
        songNames.add("COUNTRY_ROADS");
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
}
