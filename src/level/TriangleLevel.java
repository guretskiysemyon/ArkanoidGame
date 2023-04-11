/**
 * @author Semyon Guretskiy
 * I.D. 336249255
 */
package level;

import backgrounds.Triangle;
import game.Sprite;
import geometry.Point;
import geometry.Rectangle;
import instruments.Archive;
import instruments.Velocity;
import parts.Block;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Level with Triangle on the background.
 */
public class TriangleLevel extends GeneralLevel {
    private static final int NUMBER_OF_BALLS = 2;
    private static final Velocity FIRST_VELOCITY = new Velocity(5, -6);
    private static final Velocity SECOND_VELOCITY = new Velocity(4, -3);
    private static final int PADDLE_SPEED = 7;
    private static final int PADDLE_WIDTH = 130;
    private static final int X_ONE = 30;
    private static final int Y_ONE = 70;
    private static final int X_TWO = 750;
    private static final int SIZE = 20;
    private static final String LEVEL_NAME = "Triangle";
    private static final Sprite BACKGROUND = new Triangle();
    private static final int NUMBER_BLOCKS = 10;

    /**
     * Constructor.
     */
    public TriangleLevel() {
        super(NUMBER_OF_BALLS, PADDLE_SPEED, PADDLE_WIDTH, LEVEL_NAME, BACKGROUND);
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(FIRST_VELOCITY);
        velocities.add(SECOND_VELOCITY);
        setVelocities(velocities);
        setBlocks(initializeBlocks());
    }

    /**
     * Initialize blocks in the game.
     * @return list of blocks.
     */
    private List<Block> initializeBlocks() {
        List<Block> list = new ArrayList<>();
        int x1 = X_ONE;
        int x2 = X_TWO;
        int y = Y_ONE;
        for (int i = 0; i < NUMBER_BLOCKS; i++) {
            Block b  = new Block(new Rectangle(new Point(x1, y), SIZE, SIZE * Archive.DOUBLE), Color.MAGENTA);
            Block b1 = new Block(new Rectangle(new Point(x2, y), SIZE, SIZE * Archive.DOUBLE), Color.MAGENTA);
            list.add(b);
            list.add(b1);
            y += SIZE * Archive.DOUBLE;
        }
        return list;
    }
}
