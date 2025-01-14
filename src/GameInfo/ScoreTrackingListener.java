package GameInfo;

import Sprites.Ball;
import Sprites.Block;
import Sprites.HitListener;

/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
@Override
    public void hitEvent(Block beingHit, Ball hitter) {
    currentScore.increase(5);
    }
}
