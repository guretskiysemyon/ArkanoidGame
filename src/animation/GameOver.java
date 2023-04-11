/**
 * @author Semyon Guretskiy
 * I.D. 336249255
 */
package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import instruments.Archive;
import instruments.Counter;
import java.awt.Color;

/**
 * Game Over Animation is start after all lives and ball ended.
 */
public class GameOver implements Animation {
        private static final Point GAME_OVER = new Point(100, 100);
        private static final Point SCORE = new Point(200, 200);
        private static final Point EXIT = new Point(100, 500);
        private static final int SIZE_TEXT = 50;
        private Counter score;
        private boolean stop;
        private KeyboardSensor keyboard;

    /**
     * Constructor.
     * @param keyboard - the keyboard of the game.
     * @param score - the score in the game now.
     */
    public GameOver(KeyboardSensor keyboard, Counter score) {
        this.stop = false;
        this.score = score;
        this.keyboard = keyboard;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.PINK);
        d.fillRectangle(0, 0, Archive.WIDTH_OF_FRAME, Archive.HEIGHT_OF_FRAME);
        d.setColor(Color.RED);
        d.drawText((int) GAME_OVER.getX(), (int) GAME_OVER.getY(), "GAME OVER", Archive.DOUBLE * SIZE_TEXT);
        d.drawText((int) SCORE.getX(), (int) SCORE.getY(), "SCORE:" + this.score.getValue(), SIZE_TEXT);
        d.setColor(Color.BLACK);
        d.drawText((int) EXIT.getX(), (int) EXIT.getY(), "Press 'Space' for exit.", SIZE_TEXT);
        if (this.keyboard.isPressed("space")) {
            stop = true;
        }
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
