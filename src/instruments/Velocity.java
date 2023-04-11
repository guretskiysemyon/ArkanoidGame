/**
 * @author Semyon Guretskiy.
 * ID: 336249255
 */
package instruments;

import geometry.Point;

/*** Class Velocity for moving the ball.
 */
public class Velocity {
    /**
     * dx - change x coordinate per step.
     * dy - change y coordinate per step.
     */
    private double dx;
    private double dy;

    /**
     * Constructor by dx, and dy (changes coordinates).
     * @param dx of the ball per step.
     * @param dy of the ball per step.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * fromAngleAndSpeed create new object.
     * @param angle - direction of the ball
     * @param speed of the ball
     * @return the nes object Velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleRadian = Math.toRadians(angle);  //change to radians for next methods.
        double dx = Math.cos(angleRadian) * speed;
        double dy = Math.sin(angleRadian) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Get Dx - change x coordinate per step.
     * @return this.dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Get Dy - change y coordinate per step.
     * @return this.dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Return the needed velocity after collision. The result depend on direction.
     * @param direction -string that method that takes bt helping methods which says direction of collision.
     * @return Velocity  - new velocity
     */
    public Velocity turn(String direction) {
        switch (direction) { //switch case  by String
            case "Horizontal":
                return new Velocity(this.dx, (-1) * this.dy);
            case "Vertical":
                return new Velocity((-1) * this.dx, this.dy);
            default:
                return new Velocity((-1) * this.dx, (-1) * this.dy);
        }
    }

    /**
     * applyToPoint - apply the velocity for the taken point, and move it.
     * @param p - point that will be moved
     * @return new Point - (point in new position)
     */
    public Point applyToPoint(Point p) {
        double x = p.getX() + this.dx;
        double y = p.getY() + this.dy;
        Point result = new Point(x, y);
        return result;
    }
}