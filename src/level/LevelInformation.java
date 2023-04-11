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
 * LevelInformation interface. Contain all information to initialize level.
 */
public interface LevelInformation {
    /**
     * Return number of balls in the game.
     * @return int of balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     * @return velocities of balls.
     */
    List<Velocity> initialBallVelocities();

    /**
     * SPeed of the paddle.
     * @return speed of the paddle.
     */
    int paddleSpeed();

    /**
     * Paddle Width.
     * @return width of the paddle in the game.
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     * @return string of the level name.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return sprite of the background.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains.
     * its size, color and location.
     * @return list of blocks in the game
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     * @return number of block in the game.
     */
    int numberOfBlocksToRemove();

    /**
     * Set Color of the balls in the game. Black bt default.
     * @return the color of the balls.
     */
    Color getColorBalls();
}