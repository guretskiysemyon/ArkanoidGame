/**
 * @author Semyon Guretskiy
 * ID - 335249255
 */
package instruments;

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import parts.Ball;
import parts.Paddle;

/** Class calculation that will be used like lybrary of some calculations.
 */
public class Calculations {
    /**
     * Compare to coordinates.
     * @param x the first
     * @param x1 the second
     * @return if they are true.
     */
    public static boolean lessAccurate(double x, double x1) {
        double epsilon = Math.pow(10, -8);
        return Math.abs(x - x1) <= epsilon;
    }
    /**
     * Static method checks if the determinant of two lines is 0.
     * @param l1 Line
     * @param l2 Line
     * @return boolan - true if determinant = 0, else false.
     */
    public static boolean detIsZero(Line l1, Line l2) {
        double a1 = l1.start().getY() - l1.end().getY(); //a = y1-y2 (of l1)
        double a2 = l2.start().getY() - l2.end().getY(); // (of l2)
        double b1 = l1.end().getX() - l1.start().getX(); //b1 = x2-x1 (of l1)
        double b2 = l2.end().getX() - l2.start().getX(); //of (l2)
        double det = a1 * b2 - b1 * a2; //calculation of determinant
        return det == 0;
    }

    /**
     * Check if some point on line/ section.
     * @param l1 Line
     * @param point Point
     * @return boolean - is on line - true, else false.
     */
    public static boolean isPointOnLine(Line l1, Point point) {
        double a = l1.start().getY() - l1.end().getY(); //a = x1-x2
        double b = l1.end().getX() - l1.start().getX(); //b = y2-y1
        double c = (l1.start().getX() * l1.end().getY()) - (l1.end().getX() * l1.start().getY()); //c = x1*y2-x2*y1
        double x = point.getX(); //coordinates of point
        double y = point.getY();
        double result = a * x + b * y + c; //this should be 0 if the point is on this linear function
        // second check point on the section.
        return (result == 0) && isPointOnSection(l1, point);
    }

    /**
     * When already know that point is on the linear function and want to check if it is on section.
     * @param l1 Section
     * @param point Point
     * @return boolean true- if point is on, else false.
     */
    public static boolean isPointOnSection(Line l1, Point point) {
        double x = point.getX(); //coordinates of point
        double y = point.getY();
        boolean forX;
        if (l1.start().getX() < l1.end().getX()) {
           forX = ((x > l1.start().getX() || lessAccurate(x, l1.start().getX()))
                   && (x < l1.end().getX() || lessAccurate(x, l1.end().getX())));
        } else {
            forX = ((x < l1.start().getX() || lessAccurate(x, l1.start().getX()))
                    && (x >= l1.end().getX() || lessAccurate(x, l1.end().getX())));
        }
         //x of point between start and end. x related.
        if (l1.end().getY() >= l1.start().getY()) { //y is not related. First - end y >= start y.
            if (forX && (y > l1.start().getY() || lessAccurate(y, l1.start().getY()))
                    && (y < l1.end().getY() || lessAccurate(y, l1.end().getY()))) { //check that y in rang
                return true;
            }
        } else { //Second - if start y > end y
            if (forX && (y < l1.start().getY() || lessAccurate(y, l1.start().getY()))
                    && (y >= l1.end().getY() || lessAccurate(y, l1.end().getY()))) { //check y in rang
                return true;
            }
        }
        return false;
    }

    /**
     * Calculate the intersection point between 2 lines. We should already know that det != 0.
     * @param l1 Line
     * @param l2 Line
     * @return Intersection Point
     */
    public static Point calIntersection(Line l1, Line l2) {
        double a1 = l1.start().getY() - l1.end().getY(); //a = x1-x2
        double a2 = l2.start().getY() - l2.end().getY();
        double b1 = l1.end().getX() - l1.start().getX(); //b = y2-y1
        double b2 = l2.end().getX() - l2.start().getX();
        double c1 = (l1.end().getX() * l1.start().getY()) - (l1.start().getX() * l1.end().getY()); //c = x1*y2-x2*y1
        double c2 = (l2.end().getX() * l2.start().getY()) - (l2.start().getX() * l2.end().getY());
        double det = a1 * b2 - b1 * a2; //determinant
        double detX = c1 * b2 - b1 * c2; // determinant for X
        double detY = a1 * c2 - c1 * a2; // determinant fot Y
        Point result = new Point(detX / det, detY / det); //intersection Point
        return result;
    }

