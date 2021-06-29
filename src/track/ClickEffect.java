package track;

import fsm.CyclicSequence;
import fsm.ImageState;

import java.util.List;

public class ClickEffect extends CyclicSequence {
   public ClickEffect(List<ImageState> states) {
       super(states);
   }
   
   @Override 
   public String toString() {
        return "ClickEffect";
   }
}
