/**
 * @author Semyon Guretskiy
 * I.D. 336249255
 */
package level;

import backgrounds.QR;
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
 * Qr Code level. With QR code on the background.
 */
public class QrLevel extends GeneralLevel {
    private static final int NUMBER_OF_BALLS = 2;
    private static final Velocity FIRST_VELOCITY = new Velocity(5, -6);
    private static final Velocity SECOND_VELOCITY = new Velocity(4, -3);
    private static final Velocity THIRD_VELOCITY = new Velocity(-5, 6);
    private static final int PADDLE_SPEED = 8;
    private static final int PADDLE_WIDTH = 130;
    private static final int SIZE = 20;
    private static final Point BLOCKS = new Point(550, 200);
    private static final int VERTICAL = 4;
    private static final int HORIZONTAL = 3;
    private static final String LEVEL_NAME = "QR CODE";
    private static final Color BALLS_COLOR = Color.WHITE;
    private static final Sprite BACKGROUND = new QR();

    /**
     * Constructor.
     */
    public QrLevel() {
        super(NUMBER_OF_BALLS, PADDLE_SPEED, PADDLE_WIDTH, LEVEL_NAME, BACKGROUND);
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(FIRST_VELOCITY);
        velocities.add(SECOND_VELOCITY);
        velocities.add(THIRD_VELOCITY);
        setVelocities(velocities);
        setBlocks(initializeBlocks());
        setColorOfBalls(BALLS_COLOR);
    }

    /**
     * Initialize blocks.
     * @return list of the blocks.
     */
    private List<Block> initializeBlocks() {
        List<Block> list = new ArrayList<>();
        int x = (int) BLOCKS.getX();
        int y = (int) BLOCKS.getY();
        int two = Archive.DOUBLE;
        int eye = 5;
        for (int i = 0; i < VERTICAL; i++) {
            Block b = new Block(new Rectangle(new Point(x, y), SIZE, SIZE * two), Color.YELLOW);
            list.add(b);
            y += SIZE * two;
        }
        x += SIZE;
        y = (int) BLOCKS.getY();
        for (int i = 0; i < HORIZONTAL; i++) {
            Block b = new Block(new Rectangle(new Point(x, y), SIZE * two, SIZE), Color.YELLOW);
            list.add(b);
            x += SIZE * two;
        }
        for (int i = 0; i < VERTICAL; i++) {
            Block b = new Block(new Rectangle(new Point(x, y), SIZE, SIZE * two), Color.YELLOW);
            list.add(b);
            y += SIZE * two;
        }
        x = (int) BLOCKS.getX() + SIZE;
        y = (int) BLOCKS.getY() + two * SIZE * VERTICAL - SIZE;
        for (int i = 0; i < HORIZONTAL; i++) {
            Block b = new Block(new Rectangle(new Point(x, y), SIZE * two, SIZE), Color.YELLOW);
            list.add(b);
            x += SIZE * two;
        }
        x = (int) BLOCKS.getX();
        y = (int) BLOCKS.getY();
        Block b1 = new Block(new Rectangle(new Point(x + two * SIZE, y + two * SIZE),
                            SIZE, SIZE), Color.YELLOW);
        list.add(b1);
        Block b2 = new Block(new Rectangle(new Point(x + eye * SIZE, y + two * SIZE),
                            SIZE, SIZE), Color.YELLOW);
        list.add(b2);
        x = x + two * SIZE;
        y = y + eye * SIZE;
        for (int i = 0; i < two; i++) {
            Block b = new Block(new Rectangle(new Point(x, y), SIZE * two, SIZE), Color.YELLOW);
            list.add(b);
            x += SIZE * two;
        }
        return list;
    }

}
