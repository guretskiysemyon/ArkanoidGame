/**
 * @author Semyon Guretski.
 * ID: 336249255
 */
package listeners;
import game.GameLevel;
import parts.Ball;
import parts.Block;
import instruments.Counter;

/**
 * Ball remover. Listener that remove the ball when it cross the limit of the screen.
 */
public class BallRemover implements HitListener {
    private static final int DECREASE = 1;
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Constrcutor.
     * @param g - the game.
     * @param c - Counter to the balls. Amount of balls in the game.
     */
    public BallRemover(GameLevel g, Counter c) {
        this.gameLevel = g;
        this.remainingBalls = c;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.gameLevel.removeBall(hitter);
        this.remainingBalls.decrease(DECREASE);
    }
}
