/**
 * @author Guretskiy Semyon.
 * ID: 336249255
 */
package parts;
import game.Sprite;
import instruments.Archive;
import instruments.Counter;
import biuoop.DrawSurface;

/**
 * The Score indicator class, that hold the counter and print the score on the panel.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Constructor.
     * @param sc - Counter to the score with some start value.
     */
    public ScoreIndicator(Counter sc) {
        this.score = sc;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(Archive.WIDTH_OF_FRAME / Archive.DOUBLE, Archive.PRINT_PANEL_HEIGHT - Archive.TWO,
                " Score: " + score.getValue(), Archive.TEXT_SIZE);
    }

    @Override
    public void timePassed() {
    }
}
