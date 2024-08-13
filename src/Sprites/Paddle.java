//209492768 Shahar Chen
package Sprites;
import Geometry.Rectangle;
import Geometry.Velocity;
import Geometry.Point;
import GameInfo.GameLevel;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private static final int BORDER_WIDTH = 20;
    private static final int SCREEN_WIDTH = 780;
    private static final int REGIONS = 5;
    private static final int LEFT_MOST = 1;
    private static final int MIDDLE_LEFT = 2;
    private static final int MIDDLE_RIGHT = 4;
    private static final int RIGHT_MOST = 5;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private java.awt.Color color;
    private int speed;

    /**
     * Instantiates a new Paddle.
     *
     * @param rect     the rect
     * @param color    the color
     * @param speed    the speed
     * @param keyboard the keyboard
     */
    public Paddle(Rectangle rect, java.awt.Color color, int speed, biuoop.KeyboardSensor keyboard) {
        this.rect = rect;
        this.color = color;
        this.speed = speed;
        this.keyboard = keyboard;
    }

    /**
     * Move the paddle to the left.
     */
    public void moveLeft() {
        if (rect.getUpperLeft().getX() > BORDER_WIDTH) {
            rect = new Rectangle(new Point(rect.getUpperLeft().getX() - speed, rect.getUpperLeft().getY()),
                    rect.getWidth(), rect.getHeight());
        }
    }

    /**
     * Move the paddle to the right.
     */
    public void moveRight() {
        if (rect.getUpperRight().getX() < SCREEN_WIDTH) {
            rect = new Rectangle(new Point(rect.getUpperLeft().getX() + speed, rect.getUpperLeft().getY()),
                    rect.getWidth(), rect.getHeight());
        }
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        int x = (int) rect.getUpperLeft().getX();
        int y = (int) rect.getUpperLeft().getY();
        int width = (int) rect.getWidth();
        int height = (int) rect.getHeight();
        d.setColor(color);
        d.fillRectangle(x, y, width, height);
        d.setColor(java.awt.Color.BLACK);
        d.drawRectangle(x, y, width, height);
    }

   @Override
    public Rectangle getCollisionRectangle() {
        return rect;
    }

   @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // determine which region the collision point is in
        int region = (int) ((collisionPoint.getX() - this.getCollisionRectangle().getUpperLeft().getX())
                /
                (this.getCollisionRectangle().getWidth() / REGIONS)) + 1;

        // calculate the new angle based on the region
        double newAngle;
        if (region == LEFT_MOST) {
            newAngle = 300;
        } else if (region == MIDDLE_LEFT) {
            newAngle = 330;
        } else if (region == MIDDLE_RIGHT) {
            newAngle = 30;
        } else if (region == RIGHT_MOST) {
            newAngle = 60;
        } else {
            // the ball hit the middle region, so just reverse the vertical direction
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        // calculate the new velocity based on the new angle
        double speed = currentVelocity.getSpeed();
        return Velocity.fromAngleAndSpeed(newAngle, speed);
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
