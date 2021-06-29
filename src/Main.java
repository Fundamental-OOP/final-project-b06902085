import track.*;

import java.awt.*;
import controller.Game;
import model.Screen;
import views.GameView;

public class Main   {
    public static void main(String[] args) {
        int borderWidth = 10;
        int startpos = (GameView.WIDTH - 144 * 4 - 5 * borderWidth) / 2;
        
        Track t1 = new track.Track(0, new Point(startpos + borderWidth, 0));
        Track t2 = new track.Track(1, new Point(startpos + 154 * 1 + borderWidth, 0));
        Track t3 = new track.Track(2, new Point(startpos + 154 * 2 + borderWidth, 0));
        Track t4 = new track.Track(3, new Point(startpos + 154 * 3 + borderWidth, 0));

        Border b1 = new Border(new Point(startpos, 0), new Color(0,0,0), (float) 0.6, borderWidth, GameView.HEIGHT);
        Border b2 = new Border(new Point(startpos + 154, 0), new Color(0,0,0), (float) 0.6, borderWidth, GameView.HEIGHT);
        Border b3 = new Border(new Point(startpos + 154 * 2, 0), new Color(0,0,0), (float) 0.6, borderWidth, GameView.HEIGHT);
        Border b4 = new Border(new Point(startpos + 154 * 3, 0), new Color(0,0,0), (float) 0.6, borderWidth, GameView.HEIGHT);
        Border b5 = new Border(new Point(startpos + 154 * 4, 0), new Color(0,0,0), (float) 0.6, borderWidth, GameView.HEIGHT);

        Screen screen = new Screen(t1, t2, t3, t4, b1, b2, b3, b4, b5);
        Game game = new Game(screen, t1, t2, t3, t4);

        GameView view = new GameView(game);
        game.start();
        view.launch();
    }
}