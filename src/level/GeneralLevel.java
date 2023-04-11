/**
 * @author Semyon Guretskiy
 * I.D. 336249255
 */
package level;

import game.Sprite;
import instruments.Velocity;
import parts.Block;
import java.awt.Color;
import java.util.List;

/**
 * General level class that contain information about level.
 */
public class GeneralLevel implements LevelInformation {
    private int numberOfBalls;
    private Color colorOfBalls = Color.black;
    private List<Velocity> velocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;

    /**
     * Constructor.
     * @param balls - number of balls in the game.
     * @param padSpeed - the speed of the paddle.
     * @param padWidth - the width of the paddle.
     * @param levName - the level name.
     * @param back - the background of the game.
     */
    public GeneralLevel(int balls, int padSpeed, int padWidth,
                        String levName, Sprite back) {
        this.numberOfBalls = balls;
        this.paddleSpeed = padSpeed;
        this.paddleWidth = padWidth;
        this.levelName = levName;
        this.background = back;
    }

    /**
     * Set velocities of the balls in the game.
     * @param list of the velocities.
     */
    protected void setVelocities(List<Velocity> list) {
        this.velocities = list;
    }

    /**
     * Set the blocks in the game.
     * @param bList of the blocks
     */
    protected void setBlocks(List<Block> bList) {
        this.blocks = bList;
    }

    /**
     * Set colot of the balls.
     * @param c color of the balls.
     */
    protected void setColorOfBalls(Color c) {
        this.colorOfBalls = c;
    }
    @Override
    public int numberOfBalls() {
        return this.velocities.size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
       return this.velocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks.size();
    }

    @Override
    public Color getColorBalls() {
        return this.colorOfBalls;
    }

}
