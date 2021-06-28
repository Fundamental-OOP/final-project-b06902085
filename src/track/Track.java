package track;

import model.SpriteShape;
import model.Sprite;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArraySet;

public class Track extends Sprite {

    private final int id;
    private final Point location;
    private final SpriteShape shape;


    public Track(int id, Point location) {
        this.location = location;
        this.id = id;
        shape = new SpriteShape();
        ImageRenderer imageRenderer = new TrackImageRenderer(this);
        //???
    }


}
