package track;

import model.SpriteShape;
import model.Sprite;

import java.awt.*;

public class Track extends Sprite {
    private final int id;
    private final Point location;
    private final SpriteShape shape;


    public Track(int id, Point location) {
        this.location = location;
        this.id = id;
        shape = new SpriteShape(new Dimension(144, 1080), 
                                new Dimension(0, 0), new Dimension(144, 1080));
        ImageRenderer imageRenderer = new TrackImageRenderer(this);
    }

    public int getID() {
        return this.id;
    }

    @Override
    public void update() {}

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(110, 177, 235));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.82));
        g2.fillRect(this.location.x, this.location.y, this.getBodySize().width, this.getBodySize().height);
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
