/**
 * @author Semyon Guretskiy
 * I.D. 336249255
 */
package backgrounds;

import biuoop.DrawSurface;
import game.Sprite;
import geometry.Point;
import instruments.Archive;
import java.awt.Color;

/**
 * Background for the God's Eye level.
 */
public class GodsEye implements Sprite {
    private static final int NUMBER_ROTATIONS = 50;
    private static final int START_X = 400;
    private static final int START_Y = 300;
    private static final int MIDDLE_CIRCLE = 30;
    private static final int[][] RADIUSES = {{180, 170}, {150, 140}, {110, 100}, {80, 70}};
    private static final Color[] COLORS = {Color.ORANGE, Color.GREEN,
            Color.BLUE, Color.RED};
    private Point[][] circleCenters;

    /**
     * Constructor.
     */
    public GodsEye() {
        this.circleCenters = new Point[RADIUSES.length][NUMBER_ROTATIONS];
        for (int i = 0; i < RADIUSES.length; i++) {
            getCenter(RADIUSES[i][Archive.FIRST], i);
        }
    }

    /**
     * Get Center of the new circle.
     * @param r - the radius.
     * @param j - the number of cycle.
     */
    private void  getCenter(int r, int j) {
        double delta = Archive.DOUBLE * Math.PI / NUMBER_ROTATIONS;
        int c = 0;
        for (double i = 0; i < Archive.DOUBLE * Math.PI; i = i + delta) {
            double x = START_X + r * Math.cos(i);
            double y = START_Y + r * Math.sin(i);
            this.circleCenters[j][c] = new Point(x, y);
            c++;
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        for (int i = 0;  i < this.circleCenters.length; i++) {
            d.setColor(COLORS[i]);
            for (int j = 0; j < this.circleCenters[i].length; j++) {
                Point p = this.circleCenters[i][j];
                d.drawCircle((int) p.getX(), (int) p.getY(), RADIUSES[i][Archive.SECOND]);
            }
        }
        d.setColor(Color.BLACK);
        d.fillCircle(START_X, START_Y, MIDDLE_CIRCLE);
    }

    @Override
    public void timePassed() {
        return;
    }
}
