// 209492768 Shahar Chen
package Geometry;
import java.util.List;
/**
 * The type Geometry.Line.
 */
public class Line {
    private static final double THRESHOLD = 0.000001d;
    private Point start;
    private Point end;

    /**
     * Instantiates a new Geometry.Line.
     *
     * @param start the start
     * @param end   the end
     */
// constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new Geometry.Line.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * calculate the length of the line.
     *
     * @return the double
     */
    public double length() {
        return Math.sqrt((this.start.getX() - this.end.getX()) * (this.start.getX() - this.end.getX())
                + (this.start.getY() - this.end.getY()) * (this.start.getY() - this.end.getY()));
    }

    /**
     * calculate the middle point of the line.
     *
     * @return the point
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * Start point.
     *
     * @return the point
     */
    public Point start() {
        return new Point(this.start.getX(), this.start.getY());
    }

    /**
     * End point.
     *
     * @return the point
     */
    public Point end() {
        return new Point(this.end.getX(), this.end.getY());
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other the other
     * @return the boolean
     */
    public boolean isIntersecting(Line other) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();
        double denominator = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        // Check if the lines are parallel
        if (denominator == 0) {
            // Check if the lines are coincident
            if (denominator == 0) {
                // Check if the line segments overlap
                return (doubleEquality(Math.max(x1, x2), Math.min(x3, x4))
                        ||
                        doubleEqualityBigger(Math.max(x1, x2), Math.min(x3, x4))
                        &&
                        doubleEquality(Math.max(x3, x4), Math.min(x1, x2))
                        ||
                        doubleEqualityBigger(Math.max(x3, x4), Math.min(x1, x2))
                        &&
                        doubleEquality(Math.max(y1, y2), Math.min(y3, y4))
                        ||
                        doubleEqualityBigger(Math.max(y1, y2), Math.min(y3, y4))
                        &&
                        doubleEquality(Math.max(y3, y4), Math.min(y1, y2))
                        ||
                        doubleEqualityBigger(Math.max(y3, y4), Math.min(y1, y2)));
            }
            return false;
        }
        double xNumerator = (x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4);
        double yNumerator = (x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4);
        double xIntersection = xNumerator / denominator;
        double yIntersection = yNumerator / denominator;
        return !(doubleEqualityBigger(Math.min(x1, x2), xIntersection))
                &&
                !(doubleEqualityBigger(xIntersection, Math.max(x1, x2)))
                &&
                !(doubleEqualityBigger(Math.min(y1, y2), yIntersection))
                &&
                !(doubleEqualityBigger(yIntersection, Math.max(y1, y2)))
                &&
                !(doubleEqualityBigger(Math.min(x3, x4), xIntersection))
                &&
                !(doubleEqualityBigger(xIntersection, Math.max(x3, x4)))
                &&
                !(doubleEqualityBigger(Math.min(y3, y4), yIntersection))
                &&
                !(doubleEqualityBigger(yIntersection, Math.max(y3, y4)));

    }
    /**
     * Checks if one line contains the other.
     *
     * @param p the p
     * @return the boolean
     */
    public boolean contains(Point p) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double px = p.getX();
        double py = p.getY();
        double minX = Math.min(x1, x2);
        double maxX = Math.max(x1, x2);
        double minY = Math.min(y1, y2);
        double maxY = Math.max(y1, y2);
        if (doubleEqualityBigger(minX, px) || doubleEqualityBigger(px, maxX)
                ||
                doubleEqualityBigger(minY, py) || doubleEqualityBigger(py, maxY)) {
            return false;
        }

        // Calculate the distance from the point to the line
        double distance = Math.abs((x2 - x1) * (y1 - py) - (x1 - px) * (y2 - y1))
                /
                Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

