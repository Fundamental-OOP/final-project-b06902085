package menu;

import fsm.CyclicSequence;
import fsm.ImageRenderer;
import fsm.ImageState;
import fsm.State;
import model.SpriteShape;
import model.Sprite;
import views.GameView;

import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Intro extends Sprite {
    private final SpriteShape shape;
    private CyclicSequence seq;

    public Intro() {
        shape = new SpriteShape(new Dimension(GameView.WIDTH, GameView.HEIGHT), 
                                new Dimension(0, 0), new Dimension(GameView.WIDTH, GameView.HEIGHT));
        ImageRenderer imageRenderer = new IntroImageRenderer(this);
        Image image1 = null;
        Image image2 = null;
        try {
            image1 = ImageIO.read(new File("./assets/intro/1.png"));
            image2 = ImageIO.read(new File("./assets/intro/2.png"));
        }
        catch (IOException e)   {
            throw new RuntimeException(e);
        }
        ArrayList<State> colors = new ArrayList<State>();
        colors.add(new ImageState(image1, imageRenderer));
        colors.add(new ImageState(image2, imageRenderer));
        this.seq = new CyclicSequence(colors);
    }

    @Override
    public void update() {
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.seq.update();
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1.0));
        this.seq.render(g);
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