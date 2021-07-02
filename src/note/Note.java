package note;
/*
import fsm.FiniteStateMachine;
import fsm.ImageRenderer;
import fsm.State;
import fsm.WaitingPerFrame;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import static fsm.FiniteStateMachine.Transition.from;
import static utils.ImageStateUtils.imageStatesFromFolder;
*/
import model.Sprite;
import model.SpriteShape;
import views.GameView;

import java.awt.*;
import java.io.*; 
import javax.imageio.ImageIO;

public class Note extends Sprite    {
    private final Point location;
    private final SpriteShape shape;
    private final NoteDatabase db;
    public final int trackID;
    private static Image image = null;
    private static int dropDistance = 1;
    
    public Note(Point location, NoteDatabase db,int trackID) {
        this.location = location;
        this.shape = new SpriteShape(new Dimension(145, 55), new Dimension(0, 0), new Dimension(145, 55));
        this.db = db;
        this.trackID = trackID;
        try {
            image = ImageIO.read(new File("./assets/note/shark_145x55.png"));
        }
        catch (IOException e)   {
            throw new RuntimeException(e);
        }
    }

    public void NoteSuspend() {
        setdropDistance(0);
    }

    public void NoteResume() {
        setdropDistance(1);
    }

    private void setdropDistance(int d) {
        dropDistance = d;
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1.0));
        g.drawImage(image, location.x, location.y, 145, 55, null);
    }

    @Override
    public void update() {
        if (this.location.getY() <= GameView.HEIGHT - 100) {
            this.location.setLocation(this.location.getX(), this.location.getY() + dropDistance);
        }
       
        else  {
            this.db.missdrop();
            this.db.removeNote(this.trackID);
        }
        
    }

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public Rectangle getRange() {
        return new Rectangle(location, shape.size);
    }

    @Override
    public Dimension getBodyOffset() {
        return shape.bodyOffset;
    }

    @Override
    public Dimension getBodySize() {
        return shape.bodySize;
    }
}