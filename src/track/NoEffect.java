package track;

import fsm.CyclicSequence;
import fsm.ImageState;

import java.util.List;

public class NoEffect extends CyclicSequence {
   public NoEffect(List<ImageState> states) {
       super(states);
   }
   
   @Override 
   public String toString() {
        return "NoEffect";
   }
}
