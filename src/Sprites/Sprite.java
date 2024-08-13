//209492768 Shahar Chen
package Sprites;

import GameInfo.GameLevel;
import biuoop.DrawSurface;

/**
 * The interface Sprites.Sprite.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d the d
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * Add to game.
     *
     * @param g the g
     */
    void addToGame(GameLevel g);
}
