/**
 * @author Semyon Guretskiy.
 * ID: 336249255
 */
package listeners;

import parts.Ball;
import parts.Block;
import instruments.Counter;

/**
 * The Class that listen to hits and add the score.
 */
public class ScoreTrackingListener implements HitListener {
    private static final int HIT_SCORE = 5;
    private Counter currentScore;

    /**
     * Constructor.
     * @param scoreCounter - counter to score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(HIT_SCORE);
        beingHit.removeHitListener(this);
    }
}