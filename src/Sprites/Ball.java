//209492768 Shahar Chen
package Sprites;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import GameInfo.GameEnvironment;
import GameInfo.CollisionInfo;
import GameInfo.GameLevel;
import biuoop.DrawSurface;

/**
 * Creating the type Ball.
 */
public class Ball implements Sprite {
    private static final int TOP = 0;
    private static final int RIGHT = 1;
    private static final int BOTTOM = 2;
    private static final int LEFT = 3;
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment environment;

    /**
     * Instantiates a new Ball.
     *
     * @param center      the center
     * @param r           the r
     * @param color       the color
     * @param environment the environment
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment environment) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.environment = environment;
    }

    /**
     * Instantiates a new Ball.
     *
     * @param x           the x
     * @param y           the y
     * @param r           the r
     * @param color       the color
     * @param environment the environment
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment environment) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.environment = environment;
    }

    /**
     * Get center x.
     *
     * @return the x
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Get center y.
     *
     * @return the y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Get size int.
     *
     * @return the radius
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Get color.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface the surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
    }

    /**
     * Sets velocity.
     *
     * @param v the v
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Move the ball one step according to its velocity,
     * and make sure it doesn't go out of bounds or collide with collidables.
     */
    @Override
    public void timePassed() {
        if (this.v == null) {
            return;
        }
        Point newCenter = this.v.applyToPoint(this.center);
        Line trajectory = new Line(this.center, newCenter);
        CollisionInfo collision = this.environment.getClosestCollision(trajectory);

        if (collision != null) {
            Point collisionPoint = collision.collisionPoint();
            Collidable collisionObject = collision.collisionObject();
            Rectangle rect = collisionObject.getCollisionRectangle();
            Line[] sides = rect.getSides();
            // collision check
            if (sides[TOP].isOnLine(collisionPoint) || sides[BOTTOM].isOnLine(collisionPoint)
                    ||
                    sides[RIGHT].isOnLine(collisionPoint) || sides[LEFT].isOnLine(collisionPoint)) {
                this.v = collisionObject.hit(this, collisionPoint, this.v);
            }
            newCenter = this.v.applyToPoint(collisionPoint);
        }
        this.center = newCenter;
    }
    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * Remove from game.
     *
     * @param gameLevel the game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}