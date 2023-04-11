/**
 * @author Semyon Guretskiy.
 * ID - 335249255
 */
package game;
import java.util.ArrayList;
import java.util.List;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import instruments.Calculations;

 /** The environment of the game. Have the collidable object.
 */
public class GameEnvironment {
    private List<Collidable> objects;
    private Rectangle frame; //frame of the screen

    /**
     * Constructor of the Game Environment. Start new List for Collidable objects.
     */
    public GameEnvironment() {
        this.objects = new ArrayList<Collidable>();
    }

    /**
     * add the given collidable to the environment.
     * @param c  - new collidable
     */
    public void addCollidable(Collidable c) {
        this.objects.add(c);
    }

    /**
     * Get the closest collision by trajectory line.
     * @param trajectory of moving object
     * @return CollisionInfo of object with trajectory or null
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestPoint = null;      //point for collision info
        Collidable objCollision = null; //the object for info
        if (this.objects.isEmpty()) {   //if no objects in Game return null.
            return null;
        }
        for (Collidable x : this.objects) { //check every object for potential collision
            Rectangle rect = x.getCollisionRectangle(); //form of the block
            Point p = trajectory.closestIntersectionToStartOfLine(rect);  //the closest point to start, can be null.
            if (closestPoint == null && p != null) {    //for the first object with collision.
                closestPoint = p;
                objCollision = x;
            }
            if (closestPoint != null && p != null) {    //if there more than one object, check who is closer.
                closestPoint = Calculations.closestBetweenTwo(closestPoint, p, trajectory);
                if (closestPoint.equals(p)) {   //if the same distance then new object is object for info.
                    objCollision = x;
                }
            }
        }
        if (objCollision != null && closestPoint != null) { //if the parameters aren't null, then return Info.
            return  new CollisionInfo(closestPoint, objCollision);
        }
        return null;
    }

    /**
     * Remove collidable object from list.
     * @param c - the object.
     */
    public void removeCollidable(Collidable c) {
        List<Collidable> list = new ArrayList<>(this.objects);
        list.remove(c);
        this.objects = list;
    }
}