        // Check if the distance is within a threshold
        return !(doubleEqualityBigger(distance, THRESHOLD));
    }

    /**
     * Returns the intersection point if the lines intersect,and null otherwise.
     *
     * @param other the other
     * @return the point
     */
    public Point intersectionWith(Line other) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();
        if (this.equals(other)) {
            return null;
        }
        double denominator = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        // Check if the lines are parallel
        if (denominator == 0) {
            if (this.contains(other.start) && this.contains(other.end)) {
                return null;
            }
            if (other.contains(this.start) && other.contains(this.end)) {
                return null;
            }
            if (this.start.equals(other.start) || this.start.equals(other.end)
                    || this.end.equals(other.start) || this.end.equals(other.end)) {
                return this.start.equals(other.start) ? this.start : this.end;
            }
            return null;
        }


        double xNumerator = (x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4);
        double yNumerator = (x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4);

        double xIntersection = xNumerator / denominator;
        double yIntersection = yNumerator / denominator;

        // Check if the intersection point is within the bounds of the line segments
        if ((doubleEqualityBigger(Math.min(x1, x2), xIntersection))
                ||
                (doubleEqualityBigger(xIntersection, Math.max(x1, x2)))
                ||
                (doubleEqualityBigger(Math.min(y1, y2), yIntersection))
                ||
                (doubleEqualityBigger(yIntersection, Math.max(y1, y2)))
                ||
                (doubleEqualityBigger(Math.min(x3, x4), xIntersection))
                ||
                (doubleEqualityBigger(xIntersection, Math.max(x3, x4)))
                ||
                (doubleEqualityBigger(Math.min(y3, y4), yIntersection))
                ||
                (doubleEqualityBigger(yIntersection, Math.max(y3, y4)))) {
            return null;
        }

        return new Point(xIntersection, yIntersection);
    }

    /**
     * checks if lines are equal.
     *
     * @param other the other
     * @return the boolean
     */
    public boolean equals(Line other) {
        return (doubleEquality(this.start.getX(), other.start().getX())
                && doubleEquality(this.start.getY(), other.start().getY())
                &&
                ((doubleEquality(this.end.getX(), other.end().getX())
                        && doubleEquality(this.end.getY(), other.end().getY()))
                        ||
                        (doubleEquality(this.start.getX(), other.end().getX())
                                && doubleEquality(this.start.getY(), other.end().getY())
                                &&
                                (doubleEquality(this.end.getX(), other.start().getX())
                                        && doubleEquality(this.end.getY(), other.start().getY())))));
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

    /**
     * Returns the closest intersection point of this line with the given rectangle to the start of the line.
     * If this line does not intersect with the rectangle, returns null.
     *
     * @param rect the rectangle to check intersection with
     * @return the closest intersection point to the start of the line, or null if there is no intersection
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        // Calculate the distance between the start of the line and each intersection point
        double closestDistance = Double.POSITIVE_INFINITY;
        Point closestPoint = null;
        for (Point point : intersectionPoints) {
            double distance = this.start.distance(point);
            if (doubleEqualityBigger(closestDistance, distance)) {
                closestDistance = distance;
                closestPoint = point;
            }
        }
        return closestPoint;
    }

    /**
     * Is intersecting boolean.
     *
     * @param rec the rec
     * @return the boolean
     */
    public boolean isIntersecting(Rectangle rec) {
        Line[] sides = rec.getSides();

        for (Line side : sides) {
            if (this.isIntersecting(side)) {
                return true;
            }
        }

        return false;
    }

    /**
     * checks if the point is located on the line.
     *
     * @param point the point
     * @return the boolean
     */
    public boolean isOnLine(Point point) {
        double x1 = this.start().getX();
        double y1 = this.start().getY();
        double x2 = this.end().getX();
        double y2 = this.end().getY();
        double px = point.getX();
        double py = point.getY();

        // Calculate the equation of the line
        double a = y1 - y2;
        double b = x2 - x1;
        double c = x1 * y2 - x2 * y1;

        // Check if the point is on the line
        double distance = Math.abs(a * px + b * py + c) / Math.sqrt(a * a + b * b);
        return doubleEquality(distance, THRESHOLD) || doubleEqualityBigger(distance, THRESHOLD);

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
