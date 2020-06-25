package io.github.jornegitgud.galaxyquest.sprites;

import io.github.jornegitgud.galaxyquest.Direction;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

/**
 * the SimpleSprite class is used in animation that don't have specific directions.
 */
public class SimpleSpriteList implements SpriteList {

    ArrayList<Image> sprites = new ArrayList<>();
    int frameCounter = -1;

    public SimpleSpriteList() { }

    /*
    consturctor that sets the sprites ArrayList.
     */
    public SimpleSpriteList(ArrayList<Image> sprites) {
        this.sprites = sprites;
    }

    public void addSprite(Image sprite) {
        sprites.add(sprite);
    }

    public void addSprites(ArrayList<Image> sprites) {
        sprites.addAll(sprites);
    }

    public ArrayList<Image> getSprites() {
        return this.sprites;
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

    @Override
    public Image getNextSprite(Direction dir) {
        return getNextSprite();
    }
}
