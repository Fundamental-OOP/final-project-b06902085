package rank;


import model.Sprite;
import model.SpriteShape;
import views.GameView;

import java.awt.*;
import java.io.*; 
import javax.imageio.ImageIO;

public class Rank extends Sprite  {
    private final SpriteShape shape;
    private static Image image = null;


    public Rank(String string) {
        this.shape = new SpriteShape(new Dimension(GameView.WIDTH, GameView.HEIGHT), new Dimension(0, 0), new Dimension(GameView.WIDTH, GameView.HEIGHT));
        
        
        try {
            if (string.equals("S"))
            {
                image = ImageIO.read(new File("./assets/img/Result/S.png"));
            }
            else if (string.equals("A"))
            {
                image = ImageIO.read(new File("./assets/img/Result/A.png"));
            }
            else if (string.equals("B"))
            {
                image = ImageIO.read(new File("./assets/img/Result/B.png"));
            }
            else if (string.equals("C"))
            {
                image = ImageIO.read(new File("./assets/img/Result/C.png"));
            }
            else if (string.equals("F"))
            {
                image = ImageIO.read(new File("./assets/img/Result/F.png"));
            }
        }
        catch (IOException e)   {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        g.drawImage(image, 0, 0, 1920, 1280, null);
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
