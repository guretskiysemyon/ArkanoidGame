/**
 * @author Semyon Guretskiy
 * ID - 335249255
 */
package geometry;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

 /** Class of Shape Rectangle. Has it upper Left point and width and height.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructor of object.
     * @param upperLeft point of Rectangle.
     * @param width of Rectangle
     * @param height of rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Private Helping Function that crate sides of this Rectangle for some testings.
     * @return List of Lines
     */
    private java.util.List<Line> createSides() {
        List<Line> result = new ArrayList<Line>();  //new list
        Point p2 = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY()); //upperRight
        Point p3 = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height); //lowerRight
        Point p4 = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);    //lowerLeft
        result.add(new Line(this.upperLeft, p2));    //upper
        result.add(new Line(p4, p3));   //lower
        result.add(new Line(this.upperLeft, p4));   //left
        result.add(new Line(p2, p3));   //right
        return result;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
      * @param line which may be cross this rectangle
     * @return list of intersections points or null if there is no intersection
     * */
    public java.util.List<Point> intersectionPoints(Line line) {
      List<Point> result = new ArrayList<Point>();  //new list
      List<Line> sides = createSides(); //help function
      for (Line x : sides) {        //check for every side
          Point p = line.intersectionWith(x);
          if (p != null) {  //if no null so add to list of points
              result.add(p);
          }
      }
      return result;
    }
    //accessors

    /**
     * Return the width of this rectangle.
     * @return width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the height of this rectangle.
     * @return height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Return upper left point of this rectangle.
      * @return this getUpperLeft
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Check if point is inside of this rectangle.
     * @param p - point
     * @return true - if it is inside , other false.
     */
    public boolean isInside(Point p) {
        double x = p.getX();
        double y = p.getY();
        if (x >= this.upperLeft.getX() && x <= (this.upperLeft.getX() + this.width)) {
            if (y >= this.upperLeft.getY() && y <= this.upperLeft.getY() + this.height) {
                return true;
            }
        }
        return false;
    }

     /**
      * Draw rectangle.
      * @param d - surface to draw on.
      * @param color - the color of the rectangle.
      */
    public void drawRectangle(DrawSurface d, Color color) {
        d.setColor(color);
        d.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(), (int) this.width, (int) this.height);
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(), (int) this.width, (int) this.height);
    }

}