/**
 * @author Semyon Guretskiy.
 * ID: 336249255
 */
package listeners;

import parts.Ball;
import parts.Block;

/**
 * HitListener interface. Listen to the hits in the game.
 */
public interface HitListener {
    /**
     * After some block being hit either remove the block or remove the ball.
     * @param beingHit - the block.
     * @param hitter - the ball.
     */
    void hitEvent(Block beingHit, Ball hitter);
}