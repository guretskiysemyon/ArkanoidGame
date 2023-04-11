/**
 * @author Semyon Guretskiy
 * I.D. 336249255
 */
package animation;

import biuoop.DrawSurface;
import game.SpriteCollection;
import instruments.Archive;


/**The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private static final int X = 100;
    private SpriteCollection sprites;
    private int count;
    private double seconds;

    /**
     * Constructor.
     * @param numOfSeconds - the number of second to run animation.
     * @param countFrom - Number to count from.
     * @param gameScreen - the sprite collection to draw the screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.sprites = gameScreen;
        this.count = countFrom;
        this.seconds = numOfSeconds;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        d.drawText(X, Archive.PRINT_PANEL_HEIGHT - Archive.TWO,
                String.valueOf(this.count), Archive.TEXT_SIZE);
        this.count--;
    }
    @Override
    public boolean shouldStop() {
        if (this.count == 0) {
            return true;
        }
        return false;
    }
}