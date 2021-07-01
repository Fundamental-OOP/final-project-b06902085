package controller;

import model.Screen;

public abstract class GameLoop {
    private boolean running;
    private View view;

    public void setView(View view) {
        this.view = view;
    }

    public void start() {
        new Thread(this::gameLoop).start();
    }

    private void gameLoop() {
        running = true;
        while (running) {
            Screen screen = getScreen();
            screen.update();
            view.render(screen);
            delay(1);
        }
    }

    protected abstract Screen getScreen();

    public void stop() {
        running = false;
    }

    private void delay(long ms) {
        try {
            Thread.sleep(ms);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public interface View {
        void render(Screen screen);
    }
}
