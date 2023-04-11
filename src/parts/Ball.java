/**
 * @author Semyon Guretskiy.
 * ID - 335249255
 */

package parts;
import biuoop.DrawSurface;
import game.CollisionInfo;
import game.GameLevel;
import game.GameEnvironment;
import game.Sprite;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import instruments.Calculations;
import instruments.Velocity;

/** Class Ball.
 */
public class Ball implements Sprite {
    private int r;
    private Point center;
    private java.awt.Color color;
    private Velocity v;
    private Line trajectory;
    private GameEnvironment territory;
    private GameLevel gameLevel;

    /**
     * Constructor by Point, size and color.
     * @param center of ball (x, y)
     * @param r - size of ball
     * @param color - color of ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * Constructor by x,y,size,color.
     * @param x  of center
     * @param y of center
     * @param r size of ball
     * @param color of ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.r = r;
        this.center = new Point(x, y);
        this.color = color;
    }

    /**
     * Set velocity by object Velocity.
     * @param velocity of ball
     */
    public void setVelocity(Velocity velocity) {
        this.v = velocity;
        Point p = new Point(this.center.getX() + this.v.getDx(), this.center.getY() + this.v.getDy());
        this.trajectory = new Line(this.center, p);
    }

    /**
     * Another set velocity by delta x and delta y.
     * @param dx of velocity per one step
     * @param dy of velocity per one step
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
        Point p = new Point(this.center.getX() + dx, this.center.getY() + dy);
        this.trajectory = new Line(this.center, p);
    }

    /**
     * Set Environment to ball.
     * @param t - territory of game
     */
    public void setGameEnvironment(GameEnvironment t) {
        this.territory = t;
    }

    // accessors

    /**
     * Get X of center.
     * @return x of center
     */
    public double getX() {
        return this.center.getX();
    }

    /**
     * Get Y of center.
     * @return y of center
     */
    public double getY() {
        return this.center.getY();
    }

    /**
     * Get size of ball.
     * @return this radius
     */
    public int getSize() {
        return this.r;
    }
    /**
     * Get velocity of this ball.
     * @return velocity (v) of this ball
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * get the trajectory of ball.
     * @return the trajectory
     */
    public Line getTrajectory() {
        return this.trajectory;
    }
    /**
     * Draw the ball on surface.
     * @param surface of ball
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.getX(), (int) this.getY(), this.r);
    }

    /**
     * Set the trajectory of ball.
     */
    public void setTrajectory() {
        Point p = new Point(this.center.getX() + this.v.getDx(), this.center.getY() + this.v.getDy());
        this.trajectory = new Line(this.center, p);
    }

    /**
     * If ball cross the wall of paddle.
     * @return Fixed Point
     */
    private Point pointToPaddle() {
        Rectangle rect = this.gameLevel.getPaddle().getCollisionRectangle();
        if (rect.isInside(this.center)) {    //ball is in the center
            if (Math.abs(this.center.getX() - rect.getUpperLeft().getX()) <  //which wall is closer
                    Math.abs(this.center.getX() - (rect.getUpperLeft().getX() + rect.getWidth()))) {
                return new Point(rect.getUpperLeft().getX() - this.r, this.center.getY());  //left
            }
            return new Point(rect.getUpperLeft().getX() + rect.getWidth() + this.r, this.center.getY()); //right
        }
        return null;
    }
    /**
     * move the ball.
     */
    public void moveOneStep() {
        CollisionInfo impact = this.territory.getClosestCollision(this.trajectory); //no impact
        if (impact == null) {
            this.center = this.v.applyToPoint(this.center);
            setTrajectory();
            return;
        }   //impact to blocks
        Point point = impact.collisionPoint();
        Rectangle block = impact.collisionObject().getCollisionRectangle();
        Point nextLocation = Calculations.getPointInTurn(block, point, this);
        Velocity newV = impact.collisionObject().hit(this, point, this.v);
        this.center = nextLocation;
        setVelocity(newV);
    }

    /**
     * setGame to ball.
     * @param g the game
     */
    public void setGame(GameLevel g) {
        this.gameLevel = g;
    }

    /**
     * notify to ball.
     */
    @Override
    public void timePassed() {
        Point nov = pointToPaddle();    //if paddle crush all
        if (nov != null) {
            this.center = nov;  //new location
            double dx;
            dx = this.gameLevel.getPaddle().getDx() + 1;
            double dy = this.v.getDy();
            setVelocity(dx, dy); //new speed
        }
        this.moveOneStep();
    }

    /**
     * add ball to game.
     * @param g of ball.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}