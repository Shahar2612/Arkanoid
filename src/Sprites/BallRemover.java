package Sprites;
import GameInfo.GameLevel;
import GameInfo.Counter;

/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter availableBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param gameLevel           the game
     * @param availableBalls the available balls
     */
    public BallRemover(GameLevel gameLevel, Counter availableBalls) {
        this.gameLevel = gameLevel;
        this.availableBalls = availableBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel); // Remove the ball from the game
        availableBalls.decrease(1); // Decrease the available balls count
    }
}