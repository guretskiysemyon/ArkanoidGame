/**
 * @author Semyon Guretskiy
 * ID - 335249255
 */
package geometry;
import instruments.Calculations;

 /** Line by two points/ Section.
 */
public class Line {

    /** HALF - is a constant value for calculation.
     * start - is star of the line ( x of the start is always smaller that x of the end)
     * end - end of the line
     */
    static final double HALF = 0.5;
    private Point start;
    private Point end;

    /**
     * constructor by two points.
     * @param start Point of start
     * @param end Point of end
     */
    public Line(Point start, Point end) {
            this.start = start;
            this.end = end;

    }

    /**
     * Constructor by for coordinates.
     * @param x1 first x coordinate
     * @param y1 first y coordinate
     * @param x2 second x coordinate
     * @param y2 second y coordinate.
     */
    public Line(double x1, double y1, double x2, double y2) {
            this.start = new Point(x1, y1);
            this.end = new Point(x2, y2);
    }
    /**
     * Return the start point of the this line.
     * @return this.start
     */
    public Point start() {
        return this.start;
    }

    /**
     * Return the end point of this line.
     * @return this. end
     */
    public Point end() {
        return this.end;
    }
    /**
     * length of the line.
     * @return length of the line (by calling the distance method from Point class)
     */
    public double length() {
        return start.distance(end);
    }
    /**
     * Returns the middle point of the line. By formula (x1+x2)/2 and the same for y.
     * @return Point - middle of the line.
     */
    public Point middle() {
        double middleX = (start.getX() + end.getX()) * HALF;
        double middleY = (start.getY() + end.getY()) * HALF;
        return new Point(middleX, middleY);
    }
    /**
     * equals -- return true is the lines are equal, false otherwise.
     * @param other Line
     * @return boolean - if the lines are equals.
     */
    public boolean equals(Line other) {
        if ((this.start.equals(other.start)) && (this.end.equals(other.end))) {
            return true;
        } else return (this.start.equals(other.end)) && (this.end.equals(other.start));
    }
    /**
     * Check if there is intersection between two lines. Uses Calculation class for it's calculation.
     * @param other Line
     * @return boolean (if there is intersection between two lines)
     */
    public boolean isIntersecting(Line other) {
        if (this.equals(other)) { //if the lines is equals => there is intersection
            return true;
        }

        /*
         * Check if two lines are points(start=end). If they are then there is no intersection
         * because we checked early if they are equals and they are not. Return false.
         */
        if ((this.start.equals(this.end)) && (other.start.equals(other.end))) {
            return false;
        }

        /*
         * Check the determinant is 0. Then two lines are either parallel or adjacent or one of them is point.
         * Then we will check if the start or end if this line is on the other line or
         * the opposite. Else return false. Use Calculation class.
         */
        if (Calculations.detIsZero(this, other)) {
            if ((Calculations.isPointOnLine(this, other.start)) || (Calculations.isPointOnLine(this, other.end))) {
                return true;
            }
            return (Calculations.isPointOnLine(other, start)) || (Calculations.isPointOnLine(other, end));
        }

        /*
         * If came here, then should be intersection between infinite line. But we have the finite lines
         * so we find the point and then check if the point is on the section (one of the lines).Use calculation.
         */
        Point intersection = Calculations.calIntersection(this, other);
        return Calculations.isPointOnSection(this, intersection)
                && Calculations.isPointOnSection(other, intersection);
    }

    /**
     * Find the intersection point between two lines if there is.
     * @param other Line
     * @return Point intersection or null if: 1-no intersection, 2-intersection is more than one point.
     *
     */
    public Point intersectionWith(Line other) {
        if (!this.isIntersecting(other)) { //use the method isIntersection to check if there is.
            return null;
        }
        if (this.equals(other)) { // if lines are equals
            if (this.start.equals(this.end)) { //if they are and start=end then they are 2 points. Return start.
                return this.start;
            } // else they are equals so there are more than one point => return false.
            return null;
        }

        /*
         * Check the determinant is 0. Then two lines are either parallel or adjacent or one of them is point.
         * First and second - they have only one point - start or end. Can check because we related the x.
         * Third and Forth - one of them are point and other is not. Return this point cause we already
         * know there is intersection. Else return null - more then one point.
         */

        if (Calculations.detIsZero(this, other)) {
            if (this.start.equals(this.end) && Calculations.isPointOnSection(other, this.start)) {
                return this.start;
            }
            if (other.start.equals(other.end) && Calculations.isPointOnSection(this, other.start)) {
                return other.start;
            }
            if (this.start.equals(other.end) && !Calculations.isPointOnSection(other, this.end)) {
                return this.start;
            }
            if (this.end.equals(other.start) && !Calculations.isPointOnSection(other, this.start)) {
                return this.end;
            }
            return null;
        }
        /*
         * If came here, then should be intersection between infinite line. But we have the finite lines
         * so we find the point and then check if the point is on the section (one of the lines).Use calculation.
         */
        Point intersection = Calculations.calIntersection(this, other);
        if (Calculations.isPointOnSection(this, intersection) && Calculations.isPointOnSection(other, intersection)) {
            return intersection;
        }
        return null;
    }

    /**
     * Find and return the closest point to start of the line.
     * @param rect - Rectangle that we check intersection with.
     * @return  Point - the closest point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> list = rect.intersectionPoints(this); // get the all points of intersections.
        if (list.isEmpty()) { //if no points return null
            return null;
        }
        Point p = null;
        double min = this.start.distance(this.end); //start with the max value of the distance= from start to end.
        for (Point x : list) {       //check every point in list
            double k = this.start.distance(x);      //temp value
            if (k < min || Calculations.lessAccurate(k, min)) {   //func check less accurate partition.
                min = k;
                p = x;
            }
        }
        //if find some point return, else null.
        return p;
    }
}