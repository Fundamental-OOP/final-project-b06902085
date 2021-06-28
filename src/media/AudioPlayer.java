package media;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AudioPlayer {
    private Map<Object, File> sounds = new HashMap<>();
    private Clip clip;

    public AudioPlayer() throws LineUnavailableException    {
        try {
            this.clip = AudioSystem.getClip();
        }
        catch (LineUnavailableException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        addAudioByFilePath("REFLECT", new File("assets/audio/reflect.wav"));
        addAudioByFilePath("COUNTRY_ROADS", new File("assets/audio/country_roads.wav"));
    }

    public void addAudioByFilePath(String audioName, File file) {
        this.sounds.put(audioName, file);
    }

    public void addAudioByFilePath(String audioName, String path) {
        this.sounds.put(audioName, Paths.get(path).toFile());
    }

    public void playSounds(Object audioName) {
        try {
            this.clip.open(AudioSystem.getAudioInputStream(sounds.get(audioName)));
            this.clip.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void stopSounds() {
        this.clip.stop();
        this.clip.close();
    }

}
