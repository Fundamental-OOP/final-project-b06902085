package track;

import fsm.CyclicSequence;
import fsm.ImageState;
import media.AudioPlayer;
import fsm.FiniteStateMachine;

import java.util.List;

public class ClickEffect extends CyclicSequence {
   public static final String AUDIO_CLICK1 = "click1";
   public static final String AUDIO_CLICK2 = "click2";
   public static final String AUDIO_CLICK3 = "click3";
   public static final String AUDIO_CLICK4 = "click4";
   private final FiniteStateMachine fsm;
   private final Track track;

    public ClickEffect(Track track, FiniteStateMachine fsm, List<ImageState> states) {
       super(states);
       this.fsm = fsm;
       this.track = track;
    }
   
   @Override
   public void update() {
       super.update();
       if(fsm.stateChange){
            switch(track.getID()){    
                case 0:
                    AudioPlayer.playSounds(AUDIO_CLICK1);
                    break;
                case 1:
                    AudioPlayer.playSounds(AUDIO_CLICK2);
                    break;
                case 2:
                    AudioPlayer.playSounds(AUDIO_CLICK3);
                    break;
                case 3:
                    AudioPlayer.playSounds(AUDIO_CLICK4);
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
