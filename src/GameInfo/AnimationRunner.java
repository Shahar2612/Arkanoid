//209492768 Shahar Chen
package GameInfo;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The type Animation runner.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;


    /**
     * Instantiates a new Animation runner.
     *
     * @param g the g
     */
    public AnimationRunner(GUI g) {
        this.gui = g;
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     * Run the game.
     *
     * @param animation the animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondsLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondsLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondsLeftToSleep);
            }
        }
    }
}
