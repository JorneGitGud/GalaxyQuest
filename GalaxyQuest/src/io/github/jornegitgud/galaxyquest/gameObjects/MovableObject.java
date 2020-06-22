package io.github.jornegitgud.galaxyquest.gameObjects;

import io.github.jornegitgud.galaxyquest.Direction;
import io.github.jornegitgud.galaxyquest.sprites.SpriteList;
import io.github.jornegitgud.galaxyquest.Tile;

import java.util.function.Consumer;

public class MovableObject extends GameObject {
    private SpriteList walkSprites;
    private boolean isMoving;
    private int moveFrames;
    private int currentFrame;
    private Tile nextTile;
    public Consumer<MovableObject> onMoveEnded;

    public MovableObject(SpriteList spriteList, Tile tile) {
        super(spriteList, tile);
    }
    public MovableObject(SpriteList spriteList) {
        super(spriteList);
    }
    public void move(int frames, Direction direction) {
        if (isMoving)
            return;
        isMoving = true;
        moveFrames = frames;
        currentFrame = 0;

        switch (direction) {
            case UP:
                nextTile = this.getTile().getTileAbove();
                break;
            case DOWN:
                nextTile = this.getTile().getTileBelow();
                break;
            case LEFT:
                nextTile = this.getTile().getTileLeft();
                break;
            case RIGHT:
                nextTile = this.getTile().getTileRight();
                break;
        }
    }

    public double updateMove() {
        currentFrame++;
        if (currentFrame >= moveFrames) {
            isMoving = false;
            this.setTile(nextTile);
            this.onMoveEnded.accept(this);
            return 0;
        }
        return (1.0 / moveFrames) * currentFrame;
    }
}

