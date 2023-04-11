/**
 * @author Semyon Guretskiy.
 * ID: 336249255
 */
package listeners;
import game.GameLevel;
import instruments.Counter;
import parts.Ball;
import parts.Block;

/**
 * Block Remover. the listener of blocks that remove block after hit with ball.
 */
public class BlockRemover implements HitListener {
    private static final int DECREASE = 1;
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Constructor.
     * @param gameLevel  - the game.
     * @param removedBlocks - Counter to number of blocks that are in the game.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.gameLevel.removeBlock(beingHit);
        this.remainingBlocks.decrease(DECREASE);
        beingHit.removeHitListener(this);
    }

}