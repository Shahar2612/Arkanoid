package Sprites;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
/**
 * The interface Collidable.
 */
public interface Collidable {
    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */

    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     *The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @param hitter
     * @return the velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
