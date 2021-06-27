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
    public static final int HEIGHT = 1080;
    public static final int WIDTH = 1920;
    private final Canvas canvas;
    private final Game game;

    public GameView(Game game) throws HeadlessException {
        try {
            this.canvas = new Canvas("./assets/anm7064.jpeg");
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
                switch (keyEvent.getKeyCode()) {
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
    }

    public static class Canvas extends JPanel implements GameLoop.View{
        private Screen screen;
        private Image backgroundImage;

        @Override
        public void render(Screen screen) {
            this.screen = screen;
            repaint(); // ask the JPanel to repaint, it will invoke paintComponent(g) after a while.
        }

        public Canvas(String fileName) throws IOException {
            backgroundImage = ImageIO.read(new File(fileName));
        }

        @Override
        protected void paintComponent(Graphics g /*paintbrush*/) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, this);
            //g.fillRect(0, 0, GameView.WIDTH, GameView.HEIGHT);

            screen.render(g); // ask the world to paint itself and paint the sprites on the canvas
        }
    }
}
