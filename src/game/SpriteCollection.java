/**
 * @author Semyon Guretskiy.
 * ID - 335249255
 */

package game;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;
 /** The Sprite Collection of the game. Has the list of all sprites.
 */

public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Constructor. Start the new linked list fot sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Add the sprite to the list.
     * @param s - some new sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }
    /**
     * Notify the all sprites that time is passed.
     */
    public void notifyAllTimePassed() {
        for (Sprite x : sprites) {
            x.timePassed();
        }
    }
    /**
     * Draw all sprites on surface.
     * @param d - the surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite x : sprites) {
            x.drawOn(d);
        }
    }

    /**
     * Remove the sprite from list.
     * @param s - the object.
     */
    public void removeSprite(Sprite s) {
        List<Sprite> list = new ArrayList<>(this.sprites);
        list.remove(s);
        this.sprites = list;
    }
}