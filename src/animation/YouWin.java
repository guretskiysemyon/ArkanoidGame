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
 * You Win animation to show score.
 */
public class YouWin implements Animation {
    private static final Point YOU_WIN = new Point(100, 100);
    private static final Point SCORE = new Point(200, 200);
    private static final Point EXIT = new Point(100, 500);
    private static final Color FONE = new Color(153, 255, 153);
    private static final int SIZE = 50;
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;

    /**
     * Constructor.
     * @param k keyboard.
     * @param score - score now in the game.
     */
    public YouWin(KeyboardSensor k, Counter score) {
        this.keyboard = k;
        this.score = score;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(FONE);
        d.fillRectangle(0, 0, Archive.WIDTH_OF_FRAME, Archive.HEIGHT_OF_FRAME);
        d.setColor(Color.ORANGE);
        d.drawText((int) YOU_WIN.getX(), (int) YOU_WIN.getY(), "YOU WIN!!!", Archive.DOUBLE * SIZE);
        d.drawText((int) SCORE.getX(), (int) SCORE.getY(), "SCORE:" + this.score.getValue(), SIZE);
        d.setColor(Color.BLACK);
        d.drawText((int) EXIT.getX(), (int) EXIT.getY(), "Press 'Space' for exit.", SIZE);
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
