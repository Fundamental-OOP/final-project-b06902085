package views;

import controller.Game;
import controller.GameLoop;
import model.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*; 
import javax.imageio.ImageIO;

public class GameView extends JFrame {
    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;
    private final Canvas canvas;
    private final Game game;
    public static String displayImageName = "START";

    public GameView(Game game) throws HeadlessException {
        try {
            this.canvas = new Canvas();
        }
        catch (IOException e)   {
            throw new RuntimeException(e);
        }
        this.game = game;
        this.game.setView(this.canvas);
    }

    public void launch() {
        // GUI
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(canvas);
        setSize(WIDTH, HEIGHT);
        setContentPane(canvas);
        setVisible(true);

        // Keyboard listener
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                displayImageName = "CHOOSE_SONG";
            }
        });
    }

    public static class Canvas extends JPanel implements GameLoop.View{
        private Screen screen;
        private Image background;
        private Image test;
        @Override
        public void render(Screen screen) {
            this.screen = screen;
            repaint(); // ask the JPanel to repaint, it will invoke paintComponent(g) after a while.
        }

        public Canvas() throws IOException {
            background = ImageIO.read(new File("./assets/anm7064.jpeg"));
            test = ImageIO.read(new File("./assets/thankyou.jpeg"));
        }

        @Override
        protected void paintComponent(Graphics g /*paintbrush*/) {
            super.paintComponent(g);
            if (displayImageName.equals("START"))   {
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
            else    {
                g.drawImage(test, 0, 0, getWidth(), getHeight(), this);
            }
            screen.render(g); // ask the world to paint itself and paint the sprites on the canvas
        }
    }
}
