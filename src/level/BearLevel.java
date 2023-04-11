/**
 * @author Semyon Guretskiy
 * I.D. 336249255
 */
package level;

import backgrounds.Bear;
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
 * Level with bear is drawn by lines.
 */
public class BearLevel extends GeneralLevel {
    private static final int NUMBER_OF_BALLS = 8;
    private static final int PADDLE_SPEED = 7;
    private static final int PADDLE_WIDTH = 300;
    private static final String LEVEL_NAME = "BEAR";
    private static final int SIZE = 20;
    private static final int MAX_HEIGHT = 3;
    private static final int START = 50;
    private static final int STEP_E = 6;
    private static final int STEP_A = 11;
    private static final int STEP_R = 17;
    private static final Sprite BACKGROUND = new Bear();
    private static final int SPEED = 8;

    /**
     * Constructor.
     */
    public BearLevel() {
        super(NUMBER_OF_BALLS, PADDLE_SPEED, PADDLE_WIDTH, LEVEL_NAME, BACKGROUND);
        List<Velocity> velocities = new ArrayList<>();
        setVelocities(initializeVelocities());
        setBlocks(initializeBlocks());
    }

    /**
     * Initialise Velocities for the balls.
     * @return list of velocities.
     */
    private List<Velocity> initializeVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        int speed = SPEED;
        double delta = Math.PI / (NUMBER_OF_BALLS + 1);
        for (double i = delta; i < Math.PI; i += delta) {
            double dx = speed * Math.cos(i);
            double dy = speed * Math.sin(i);
            velocities.add(new Velocity(dx, dy));
        }
        return velocities;
    }

    /**
     * initialize blocks.
     * @return list of blocks in the game.
     */
    private List<Block> initializeBlocks() {
        List<Block> list = new ArrayList<>();
        list.addAll(getB());
        list.addAll(getE());
        list.addAll(getA());
        list.addAll(getR());
        return list;
    }

    /**
     * Get the B letter from blocks.
     * @return - list of blocks.
     */
    private List<Block> getB() {
        int x = START;
        int y = START;
        List<Block> b = new ArrayList<>();
        for (int i = 1; i <= MAX_HEIGHT; i++) {
            Block b1 = new Block(new Rectangle(new Point(x, y), SIZE, Archive.DOUBLE * SIZE), Color.ORANGE);
            b.add(b1);
            y = y + SIZE * Archive.DOUBLE;
        }
        x = x + SIZE;
        y = START;
        for (int i = 0; i < MAX_HEIGHT; i++) {
            Block b1 = new Block(new Rectangle(new Point(x, y), SIZE * Archive.DOUBLE, SIZE), Color.ORANGE);
            b.add(b1);
            y = y +  SIZE * (Archive.DOUBLE + i);
        }
        x = START + MAX_HEIGHT * SIZE;
        y = START;
        for (int i = 1; i <= MAX_HEIGHT; i++) {
            Block b1 = new Block(new Rectangle(new Point(x, y), SIZE, Archive.DOUBLE * SIZE), Color.ORANGE);
            b.add(b1);
            y = y + SIZE * Archive.DOUBLE;
        }
        return b;
    }

    /**
     * Get the E letter from the blocks.
     * @return - list of blocks
     */
    private List<Block> getE() {
        int y = START;
        int x = START + STEP_E * SIZE;
        List<Block> e = new ArrayList<>();
        for (int i = 1; i <= MAX_HEIGHT; i++) {
            Block b1 = new Block(new Rectangle(new Point(x, y), SIZE, Archive.DOUBLE * SIZE), Color.ORANGE);
            e.add(b1);
            y = y + SIZE * Archive.DOUBLE;
        }
        x  +=  SIZE;
        y = START;
        for (int i = 0; i < MAX_HEIGHT; i++) {
            Block b1 = new Block(new Rectangle(new Point(x, y), SIZE * Archive.DOUBLE, SIZE), Color.ORANGE);
            e.add(b1);
            y = y +  SIZE * (Archive.DOUBLE + i);
        }
        return e;
    }

    /**
     * Get the A letter from tth blocks.
     * @return list of blocks.
     */
    private List<Block> getA() {
        int y = START;
        int x = START + STEP_A * SIZE;
        List<Block> a = new ArrayList<>();
        for (int i = 1; i <= MAX_HEIGHT; i++) {
            Block b1 = new Block(new Rectangle(new Point(x, y), SIZE, Archive.DOUBLE * SIZE), Color.ORANGE);
            a.add(b1);
            y = y + SIZE * Archive.DOUBLE;
        }
        x += SIZE;
        y = START;
        for (int i = 0; i < MAX_HEIGHT - 1; i++) {
            Block b1 = new Block(new Rectangle(new Point(x, y), SIZE * Archive.DOUBLE, SIZE), Color.ORANGE);
            a.add(b1);
            y = y +  SIZE * (Archive.DOUBLE + i);
        }
        y = START;
        x += (MAX_HEIGHT - 1) * SIZE;
        for (int i = 1; i <= MAX_HEIGHT; i++) {
            Block b1 = new Block(new Rectangle(new Point(x, y), SIZE, Archive.DOUBLE * SIZE), Color.ORANGE);
            a.add(b1);
            y = y + SIZE * Archive.DOUBLE;
        }
        return a;
    }

    /**
     * The R letter from the blocks.
     * @return list of the blocks.
     */
    private List<Block> getR() {
        int y = START;
        int x = START + STEP_R * SIZE;
        List<Block> r = new ArrayList<>();
        for (int i = 1; i <= MAX_HEIGHT; i++) {
            Block b1 = new Block(new Rectangle(new Point(x, y), SIZE, Archive.DOUBLE * SIZE), Color.ORANGE);
            r.add(b1);
            y = y + SIZE * Archive.DOUBLE;
        }
        x += SIZE;
        y = START;
        for (int i = 0; i < MAX_HEIGHT - 1; i++) {
            Block b1 = new Block(new Rectangle(new Point(x, y), SIZE * Archive.DOUBLE, SIZE), Color.ORANGE);
            r.add(b1);
            y = y +  SIZE * (Archive.DOUBLE + i);
        }
        y = START;
        x +=  (MAX_HEIGHT - 1) * SIZE;
        Block b2 = new Block(new Rectangle(new Point(x, y), SIZE, MAX_HEIGHT * SIZE), Color.ORANGE);
        x -= SIZE;
        y = y + MAX_HEIGHT * SIZE;
        Block b3 = new Block(new Rectangle(new Point(x, y), SIZE, MAX_HEIGHT * SIZE), Color.ORANGE);
        r.add(b2);
        r.add(b3);
        return r;
    }

}
