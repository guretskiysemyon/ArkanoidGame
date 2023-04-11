/**
 * @author Semyon Guretskiy
 * I.D. 336249255
 */
package animation;

import biuoop.DrawSurface;

/**
 * The Animation interface for different animation in game.
 */
public interface Animation {
    /**
     * Draw one frame in the animation.
     * @param d -the surface to draw on
     */
    void doOneFrame(DrawSurface d);

    /**
     * Check if animation should stop or to continue.
     * @return boolean true if it should stop and false it's not.
     */
    boolean shouldStop();
}
