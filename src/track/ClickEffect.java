package track;

import fsm.CyclicSequence;
import fsm.ImageState;
import media.AudioPlayer;
import fsm.FiniteStateMachine;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;

public class ClickEffect extends CyclicSequence {
   private final FiniteStateMachine fsm;
   private final Track track;
   private AudioPlayer soundEffectPlayer = null;

    public ClickEffect(Track track, FiniteStateMachine fsm, List<ImageState> states) {
       super(states);
       this.fsm = fsm;
       this.track = track;
       try {
            this.soundEffectPlayer = new AudioPlayer();
       } catch (LineUnavailableException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
       }   
    }
   
   @Override
   public void update() {
       super.update();
       if(fsm.stateChange){
            switch(track.getID()){  
                case 0:
                    soundEffectPlayer.playSounds("AUDIO_CLICK1",false);
                    break;
                case 1:
                    soundEffectPlayer.playSounds("AUDIO_CLICK2",false);
                    break;
                case 2:
                    soundEffectPlayer.playSounds("AUDIO_CLICK3",false);
                    break;
                case 3:
                    soundEffectPlayer.playSounds("AUDIO_CLICK4",false);
                    break;
            }
            fsm.stateChange = false;
        }
    }

   @Override 
   public String toString() {
        return "ClickEffect";
   }
}
