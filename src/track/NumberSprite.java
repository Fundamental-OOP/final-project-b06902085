package track;

import model.Sprite;
import model.SpriteShape;

import java.awt.*;

public class NumberSprite extends Sprite  {
    private final Point location;
    private final SpriteShape shape;
    private final Integer number;
    
    public NumberSprite(Point location, Integer number) {
        this.location = location;
        this.shape = new SpriteShape(new Dimension(300, 50), new Dimension(0, 0), new Dimension(300, 50));
        this.number = number;
    }

    @Override
    public void render(Graphics g) {
        g.setFont(new Font("Arial Black", Font.BOLD, 20));
        g.drawString("Combo " + Integer.toString(number), location.x, location.y);
    }

    @Override
    public void update() {
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
