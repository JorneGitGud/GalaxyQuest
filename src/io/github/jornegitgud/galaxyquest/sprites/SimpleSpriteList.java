package io.github.jornegitgud.galaxyquest.sprites;

import io.github.jornegitgud.galaxyquest.Direction;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

/**
 * the SimpleSprite class is used in animation that don't have specific directions.
 */
public class SimpleSpriteList implements SpriteList {

    private ArrayList<Image> sprites = new ArrayList<>();
    int frameCounter = -1;

    /**
     * this is an empty constructor used o create an object without any images. We used this if we want to add images af te we create the object
     */
    public SimpleSpriteList() { }

    /**
    constructor that sets the sprites ArrayList.
     */
    public SimpleSpriteList(ArrayList<Image> sprites) {
        this.sprites = sprites;
    }

    /**
     * Method used to add a sprite
     * @param sprite the sprite that is added
     */
    public void addSprite(Image sprite) {
        sprites.add(sprite);
    }

    /**
     * method used to add an arraylist of sprites
     * @param sprites the arraylist of sprites that is added
     */
    public void addSprites(ArrayList<Image> sprites) {
        this.sprites.addAll(sprites);
    }

    /**
     * randomizes the first image used in animations.
     * @return returns an Image from the sprites Arraylist
     */
    @Override
    public Image getNextSprite() {
        if (frameCounter == -1) //randomize start frame on app start
            frameCounter = new Random().nextInt(sprites.size());
        frameCounter++;
        if (frameCounter == sprites.size())
            frameCounter = 0;
        return sprites.get(frameCounter);
    }

    /**
     * method used to return a sprite that has a direction
     * @param dir direction that is used
     * @return returns a sprite
     */
    @Override
    public Image getNextSprite(Direction dir) {
        return getNextSprite();
    }

    /**
     * method used to return the arraylist of sprites
     * @return returns the arraylist of sprites
     */
    public ArrayList<Image> getSprites() {
        return this.sprites;
    }
}
