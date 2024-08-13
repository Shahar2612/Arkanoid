//209492768 Shahar Chen
package GameInfo;
import biuoop.DrawSurface;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {
    private double countdownDuration;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private int currentCount;
    private long startTime;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countdownDuration = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.currentCount = countFrom;
        this.startTime = System.currentTimeMillis();
    }
@Override
public void doOneFrame(DrawSurface d) {
    this.gameScreen.drawAllOn(d);
    long elapsedTime = System.currentTimeMillis() - this.startTime;
    double numberDuration = this.countdownDuration / this.countFrom;

    // Calculate the current number based on the elapsed time
    int currentNumber = (int) (elapsedTime / (numberDuration * 1000)) + 1;

    // Update the current count if the number has changed
    if (currentNumber != this.currentCount) {
        this.currentCount = currentNumber;
    }
    // Draw the current count on the screen
    String countText = Integer.toString(this.countFrom - this.currentCount + 1);
    int textSize = 64;
    int textX = d.getWidth() / 2 - (textSize / 2);
    int textY = d.getHeight() / 2 + (textSize / 2);
    d.drawText(textX, textY, countText, textSize);

    // Check if the countdown has finished
    if (this.currentCount > this.countFrom) {
        this.stop = true;
    }
}
    @Override

    public boolean shouldStop() {
        return this.stop;
    }
}

