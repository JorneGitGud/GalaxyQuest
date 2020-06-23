package io.github.jornegitgud.galaxyquest.gameObjects;

import io.github.jornegitgud.galaxyquest.Direction;
import io.github.jornegitgud.galaxyquest.HasDirection;
import io.github.jornegitgud.galaxyquest.sprites.SpriteList;
import io.github.jornegitgud.galaxyquest.Tile;

import java.util.function.Consumer;

public class MovableObject extends GameObject {
    private SpriteList walkSprites;
    private boolean moving;
    private int moveFrames;
    private int currentFrame;
    private Tile nextTile;
    private Direction moveDirection;
    public Consumer<MovableObject> onMoveEnded;


    public MovableObject(SpriteList spriteList) {
        super(spriteList);
    }

    public void move(int frames, Direction direction) {
        if (moving)
            return;
        moving = true;
        moveFrames = frames;
        currentFrame = 0;

        moveDirection = direction;

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

        if(nextTile == null)
            moving = false;

        if(this instanceof HasDirection)
            ((HasDirection) this).setDirection(direction);
    }

    public double updateMove() {
        currentFrame++;
        if (currentFrame >= moveFrames) {
            moving = false;
            this.setTile(nextTile);
            this.onMoveEnded.accept(this);
            return 0;
        }
        return (1.0 / moveFrames) * currentFrame;
    }

    public boolean isMoving() {
        return moving;
    }

    public Direction getMoveDirection() {
        return moveDirection;
    }
}

