package track;

import model.SpriteShape;
import model.Sprite;

import java.awt.*;

public class Border extends Sprite {
    private final Point location;
    private final SpriteShape shape;
    private final Color color;
    private final float opacity;

    public Border(Point location, Color color, float opacity, int width, int height) {
        this.location = location;
        this.shape = new SpriteShape(new Dimension(width, height), 
                                new Dimension(0, 0), new Dimension(width, height));
        this.color = color;
        this.opacity = opacity;
    }

    @Override
    public void update() {}

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(this.color);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) this.opacity));
        g2.fillRect(this.location.x, this.location.y, this.getBodySize().width, this.getBodySize().height);
    }


    @Override
    public Dimension getBodyOffset() {
        return this.shape.bodyOffset;
    }


    @Override
    public Dimension getBodySize() {
        return this.shape.bodySize;
    }


    @Override
    public Rectangle getRange() {
        return new Rectangle(this.location, this.shape.size);
    }
}