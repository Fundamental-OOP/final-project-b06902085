package model;

import java.awt.*;

public abstract class Sprite {
    protected Screen screen;
    protected Point location = new Point();

    public abstract void update();

    public abstract void render(Graphics g);

    public Screen getScreen() {
        return this.screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Point getLocation() {
        return this.location;
    }

    public abstract Dimension getBodyOffset();

    public abstract Dimension getBodySize();

    public Rectangle getBody() {
        return getArea(getBodyOffset(), getBodySize());
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public int getX() {
        return getRange().x;
    }

    public int getY() {
        return getRange().y;
    }

    public abstract Rectangle getRange();

    public Rectangle getArea(Dimension offset, Dimension size) {
        return null;
    }

    public boolean isAlive() {
        return this.screen != null;
    }
}
