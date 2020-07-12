package io.github.jornegitgud.galaxyquest.sprites;

import io.github.jornegitgud.galaxyquest.Direction;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * the DirectionalSpriteList creates 4(directions) Arraylists that hold sprites used to in animations
 */
public class DirectionalSpriteList implements SpriteList {

    private HashMap<Direction, ArrayList<Image>> sprites = new HashMap<>();
    private Direction lastDirection;
    private int frameCounter = -1;

    /**
     * this constructor sets the last direction to the default direction
     * fills the hashmap with arraylists containing NO sprites (used for that direction)
     * @param defaultDirection defines what the default direction is.
     */
    public DirectionalSpriteList(Direction defaultDirection) {
        //Init all sprite collections with empty ArrayList
        this.lastDirection = defaultDirection;

        sprites.put(Direction.RIGHT, new ArrayList<>());
        sprites.put(Direction.DOWN, new ArrayList<>());
        sprites.put(Direction.LEFT, new ArrayList<>());
        sprites.put(Direction.UP, new ArrayList<>());
    }

    /**
     * this constructor sets the last direction to the default direction
     * fills the hashmap with arraylists containing sprites (used for that direction)
     * @param sprites contains the sprites
     * @param defaultDirection the direction to which to set the default direction.
     */
    public DirectionalSpriteList(HashMap<Direction, ArrayList<Image>> sprites, Direction defaultDirection) {
        this.lastDirection = defaultDirection;
        if (!sprites.containsKey(Direction.UP))
            sprites.put(Direction.UP, new ArrayList<>());

        if (!sprites.containsKey(Direction.LEFT))
            sprites.put(Direction.LEFT, new ArrayList<>());

        if (!sprites.containsKey(Direction.RIGHT))
            sprites.put(Direction.RIGHT, new ArrayList<>());

        if (!sprites.containsKey(Direction.DOWN))
            sprites.put(Direction.DOWN, new ArrayList<>());

        this.sprites = sprites;
    }

    /**
     * method used to get the next sprite in the arraylist
     * @return returns an image
     */
    @Override
    public Image getNextSprite() {
        return getNextSprite(lastDirection);
    }


    /**
     * getNextSprite returns the next sprite used in animations.
     * @param dir direction of the next sprite
     * @return returns an image
     */
    @Override
    public Image getNextSprite(Direction dir) {
        if(sprites.get(dir).size() == 0)
            return null;
        if (lastDirection != dir)
            frameCounter = -1;
        else if (frameCounter == -1) //randomize start frame on app start
            frameCounter = new Random().nextInt(sprites.get(dir).size());

        ArrayList<Image> frames = sprites.get(dir);

        frameCounter++;
        if (frameCounter >= frames.size())
            frameCounter = 0;

        lastDirection = dir;
        return frames.get(frameCounter);
    }

    /**
     * method used to add a sprite
     * @param direction the direction that is used for the added sprite
     * @param sprite the sprite that is used
     */
    public void addSprite(Direction direction, Image sprite) {
        sprites.get(direction).add(sprite);
    }

    /**
     * method used to add multiple sprites
     * @param direction direction used for the sprites
     * @param sprites the sprites that are added
     */
    public void addSprites(Direction direction, ArrayList<Image> sprites) {
        this.sprites.get(direction).addAll(sprites);
    }

    /**
     * method used to get the sprite list hashmap
     * @return returns the sprites hashmap
     */
    public HashMap<Direction, ArrayList<Image>> getSprites() {
        return this.sprites;
    }

    /**
     * method used to get te sprites arraylist with a certain direction
     * @param direction direction the is used to get de arraylist
     * @return returns the arraylist with sprites
     */
    public ArrayList<Image> getSprites(Direction direction) {
        return this.sprites.get(direction);
    }
}
