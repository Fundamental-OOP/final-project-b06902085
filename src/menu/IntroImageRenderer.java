package menu;

import java.awt.*;
import fsm.ImageRenderer;

public class IntroImageRenderer implements ImageRenderer {
    protected Intro intro;

    public IntroImageRenderer(Intro intro) {
        this.intro = intro;
    }

    @Override
    public void render(Image image, Graphics g) { 
        Rectangle range = intro.getRange();
        g.drawImage(image, 0, 0, range.width - 500, range.height - 250, null);
    }
}