// 209492768 Shahar Chen
package Geometry;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Geometry.Rectangle.
 */
public class Rectangle {
    private static final double THRESHOLD = 0.000001d;
    private static final int TOP = 0;
    private static final int RIGHT = 1;
    private static final int BOTTOM = 2;
    private static final int LEFT = 3;
    private static final int SIDES = 4;
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Create a new rectangle with location and width/height..
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points, with the specified line.
     *
     * @param line the line
     * @return the java . util . list
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();
        Line[] sides = this.getSides();
        // Check for intersection with each side of the rectangle
        for (Line side : sides) {
            if (line.isIntersecting(side)) {
                Point intersectionPoint = line.intersectionWith(side);
                if (intersectionPoint == null) {
                    if (doubleEqualityBigger(side.start().distance(line.end()), side.end().distance(line.end()))) {
                        intersectionPoint = line.end();
                    } else {
                        intersectionPoint = line.start();
                    }
                }
                    intersectionPoints.add(intersectionPoint);
            }
        }
        return intersectionPoints;
    }

    /**
     * Get width.
     *
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Get height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Gets upper right.
     *
     * @return the upper right point of the rectangle
     */
    public Point getUpperRight() {
        return new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
    }

    /**
     * Gets lower right.
     *
     * @return the lower right point of the rectangle
     */
    public Point getLowerRight() {
        return new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
    }

    /**
     * Gets lower left.
     *
     * @return the lower left point of the rectangle
     */
    public Point getLowerLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
    }

    /**
     * Get sides line.
     *
     * @return the sides of the rectangle
     */
    public Line[] getSides() {
        Line[] sides = new Line[SIDES];
        // top side
        sides[TOP] = new Line(this.getUpperLeft(), this.getUpperRight());
        //right side
        sides[RIGHT] = new Line(this.getUpperRight(), this.getLowerRight());
        //bottom side
        sides[BOTTOM] = new Line(this.getLowerLeft(), this.getLowerRight());
        //left side
        sides[LEFT] = new Line(this.getUpperLeft(), this.getLowerLeft());
        return sides;
    }

    /**
     * Double equality using threshold.
     *
     * @param num1 the num 1
     * @param num2 the num 2
     * @return the boolean
     */
    public static boolean doubleEqualityBigger(double num1, double num2) {
        return num1 - num2 > THRESHOLD;
    }
}
