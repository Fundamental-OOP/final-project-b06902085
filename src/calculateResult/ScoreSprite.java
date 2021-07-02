package calculateResult;

import model.Sprite;
import model.SpriteShape;

import java.awt.*;

public class ScoreSprite extends Sprite {
    private final Point location;
    private final SpriteShape shape;     
    private final int score;
    private String str = null;

    public ScoreSprite(Point location, int score) {
        this.location = location;
        this.score = score;
        this.shape = new SpriteShape(new Dimension(145, 200), new Dimension(0, 0), new Dimension(145, 200));
    }

    public ScoreSprite(Point location, String str) {
        this.location = location;
        this.str = str;
        this.shape = new SpriteShape(new Dimension(145, 200), new Dimension(0, 0), new Dimension(145, 200));
        this.score = 0;
    }

    @Override
    public void update() {
        
    }

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public void render(Graphics g) {
        g.setFont(new Font("Arial Black", Font.BOLD, 60));
        if (this.str == null) {
            g.drawString(Integer.toString(score), location.x, location.y);
        } else {
            g.drawString(str, location.x, location.y);
        }
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