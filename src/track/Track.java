package track;

import fsm.FiniteStateMachine;
import fsm.ImageRenderer;
import fsm.State;
import fsm.WaitingPerFrame;
import model.SpriteShape;
import model.Sprite;
import views.GameView;

import java.awt.*;
import static fsm.FiniteStateMachine.Transition.from;
import static utils.ImageStateUtils.imageStatesFromFolder;
import static track.Track.Click.*;

public class Track extends Sprite {
    private final int id;
    private final Point location;
    private final SpriteShape shape;
    private FiniteStateMachine fsm;

    public enum Click {
        ON,OFF
    }

    public Track(int id, Point location) {
        this.location = location;
        this.id = id;
        shape = new SpriteShape(new Dimension(144, GameView.HEIGHT), 
                                new Dimension(0, 0), new Dimension(144, GameView.HEIGHT));
        fsm = new FiniteStateMachine();
        ImageRenderer imageRenderer = new TrackImageRenderer(this);
        State clickOn = new WaitingPerFrame(1, new ClickEffect(this,fsm,imageStatesFromFolder("assets/clickeffect",imageRenderer)));
        State clickOff = new WaitingPerFrame(2, new NoEffect(imageStatesFromFolder("assets/noeffect",imageRenderer)));
        fsm.setInitialState(clickOff);
        fsm.addTransition(from(clickOff).when(ON).to(clickOn));
        fsm.addTransition(from(clickOn).when(OFF).to(clickOff));
    }

    public int getID() {
        return this.id;
    }

    public void click() {
    	fsm.trigger(ON);
    }

    public void release() {
        fsm.trigger(OFF);
    }

    @Override
    public void update() {
        fsm.update();
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(110, 177, 235));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.82));
        g2.fillRect(this.location.x, this.location.y, this.getBodySize().width, this.getBodySize().height);
        fsm.render(g);
    }

    @Override
    public Dimension getBodyOffset() {
        return shape.bodyOffset;
    }


    @Override
    public Dimension getBodySize() {
        return shape.bodySize;
    }


    @Override
    public Rectangle getRange() {
        return new Rectangle(location, shape.size);
    }


}
