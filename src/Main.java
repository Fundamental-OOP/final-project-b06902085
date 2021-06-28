import controller.Game;
import model.Screen;
import views.GameView;
import java.io.*;

import static media.AudioPlayer.addAudioByFilePath;

public class Main   {
    public static void main(String[] args) {
        addAudioByFilePath("Reflect", new File("assets/audio/reflect.wav"));
        Screen screen = new Screen();
        Game game = new Game(screen);
        GameView view = new GameView(game);

        game.start();
        view.launch();
    }
}