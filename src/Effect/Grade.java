package Effect;

import java.awt.*;

import model.*;
import views.GameView;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Grade extends Sprite  {
    enum GradeStatus {PERFECT, GOOD, MISS, NULL};

    private Image currentImg;
    private Image perfectImg;
    private Image goodImg;
    private Image missImg;
    private Image nullImg;

    private final Point location;
    private final SpriteShape shape;
    private GradeStatus status;
    
    private int cnt;

    public Grade(Point location) {
        this.location = location;
        this.shape = new SpriteShape(new Dimension(GameView.WIDTH, GameView.HEIGHT), new Dimension(0, 0), new Dimension(GameView.WIDTH, GameView.HEIGHT));
        this.status = status.NULL;
        try {
            perfectImg = ImageIO.read(new File("assets/GameEffect/Perfect.png"));
            goodImg = ImageIO.read(new File("assets/GameEffect/Great.png"));
            missImg = ImageIO.read(new File("assets/GameEffect/Miss.png"));
            nullImg = ImageIO.read(new File("assets/noeffect/1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.currentImg = nullImg;
        this.cnt = 0;
    }

    public String getGradeStatus() {
        if (status == GradeStatus.PERFECT) {
            return "PERFECT";
        } else if (status == GradeStatus.GOOD) {
            return "GOOD";
        } else if (status == GradeStatus.MISS) {
            return "MISS";
        } else {
            return "NULL";
        }
    }

    public void setGradeStatus(String status) {
        this.cnt = 0;
        if (status.equals("PERFECT")) {
            this.currentImg = perfectImg;
            this.status = GradeStatus.PERFECT;
        } else if (status.equals("GOOD")) {
            this.currentImg = goodImg;
            this.status = GradeStatus.GOOD;
        } else if (status.equals("MISS")) {                
            this.currentImg = missImg;
            this.status = GradeStatus.MISS;
        } else {
            this.currentImg = nullImg;
            this.status = GradeStatus.NULL;
        }
    }

    @Override
    public void update() {
       cnt += 1; 
       if (cnt > 1000) {
           this.setGradeStatus("NULL");
       }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(currentImg, location.x, location.y, (int) shape.size.getWidth(), (int) shape.size.getHeight(), null);
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
