/**
 * @author Semyon Guretskiy
 * I.D. 336249255
 */
package backgrounds;

import biuoop.DrawSurface;
import game.Sprite;
import geometry.Line;
import geometry.Point;
import instruments.Archive;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Background o the triangle.
 */
public class Triangle implements Sprite {
    private static final int[][] COORDINATES = {
            {80, 560}, {700, 560}, {730, 525}, {50, 525}, {610, 525}, {370, 120},
            {350, 165}, {180, 480}, {578, 480}, {125, 480}, {365, 40}, {315, 40}
    };
    private static final String[] POINTS_NAME = {"D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"};
    private static final String[][] SEGMENTS = {
            {"D", "E"}, {"E", "F"}, {"D", "G"}, {"G", "H"}, {"E", "I"}, {"H", "J"}, {"K", "L"}, {"I", "J"}, {"J", "K"},
            {"M", "K"}, {"M", "N"}, {"N", "F"}, {"N", "O"}, {"O", "G"}
    };
    private List<Line> lines;

    /**
     * Construct.
     */
    public Triangle() {
        TreeMap<String, Point> map = new TreeMap<>();
        this.lines = new ArrayList<>();
        for (int i = 0; i < COORDINATES.length; i++) {
            map.put(POINTS_NAME[i], new Point(COORDINATES[i][Archive.FIRST], COORDINATES[i][Archive.SECOND]));
        }
        for (String[] segment : SEGMENTS) {
            Point p1 = map.get(segment[Archive.FIRST]);
            Point p2 = map.get(segment[Archive.SECOND]);
            this.lines.add(new Line(p1, p2));
        }
    }
    @Override
    public void drawOn(DrawSurface d) {
        for (Line x: this.lines) {
            d.drawLine((int) x.start().getX(), (int) x.start().getY(), (int) x.end().getX(), (int) x.end().getY());
        }
    }

    @Override
    public void timePassed() {
    }
}
