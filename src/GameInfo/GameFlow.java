//209492768 Shahar Chen
package GameInfo;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter currentScore;
    private GUI gui;
    /**
     * Instantiates a new Game flow.
     *
     * @param ar the ar
     * @param ks the ks
     * @param gui gui.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.currentScore = new Counter(0);
        this.gui = gui;
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, gui, currentScore);
            level.initialize();
            while (level.getBlocks() > 0 && level.getBalls() > 0) {
                level.run();
            }
            if (level.getBalls() == 0) {
                Animation over = new GameOverScreen(keyboardSensor, currentScore.getValue());
               animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY, over));
                gui.close();
            }
            this.currentScore = level.getScore();
        }
        Animation win = new YouWinScreen(keyboardSensor, currentScore.getValue());
        animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY, win));
        gui.close();
    }
}
