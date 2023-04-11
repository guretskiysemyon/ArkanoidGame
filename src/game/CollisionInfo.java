/**
 * @author Semyon Guretskiy.
 * ID - 335249255
 */
package game;

import geometry.Point;

/** Class that keep the information about collision. The object and point.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable object;

    /**
     * Constructor of class.
     * @param p - the collision point
     * @param object - the collision object
     */
    public CollisionInfo(Point p, Collidable object) {
        this.collisionPoint = p;
        this.object = object;
    }
    //accessors
    /**
     * Return the collision point.
     * @return point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Return the collidable object involved in the collision.
     * @return Collidable
     */
    public Collidable collisionObject() {
        return this.object;
    }
}