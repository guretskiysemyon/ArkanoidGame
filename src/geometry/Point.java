/**
 * @author Semyon Guretskiy
 * ID - 335249255
 */
package geometry;

 /** Two dimensional point.
 */
public class Point {
    private static final double EPSILON = Math.pow(10, -5);

    /**members.
     * x - x coordinate of point
     * y - y coordinate of point
     */
    private double x;
    private double y;

    /**Constructors.
     * @param x - the x coordinate of future point
     * @param y - the y coordinate of future point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**Return this X.
     * @return this.x
     */
    public double getX() {
        return this.x;
    }

    /**Return this Y.
     * @return this y
     */
    public double getY() {
        return this.y;
    }

    /** distance -- return the distance of this point to the other point.
     *  @param other (Point)
     * @return distance between two points
     *  Method uses the formula - sqrt(x^2 + y^2)
     */
    public double distance(Point other) {
        double result;
        double x2 = other.getX();
        double y2 = other.getY();
        result = (((this.x - x2) * (this.x - x2)) + ((this.y - y2) * (this.y - y2))); //calculation
        result = Math.sqrt(result);
        return result;
    }

    /** equals -- return true is the points are equal, false otherwise.
     * @param other (Point)
     * @return boolean (if those points are equals)
     */
    public boolean equals(Point other) {
        if (other == this) {
            return true;
        }
        boolean dx = Math.abs(this.x - other.getX()) < EPSILON;
        boolean dy = Math.abs(this.y - other.getY()) < EPSILON;
        return dx && dy;
    }
}