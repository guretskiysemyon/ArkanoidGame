/**
 * @author Semyon Guretskiy.
 * ID - 335249255
 */
package game;
import biuoop.DrawSurface;
 /** The Sprite interface.
 */
public interface Sprite {
    /**
     * draw the sprite object on the surface.
     * @param d - the surface
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the object that time passed.
     */
    void timePassed();
}