//209492768 Shahar Chen
import GameInfo.AnimationRunner;
import GameInfo.LevelInformation;
import GameInfo.Level1;
import GameInfo.Level2;
import GameInfo.Level3;
import GameInfo.GameFlow;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines and runs the game.
 */
public class Ass6Game {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;

    /**
     * Initiate default list.
     *
     * @return the list
     */
    public static List<LevelInformation> initiateDefault() {
        LevelInformation level1 = new Level1();
        LevelInformation level2 = new Level2();
        LevelInformation level3 = new Level3();

        // Create the list of levels
        List<LevelInformation> levels = new ArrayList<>();
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
        return levels;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", SCREEN_WIDTH, SCREEN_HEIGHT);
        AnimationRunner animationRunner = new AnimationRunner(gui);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        GameFlow gameFlow = new GameFlow(animationRunner, keyboardSensor, gui);
        if (args.length == 0) {
            gameFlow.runLevels(initiateDefault());
        } else {
            List<Integer> numbers = new ArrayList<>();
            for (String arg : args) {
                if (arg.equals("1") || arg.equals("2") || arg.equals("3")) {
                    numbers.add(Integer.parseInt(arg));
                }
            }
            if (numbers.isEmpty()) {
                gameFlow.runLevels(initiateDefault());
            } else {
                List<LevelInformation> levels = new ArrayList<>();
                for (int num : numbers) {
                    if (num == 1) {
                        LevelInformation level1 = new Level1();
                        levels.add(level1);
                    } else if (num == 2) {
                        LevelInformation level2 = new Level2();
                        levels.add(level2);
                    } else {
                        LevelInformation level3 = new Level3();
                        levels.add(level3);
                    }
                }
                gameFlow.runLevels(levels);
            }
        }
    }
}