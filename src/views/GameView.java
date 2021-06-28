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

import java.util.Map;
import java.util.HashMap;

public class GameView extends JFrame {
    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;
    private final Canvas canvas;
    private final Game game;
    public static String state = "TITLE";
    private static final Map<String, Image> images = new HashMap<>();

    public GameView(Game game) throws HeadlessException {
        try {
            addImageByFilePath("TITLE", ImageIO.read(new File("./assets/img/anm7064.jpeg")));
            addImageByFilePath("REFLECT", ImageIO.read(new File("./assets/img/reflect_bg.png")));
            addImageByFilePath("COUNTRY_ROADS", ImageIO.read(new File("./assets/img/ukelele.jpeg")));
            this.canvas = new Canvas();
        }
        catch (IOException e)   {
            throw new RuntimeException(e);
        }
        this.game = game;
        this.game.setView(this.canvas);
    }

    public static void addImageByFilePath(String imageName, Image image) {
        images.put(imageName, image);
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
                if (state.equals("TITLE"))  {
                    state = game.enterMenu();
                }
                else    {
                    switch (keyEvent.getKeyCode()) {
                        case KeyEvent.VK_RIGHT:
                            state = game.nextSong();
                            break;
                        case KeyEvent.VK_LEFT:
                            state = game.previousSong();
                            break;
                    }
                }
            }
        });
    }

    public static class Canvas extends JPanel implements GameLoop.View{
        private Screen screen;
        @Override
        public void render(Screen screen) {
            this.screen = screen;
            repaint(); // ask the JPanel to repaint, it will invoke paintComponent(g) after a while.
        }

        @Override
        protected void paintComponent(Graphics g /*paintbrush*/) {
            super.paintComponent(g);
            g.drawImage(images.get(state), 0, 0, getWidth(), getHeight(), this);

            screen.render(g); // ask the world to paint itself and paint the sprites on the canvas
        }
    }
}
