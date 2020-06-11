package io.github.jornegitgud.galaxyquest;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DirectionalSpriteList implements SpriteList {

    private HashMap<Direction, ArrayList<Image>> sprites = new HashMap<>();
    private Direction lastDirection;
    private int frameCounter = -1;

    public DirectionalSpriteList(Direction defaultDirection) {
        //Init all sprite collections with empty ArrayList
        this.lastDirection = defaultDirection;

        sprites.put(Direction.RIGHT, new ArrayList<>());
        sprites.put(Direction.DOWN, new ArrayList<>());
        sprites.put(Direction.LEFT, new ArrayList<>());
        sprites.put(Direction.UP, new ArrayList<>());
    }

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

    public void addSprite(Direction direction, Image sprite) {
        sprites.get(direction).add(sprite);
    }

    public void addSprites(Direction direction, ArrayList<Image> sprites) {
        this.sprites.get(direction).addAll(sprites);
    }

    public HashMap<Direction, ArrayList<Image>> getSprites() {
        return sprites;
    }

    public ArrayList<Image> getSprites(Direction direction) {
        return sprites.get(direction);
    }

    @Override
    public Image getNextSprite() {
        return getNextSprite(lastDirection);
    }

    @Override
    public Image getNextSprite(Direction dir) {
        if (lastDirection != dir)
            frameCounter = -1;
        else if (frameCounter == -1) //randomize start frame on app start
            frameCounter = new Random().nextInt(sprites.get(dir).size());

        ArrayList<Image> frames = sprites.get(dir);

        frameCounter++;
        if (frameCounter == frames.size())
            frameCounter = 0;

        lastDirection = dir;
        return frames.get(frameCounter);
    }
}
