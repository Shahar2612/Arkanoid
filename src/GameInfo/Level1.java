//209492768 Shahar Chen
package GameInfo;

import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import Sprites.Block;
import biuoop.DrawSurface;
import java.util.List;
import java.awt.Color;
import java.util.ArrayList;
/**
 * The type Level 1.
 */
public class Level1 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        Velocity v1 = Velocity.fromAngleAndSpeed(1, 5);
        velocities.add(v1);
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Level name: Direct Hit";
    }
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Block block = new Block(new Rectangle(new Point(385, 150), 30, 30), Color.RED);
        blocks.add(block);
        return blocks;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return 1;
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
