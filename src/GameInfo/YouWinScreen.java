//209492768 Shahar Chen
package GameInfo;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Game over screen.
 */
public class YouWinScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private int score;

    /**
     * Instantiates a new Game over screen.
     *
     * @param keyboardSensor the keyboard sensor
     * @param score          the score
     */
    public YouWinScreen(KeyboardSensor keyboardSensor, int score) {
        this.keyboard = keyboardSensor;
        this.stop = false;
        this.score = score;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "You Win! your score is " + score, 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
