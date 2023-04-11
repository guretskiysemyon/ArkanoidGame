/**
 * @author Semyon Guretskiy
 * I.D. 336249255
 */
package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import instruments.Archive;

import java.awt.Color;

/**
 * Pause in the game.
 */
public class PauseScreen implements Animation {
    private static final Point PAUSE = new Point(200, 250);
    private static final Point EXIT = new Point(100, 500);
    private static final Rectangle ONE = new Rectangle(new Point(400, 300), 20, 60);
    private static final Rectangle TWO = new Rectangle(new Point(360, 300), 20, 60);
    private static final Point CIRCLE = new Point(390, 327);
    private static final int SIZE_CIRCLE = 60;
    private static final int SIZE = 50;
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Constructor.
     * @param k - keyboard in the game.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(0, 0, Archive.WIDTH_OF_FRAME, Archive.HEIGHT_OF_FRAME);
        d.setColor(Color.WHITE);
        d.drawText((int) PAUSE.getX(), (int) PAUSE.getY(), "You are on Pause.", SIZE);
        ONE.drawRectangle(d, Color.WHITE);
        TWO.drawRectangle(d, Color.WHITE);
        d.setColor(Color.WHITE);
        d.drawCircle((int) CIRCLE.getX(), (int) CIRCLE.getY(), SIZE_CIRCLE);
        d.setColor(Color.RED);
        d.drawText((int) EXIT.getX(), (int) EXIT.getY(), "To continue press 'space'", SIZE);
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}