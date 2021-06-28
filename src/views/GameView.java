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
    public static final int T0 = 0;
    public static final int T1 = 1;
    public static final int T2 = 2;
    public static final int T3 = 3;
    private final Canvas canvas;
    private final Game game;

    public GameView(Game game) throws HeadlessException {
        try {
            this.canvas = new Canvas("./assets/sea.png");
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
                	case KeyEvent.VK_D:
				game.clickTrack(T0);
				break;
                	case KeyEvent.VK_F:
				game.clickTrack(T1);
				break;
                	case KeyEvent.VK_J:
				game.clickTrack(T2);
				break;
                	case KeyEvent.VK_K:
				game.clickTrack(T3);
				break;
			case KeyEvent.VK_P:
				//Pause
				break;
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
            
            screen.render(g); // ask the world to paint itself and paint the sprites on the canvas
        }
    }
}
