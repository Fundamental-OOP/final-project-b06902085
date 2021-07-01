package model;

import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;

public class Screen {
    private final List<Sprite> sprites = new CopyOnWriteArrayList<>();

    public Screen(Sprite... sprites) {
        addSprites(sprites);
    }

    public void update() {
        for (Sprite sprite : sprites) {
            sprite.update();
        }
    }

    public void addSprites(Sprite... sprites) {
        stream(sprites).forEach(this::addSprite);
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
        sprite.setScreen(this);
    }

    public void removeSprite(Sprite sprite) {
        sprites.remove(sprite);
        sprite.setScreen(null);
    }

    public void removeSprites() {
        for(Sprite s : sprites) {
            removeSprite(s);
        }
    }

    public Collection<Sprite> getSprites(Rectangle area) {
        return sprites.stream()
                .filter(s -> area.intersects(s.getBody()))
                .collect(toSet());
    }

    public List<Sprite> getSprites() {
        return sprites;
    }

    public void render(Graphics g) {
        for (Sprite sprite : sprites) {
            sprite.render(g);
        }
    }
}
