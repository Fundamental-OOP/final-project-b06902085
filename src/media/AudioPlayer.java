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
    private static long cliptime = 0;

    public AudioPlayer() throws LineUnavailableException    {
        try {
            this.clip = AudioSystem.getClip();
        }
        catch (LineUnavailableException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        addAudioByFilePath("TITLE", new File("assets/audio/title.wav"));
        addAudioByFilePath("A", new File("assets/audio/a.wav"));
        addAudioByFilePath("ENDING", new File("assets/audio/ending.wav"));
        addAudioByFilePath("REFLECT", new File("assets/audio/reflect_mixdown.au"));
        addAudioByFilePath("COUNTRY_ROADS", new File("assets/audio/country_roads.wav"));
        addAudioByFilePath("AUDIO_CLICK1", new File("assets/audio/click1.wav"));
        addAudioByFilePath("AUDIO_CLICK2", new File("assets/audio/click2.wav"));
        addAudioByFilePath("AUDIO_CLICK3", new File("assets/audio/click3.wav"));
        addAudioByFilePath("AUDIO_CLICK4", new File("assets/audio/click4.wav"));
    }

    public void addAudioByFilePath(String audioName, File file) {
        this.sounds.put(audioName, file);
    }

    public void addAudioByFilePath(String audioName, String path) {
        this.sounds.put(audioName, Paths.get(path).toFile());
    }

    public void playSounds(Object audioName,boolean loop) {
        
        try {
            this.clip.open(AudioSystem.getAudioInputStream(sounds.get(audioName)));
            this.clip.start();
            if(loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void pauseSound() {
        cliptime = this.clip.getMicrosecondPosition();
        this.clip.stop();
    }

    public void resumeSound() {
        clip.setMicrosecondPosition(cliptime);
        clip.start();
    }

    public void stopSounds() {
        if(this.clip.isRunning()) {
            this.clip.stop();
        }
        this.clip.close();
    }

}
