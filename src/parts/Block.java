/**
 * @author Semyon Guretskiy.
 * ID - 335249255
 */

package parts;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import game.Collidable;
import game.GameLevel;
import game.Sprite;
import geometry.Point;
import geometry.Rectangle;
import instruments.Calculations;
import instruments.Velocity;
import listeners.HitListener;
import listeners.HitNotifier;

/** Class of block. Is collidabla and sprite.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle form;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * Constructor.
     * @param form - the rectangle of block
     * @param color - the color of block
     */
    public Block(Rectangle form, Color color) {
        this.form = form;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }
    //accessors

    /**
     * return the form of this block.
     * @return Rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.form;
    }

    /**
     * return the color of this block.
     * @return this color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * The function belongs to the Collidable implement. Return the velocity of the object
     * after collision with this block.
     * @param hitter  - ths ball that hit
     * @param collisionPoint - point of the collision.
     * @param currentVelocity - the velocity of the object.
     * @return the new Velocity.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        String ans = Calculations.witchWall(this.form, collisionPoint);
        Velocity v = currentVelocity.turn(ans);
        this.notifyHit(hitter);
        return v;
    }

    /**
     * Set the new upper left point of rectangle of this block.
     * @param p - new point of location.
     */
    public void setLocation(Point p) {
        if (p != null) {
            this.form = new Rectangle(p, this.form.getWidth(), this.form.getHeight());
        }
    }

    /**
     * drow this block on surface.
     * @param d - the surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
       this.form.drawRectangle(d, this.color);
    }

    /**
     * add this block to game.
     * @param gameLevel - the game to add.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
    }

    /**
     * Now for no use. Will be used later.
     */
    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Notify the all listeners that was hit to this block.
     * @param hitter - the ball that hit this block,
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}