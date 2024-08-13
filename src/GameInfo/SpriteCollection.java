//209492768 Shahar Chen
package GameInfo;
import Sprites.Sprite;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Sprite collection.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Instantiates a new Sprites.Sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Add sprite to the list.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }
    /**
     * Removes a sprite from the game.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * Notify all sprites that time passed.
     */
    public void notifyAllTimePassed() {
//        for (Sprite sprite : sprites) {
//            sprite.timePassed();
//        }
        List<Sprite> spritesCopy = new ArrayList<>(sprites); // Create a copy of the sprites list

        for (Sprite sprite : spritesCopy) {
            sprite.timePassed();
        }
    }

    /**
     * Draw all sprites on.
     *
     * @param d the d
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
}
