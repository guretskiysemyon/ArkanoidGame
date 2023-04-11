/**
 * @author Semyon Guretskiy
 * I.D. 336249255
 */
package level;

import backgrounds.GodsEye;
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
 * Level with GodEye Sprite.
 */
public class GodEyeLevel extends GeneralLevel {
    private static final int NUMBER_OF_BALLS = 1;
    private static final Velocity FIRST_VELOCITY = new Velocity(0, -2);
    private static final int PADDLE_SPEED = 8;
    private static final int PADDLE_WIDTH = 100;
    private static final int BLOCK_X = 390;
    private static final int BLOCK_Y = 280;
    private static final int SIZE = 20;
    private static final String LEVEL_NAME = "GOD'S EYE";
    private static final Sprite BACKGROUND = new GodsEye();

    /**
     * Constructor.
     */
    public GodEyeLevel() {
        super(NUMBER_OF_BALLS, PADDLE_SPEED, PADDLE_WIDTH, LEVEL_NAME, BACKGROUND);
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(FIRST_VELOCITY);
        setVelocities(velocities);
        setBlocks(initializeBlocks());
    }

    /**
     * Initialise block in this level.
     * @return list of the blocks.
     */
    private List<Block> initializeBlocks() {
        Block b = new Block(new Rectangle(new Point(BLOCK_X, BLOCK_Y),
                            SIZE, SIZE * Archive.DOUBLE), Color.WHITE);
        List<Block> list = new ArrayList<>();
        list.add(b);
        return list;
    }
}
