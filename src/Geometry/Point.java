//209492768 Shahar Chen
package Geometry;
/**
 * The type Point.
 */

public class Point {
    private static final double THRESHOLD = 0.000001d;
    private double x;
    private double y;

    /**
     * constructor, Instantiates a new Point.
     *
     * @param x the x
     * @param y the y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * calculate the distance of this point to the other point.
     *
     * @param other the other
     * @return the double
     */
    public double distance(Point other) {
        return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }

    /**
     * return true is the points are equal, false otherwise.
     *
     * @param other the other
     * @return the boolean
     */
    public boolean equals(Point other) {
        return doubleEquality(this.x, other.x) && doubleEquality(this.y, other.y);
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return this.y;
    }


    /**
     * Double equality using threshold.
     *
     * @param first the first
     * @param sec   the sec
     * @return the boolean
     */
    public boolean doubleEquality(double first, double sec) {
        return Math.abs(first - sec) < THRESHOLD;
    }
}
