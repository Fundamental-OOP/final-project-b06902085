package controller;

import model.Screen;

public class Game extends GameLoop  {
    private final Screen screen;

    public Game(Screen screen)   {
        this.screen = screen;
    }
    @Override
    protected Screen getScreen() {
        return screen;
    }
}