    /**
     * Check which wall of the clock.
     * @param rect of the clock.
     * @param p collision point
     * @return the String about wall.
     */
    public static String witchWall(Rectangle rect, Point p) {
       double x = p.getX();
       double y = p.getY();
       if (lessAccurate(x, rect.getUpperLeft().getX()) ||   //if on the vertical
               lessAccurate(x, rect.getUpperLeft().getX() + rect.getWidth())) {
           if (lessAccurate(y, rect.getUpperLeft().getY()) ||    //if on the horizontal
                   lessAccurate(y, rect.getUpperLeft().getY() + rect.getHeight())) {
               return "Corner";
           }
           return "Vertical";
       }
       return "Horizontal";
    }

    /**
     * Point almost to block.
     * @param rect of block
     * @param p collision point
     * @param ball - ball
     * @return the point
     */
    public static Point getPointInTurn(Rectangle rect, Point p, Ball ball) {
        Velocity v = ball.getVelocity();
        int r = ball.getSize();
        String ans = witchWall(rect, p);
        double signX = Math.abs(v.getDx()) / v.getDx(); //sign of velocities
        double signY = Math.abs(v.getDy()) / v.getDy();
        double angle = Math.atan(v.getDy() / v.getDx());
        Line trj = ball.getTrajectory();
        double distanceAll = trj.length();
        double distancePart = trj.start().distance(p) - ball.getSize();
        double partition = distancePart / distanceAll;
        double xNew = ball.getX() + ball.getVelocity().getDx() * partition;
        double yNew = ball.getY() +  ball.getVelocity().getDy() * partition;    //partition to step
         switch (ans) {
            case "Vertical":
                double x = p.getX() - signX * r;
                return new Point(x, yNew);
            case "Horizontal":
                 double y = p.getY() - signY * r;
                 return new Point(xNew, y);
            default:
                 x = p.getX() - signX * (r * Math.cos(angle));
                 y = p.getY() - signY * (r * Math.sin(angle));
                return new Point(x, y);
        }
    }

    /**
     * Closest between points to start.
     * @param p1 first point
     * @param p2 second point
     * @param line line
     * @return the closest one
     */
    public static Point closestBetweenTwo(Point p1, Point p2, Line line) {
        Point start = line.start();
        double distanceOne = start.distance(p1);
        double distanceTwo = start.distance(p2);
        if (distanceOne < distanceTwo) {
            return p1;
        }
        return p2;
    }

    /**
     * which section of paddle.
     * @param paddle in game
     * @param p - collision point
     * @return the number of section
     */
    public static int paddleSection(Paddle paddle, Point p) {
        double part = paddle.getCollisionRectangle().getWidth() / 5;
        Point start = paddle.getCollisionRectangle().getUpperLeft();
        for (int i = 1; i <= 5; i++) {   //check which one
            Point end = new Point(start.getX() + part, start.getY());
            Line l = new Line(start, end);
            if (Calculations.isPointOnSection(l, p)) {
                return i;
            }
            start = end;
        }
        return 0;
    }

    /**
     * Velocity of ball after paddle.
     * @param ans which wall
     * @param v of ball
     * @return the new velocity
     */
    public static Velocity paddleVelocity(int ans, Velocity v) {
        double speed = Math.sqrt(Math.pow(v.getDx(), 2) + Math.pow(v.getDy(), 2));
        double angle;
        switch (ans) {
            case 1:
                angle = 210;
                break;
            case 2:
                angle = 240;
                break;
            case 4:
                angle = 300;
                break;
            case 5:
                angle = 330;
                break;
            default:
                return v.turn("Horizontal");
        }
        return Velocity.fromAngleAndSpeed(angle, speed);
    }
}