/**
 * @author Semyon Guretskiy.
 * ID - 335249255
 */
package game;
import parts.Ball;
import geometry.Point;
import geometry.Rectangle;
import instruments.Velocity;

 /** interface to Collidable objects.
 */
public interface Collidable {
    /**
     * Return the form of collidable object.
     * @return Rectangle.
     */
    Rectangle getCollisionRectangle();
    /**
     * Return the new velocity of object after collision with collidable object.
     * @param hitter - the object that hit the object. Ball in our game.
     * @param collisionPoint - the point of collision.
     * @param currentVelocity - the curentVelocity
     * @return - new Velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}