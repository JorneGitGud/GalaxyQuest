package io.github.jornegitgud.galaxyquest.gameObjects;

import io.github.jornegitgud.galaxyquest.Direction;
import io.github.jornegitgud.galaxyquest.GameResult;
import io.github.jornegitgud.galaxyquest.HasDirection;
import io.github.jornegitgud.galaxyquest.sprites.SpriteList;
import io.github.jornegitgud.galaxyquest.Tile;

import java.util.function.Consumer;

/**
 * the MovableObject class extends from {@link GameObject}
 * it holds all the common variables use by the {@link Player}, {@link SpacePirate}, {@link Meteorite}.
 */
public class MovableObject extends GameObject {

    private SpriteList walkSprites;
    private boolean moving;
    private int moveFrames;
    private int currentFrame;
    private Tile nextTile;
    private Direction moveDirection;
    public Consumer<MovableObject> onMoveEnded;

    //to do java doc
    public MovableObject(SpriteList spriteList) {
        super(spriteList);
    }

    //to do java doc
    public boolean move(int frames, Direction direction) {
        if (moving)
            return true;
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

        if(nextTile == null
                || (!(this instanceof Player) && nextTile.containsAny(Planet.class, Meteorite.class, SpacePirate.class))
                || (!(this instanceof Player) && nextTile.contains(Wormhole.class) && nextTile.getFirst(Wormhole.class).isActive())) {
            direction = Direction.randomDirection();
            this.moving = false;
            this.move(frames, direction);
            return true;
        }



        if (this instanceof HasDirection)
            ((HasDirection) this).setDirection(direction);

        return true;
    }

    //to do java doc
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
    //to do java doc
    public boolean isMoving() {
        return moving;
    }
    //to do java doc
    public Direction getMoveDirection() {
        return moveDirection;
    }
}

