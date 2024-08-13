//209492768 Shahar Chen
package GameInfo;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private biuoop.KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean shouldStop;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param keyboardSensor the keyboard sensor
     * @param key            the key
     * @param animation      the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboardSensor, String key, Animation animation) {
        this.keyboardSensor = keyboardSensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
        this.shouldStop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.isAlreadyPressed) {
            if (!this.keyboardSensor.isPressed(this.key)) {
                this.isAlreadyPressed = false;
            }
        } else {
            if (this.keyboardSensor.isPressed(this.key)) {
                this.shouldStop = true;
            }
        }
        this.animation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        return this.animation.shouldStop();
    }
}

