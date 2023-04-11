/**
 * @author Semyon Guretskiy
 * I.D. 336249255
 */
package parts;

import biuoop.DrawSurface;
import game.Sprite;
import instruments.Archive;

/**
 * Level name.
 */
public class LevelName implements Sprite {
    private String text;

    /**
     * Constructor.
     * @param s - text to write.
     */
    public LevelName(String s) {
        this.text = s;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(Archive.LEVEL_NAME_X, Archive.PRINT_PANEL_HEIGHT - Archive.TWO, "Level: " + this.text,
                Archive.TEXT_SIZE);
    }

    @Override
    public void timePassed() {
    }
}
