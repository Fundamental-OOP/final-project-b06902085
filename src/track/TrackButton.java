package track;

import model.SpriteShape;
import model.Sprite;
import java.io.*; 
import javax.imageio.ImageIO;
import java.awt.*;

public class TrackButton extends Sprite {
    private final Point location;
    private final SpriteShape shape;
    private final Image image;

    public TrackButton(Point location, String buttonName, int width, int height) {
        this.location = location;
        this.shape = new SpriteShape(new Dimension(width, height), 
                                new Dimension(0, 0), new Dimension(width, height));
        try {
            image = ImageIO.read(new File("./assets/button/" + buttonName + ".png"));
        }
        catch (IOException e)   {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update() {}

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1.0));
        g.drawImage(image, location.x, location.y, 145, 55, null);
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