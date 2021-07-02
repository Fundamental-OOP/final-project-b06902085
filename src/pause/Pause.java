package pause;

import model.Sprite;
import model.SpriteShape;
import views.GameView;

import java.awt.*;
import java.io.*; 
import javax.imageio.ImageIO;

public class Pause extends Sprite    {
    private final Point location;
    private final SpriteShape shape;
    private static Image image = null;
    
    public Pause(Point location) {
        this.location = location;
        this.shape = new SpriteShape(new Dimension(GameView.WIDTH, GameView.HEIGHT), new Dimension(0, 0), new Dimension(GameView.WIDTH, GameView.HEIGHT));
        try {
            image = ImageIO.read(new File("./assets/img/Pause/select.png"));
        }
        catch (IOException e)   {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, location.x, location.y, (int) shape.bodySize.getWidth(), (int) shape.bodySize.getHeight(), null);
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