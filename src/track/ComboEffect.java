package track;

import model.SpriteShape;
import model.Sprite;
import views.GameView;

import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;


public class ComboEffect extends Sprite{
    private final SpriteShape shape;
    private Image curImg = null;

    public ComboEffect()
    {
        shape = new SpriteShape(new Dimension(GameView.WIDTH, GameView.HEIGHT), 
                                new Dimension(0, 0), new Dimension(GameView.WIDTH, GameView.HEIGHT));

        try {
            curImg = ImageIO.read(new File("./assets/GameEffect/combo.png"));
        }
        catch (IOException e)   {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1.0));
        g.drawImage(curImg, 0, 0, 1920, 1280, null);
        // this.seq.render(g);
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

    @Override
    public void update() {
        // TODO Auto-generated method stub
    }
}
