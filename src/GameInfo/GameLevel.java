//209492768 Shahar Chen
package GameInfo;
import Sprites.BallRemover;
import Sprites.Paddle;
import Sprites.Ball;
import Sprites.BlockRemover;
import Sprites.Sprite;
import Sprites.Collidable;
import Sprites.Block;
import Sprites.ScoreIndicator;
import Geometry.Rectangle;
import Geometry.Point;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Game.
 */
public class GameLevel implements Animation {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int STARTX = 400;
    private static final int STARTY = 500;
    private static final int RADIUS = 8;
    private static final int PADDLE_HEIGHT = 20;
    private static final int BORDER_WIDTH = 20;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation level;


    /**
     * Instantiates a new Game level.
     *
     * @param level the level
     * @param gui   gui.
     * @param currentScore score.
     */
    public GameLevel(LevelInformation level, GUI gui, Counter currentScore) {
        this.level = level;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = gui;
        this.remainingBlocks = new Counter(level.numberOfBlocksToRemove());
        this.remainingBalls = new Counter(level.numberOfBalls());
        this.score = currentScore;
        this.runner = new AnimationRunner(this.gui);
        this.running = false;
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * Add collidable to the list.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add sprite to the list.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }
    /**
     * Initialize the game.
     */
    public void initialize() {
        Paddle paddle = new Paddle(new Rectangle(level.location(), level.paddleWidth(), PADDLE_HEIGHT),
                Color.BLACK, level.paddleSpeed(), gui.getKeyboardSensor());
        paddle.addToGame(this);
        //create scoreIndicator
        ScoreIndicator scoreIndicator = new ScoreIndicator(score);
        this.addSprite(scoreIndicator);
        scoreIndicator.addToGame(this);
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(score);
            for (Block block: level.blocks()) {
                block.addToGame(this);
                    BlockRemover blockRemover = new BlockRemover(this, remainingBlocks); // Create BlockRemover
                    block.addHitListener(blockRemover); // Add BlockRemover as a listener
                    block.addHitListener(scoreListener);
            }
        // Create the border blocks
        Block leftBlock = new Block(new Rectangle(new Point(0, 20), BORDER_WIDTH, SCREEN_HEIGHT), Color.GRAY);
        Block rightBlock = new Block(new Rectangle(new Point(SCREEN_WIDTH - BORDER_WIDTH, 20),
                BORDER_WIDTH, SCREEN_HEIGHT), Color.GRAY);
        Block topBlock = new Block(new Rectangle(new Point(0, 20), SCREEN_WIDTH, BORDER_WIDTH), Color.GRAY);
        this.addCollidable(leftBlock);
        this.addCollidable(rightBlock);
        this.addCollidable(topBlock);
        this.addSprite(leftBlock);
        this.addSprite(rightBlock);
        this.addSprite(topBlock);
        // Create the death-region block
        Block deathRegion = new Block(new Rectangle(new Point(0, SCREEN_HEIGHT - BORDER_WIDTH),
                SCREEN_WIDTH, BORDER_WIDTH), Color.WHITE);
        deathRegion.addToGame(this);

        // Create the BallRemover and register it as a listener for the death-region block
        BallRemover ballRemover = new BallRemover(this, remainingBalls);
        deathRegion.addHitListener(ballRemover);
    }

    /**
     * Run the game.
     */
    public void run() {
        this.createBallsOnTopOfPaddle();
        CountdownAnimation countdownAnimation = new CountdownAnimation(2, 3, this.sprites);
        this.runner.run(countdownAnimation);
        this.running = true;
        this.runner.run(this);
    }

    /**
     * Removes a collidable from the game.
     *
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Removes a sprite from the game.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.level.drawOn(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (remainingBlocks.getValue() == 0) {
            score.increase(100);
            this.running = false;
        }
        if (remainingBalls.getValue() == 0) {
            this.running = false;
            return;
        }
        if (this.keyboard.isPressed("p")) {
            Animation pause = new PauseScreen(keyboard);
            runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, pause));
        }
    }

    /**
     * Create balls on top of paddle.
     */
    public void createBallsOnTopOfPaddle() {
        for (int i = 0; i < level.numberOfBalls(); i++) {
            Ball ball = new Ball(STARTX, STARTY, RADIUS, Color.BLACK, this.environment);
            ball.setVelocity(level.initialBallVelocities().get(i));
            ball.addToGame(this);
        }
    }

    /**
     * Gets balls.
     *
     * @return the balls
     */
    public int getBalls() {
        return this.remainingBalls.getValue();
    }

    /**
     * Gets blocks.
     *
     * @return the blocks
     */
    public int getBlocks() {
        return this.remainingBlocks.getValue();
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public Counter getScore() {
        return this.score;
    }
}