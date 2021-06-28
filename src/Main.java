import track.*;

import java.awt.*;
import controller.Game;
import model.Screen;
import views.GameView;

public class Main   {
    public static void main(String[] args) {
        Track t1 = new track.Track(0, new Point(657, 0));
        Track t2 = new track.Track(1, new Point(657 + 154 * 1, 0));
        Track t3 = new track.Track(2, new Point(657 + 154 * 2, 0));
        Track t4 = new track.Track(3, new Point(657 + 154 * 3, 0));

        Border b1 = new Border(new Point(647, 0), new Color(0,0,0), (float) 0.6, 10, 1080);
        Border b2 = new Border(new Point(647 + 154, 0), new Color(0,0,0), (float) 0.6, 10, 1080);
        Border b3 = new Border(new Point(647 + 154 * 2, 0), new Color(0,0,0), (float) 0.6, 10, 1080);
        Border b4 = new Border(new Point(647 + 154 * 3, 0), new Color(0,0,0), (float) 0.6, 10, 1080);
        Border b5 = new Border(new Point(647 + 154 * 4, 0), new Color(0,0,0), (float) 0.6, 10, 1080);

        Screen screen = new Screen(t1, t2, t3, t4, b1, b2, b3, b4, b5);
        Game game = new Game(screen, t1, t2, t3, t4);

        GameView view = new GameView(game);
        game.start();
        view.launch();
    }
}