package track;

import java.awt.*;
import fsm.ImageRenderer;

public class TrackImageRenderer implements ImageRenderer {
    protected Track track;

    public TrackImageRenderer(Track track) {
        this.track = track;
    }

    @Override
    public void render(Image image, Graphics g) { 
        Rectangle range = track.getRange();
        g.drawImage(image, range.x + range.width, range.y - 120, -range.width, range.height, null);
    }
}
