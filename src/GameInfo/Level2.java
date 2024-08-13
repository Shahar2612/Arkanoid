package GameInfo;

import Geometry.Point;
import Geometry.Velocity;
import Sprites.Block;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import Geometry.Rectangle;
import biuoop.DrawSurface;
/**
 * The type Level 2.
 */
public class Level2 implements LevelInformation {
    private static final int BLOCK_WIDTH = 51;
    private static final int BLOCK_HEIGHT = 30;
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        int angle = -70;
        for (int i = 0; i < 10; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(angle, 5));
            angle += 15;
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 2;
    }

    @Override
    public int paddleWidth() {
        return 650;
    }

    @Override
    public String levelName() {
        return "Level name: Wide Easy";
    }
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Color color;
            switch (i) {
                case 0:
                case 1:
                    color = Color.RED;
                    break;
                case 2:
                case 3:
                    color = Color.ORANGE;
                    break;
                case 4:
                case 5:
                    color = Color.YELLOW;
                    break;
                case 6:
                case 7:
                case 8:
                    color = Color.GREEN;
                    break;
                case 9:
                case 10:
                    color = Color.BLUE;
                    break;
                case 11:
                case 12:
                    color = Color.PINK;
                    break;
                default:
                    color = Color.CYAN;
                    break;
            }
                Block block = new Block(new Rectangle(new Point(20 + i * 51, 220),
                        BLOCK_WIDTH, BLOCK_HEIGHT), color);
                blocks.add(block);
        }
        return blocks;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
    @Override
    public Point location() {
        return new Point(60, 560);
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(550, 15, levelName(), 15);
    }
}
