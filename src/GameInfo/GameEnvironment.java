//209492768 Shahar Chen
package GameInfo;
import Geometry.Line;
import Geometry.Rectangle;
import Sprites.Collidable;
import Geometry.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Game environment.
 */
public class GameEnvironment {
    private static final double THRESHOLD = 0.000001d;
    private List<Collidable> collidables;

    /**
     * Instantiates a new Game environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Add collidable to the list.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }
    /**
     * Removes a collidable from the game.
     *
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Gets closest collision.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // Initialize variables to keep track of the closest collision and its distance
        CollisionInfo closestCollision = null;
        double closestDistance = Double.POSITIVE_INFINITY;
        List<Collidable> collidablesCopy = new ArrayList<>(collidables);
        // Iterate over all collidables in the environment
        for (Collidable c : collidablesCopy) {
            // Get the collision rectangle for the current collidable
            Rectangle rect = c.getCollisionRectangle();

            // Calculate the intersection point between the trajectory and the collision rectangle
            Point intersection = trajectory.closestIntersectionToStartOfLine(rect);

            // If there is no intersection, continue to the next collidable
            if (intersection == null) {
                continue;
            }

            // Calculate the distance between the intersection point and the start of the trajectory
            double distance = trajectory.start().distance(intersection);

            // If this collision is closer than the current closest collision,
            // update closestCollision and closestDistance
            if (doubleEqualityBigger(closestDistance, distance)) {
                closestCollision = new CollisionInfo(intersection, c);
                closestDistance = distance;
            }
        }

        // Return the closest collision (or null if there were no collisions)
        return closestCollision;
    }
    /**
     * calculate double equality using threshold.
     *
     * @param num1 the num 1
     * @param num2 the num 2
     * @return the boolean
     */
    public static boolean doubleEqualityBigger(double num1, double num2) {
        return num1 - num2 > THRESHOLD;
    }
}
