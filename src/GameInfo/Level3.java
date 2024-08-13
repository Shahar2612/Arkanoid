package GameInfo;

import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import Sprites.Block;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * The type Level 3.
 */
public class Level3 implements LevelInformation {
    private static final int LINES = 5;
    private static final int COLUMNS = 10;
    private static final int BLOCK_WIDTH = 60;
    private static final int BLOCK_HEIGHT = 30;
    private static final int ANGLE = 50;
    private static final int SPEED = 5;
    private static final int PADDLE_WIDTH = 100;
    @Override
    public int numberOfBalls() {
        return 2;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        Velocity v1 = Velocity.fromAngleAndSpeed(ANGLE, SPEED);
        Velocity v2 = Velocity.fromAngleAndSpeed(ANGLE - 100, SPEED);
        velocities.add(v1);
        velocities.add(v2);
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return "Level name: Green 3";
    }
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < LINES; i++) {
            Color color;
            switch (i) {
                case 0:
                    color = Color.RED;
                    break;
                case 1:
                    color = Color.ORANGE;
                    break;
                case 2:
                    color = Color.YELLOW;
                    break;
                case 3:
                    color = Color.GREEN;
                    break;
                case 4:
                    color = Color.BLUE;
                    break;
                default:
                    color = Color.PINK;
                    break;
            }
            for (int j = COLUMNS - 1; j >= i; j--) {
                Block block = new Block(new Rectangle(new Point(180 + j * 60, 150 + i * 30),
                        BLOCK_WIDTH, BLOCK_HEIGHT), color);
                blocks.add(block);
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
    @Override
    public Point location() {
        return new Point(350, 560);
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(550, 15, levelName(), 15);
    }
}
