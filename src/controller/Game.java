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
    private static int songIndex = 0;
    private AudioPlayer player;

    public Game(Screen screen)   {
        this.screen = screen;
        this.songNames = new ArrayList<String>();
        try {
            this.player = new AudioPlayer();
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
    public String enterMenu() {
        String songName = songNames.get(songIndex);
        player.playSounds(songName);
        return songName;
    }

    // todo: encapsulate common behaviors
    public String previousSong()  {
        songIndex--;
        songIndex = (songIndex + songNames.size()) % songNames.size();
        String songName = songNames.get(songIndex);
        player.stopSounds();
        player.playSounds(songName);
        return songName;
    }
    public String nextSong()  {
        songIndex = (songIndex + 1) % songNames.size();
        String songName = songNames.get(songIndex);
        player.stopSounds();
        player.playSounds(songName);
        return songName;
    }
}
