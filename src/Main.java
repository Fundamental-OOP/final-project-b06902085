import controller.Game;
import model.Screen;
import views.GameView;

public class Main   {
    public static void main(String[] args) {
        Screen screen = new Screen();
        Game game = new Game(screen);
        GameView view = new GameView(game);

        game.start();
        view.launch();
    }
}