package io.github.jornegitgud.galaxyquest.gameObjects;

import io.github.jornegitgud.galaxyquest.Direction;
import io.github.jornegitgud.galaxyquest.HasDirection;
import io.github.jornegitgud.galaxyquest.Tile;
import io.github.jornegitgud.galaxyquest.sprites.SpriteList;

import java.util.function.Consumer;

/**
 * the MovableObject class extends from {@link GameObject}
 * it holds all the common variables use by the {@link Player}, {@link SpacePirate}, {@link Meteorite}.
 */
public class MovableObject extends GameObject {

    private boolean moving;
    private int moveFrames;
    private int currentFrame;
    private Tile nextTile;
    private Direction moveDirection;
    public Consumer<MovableObject> onMoveEnded;


    public MovableObject(SpriteList spriteList) {
        super(spriteList);
    }

    /**
     *this method is used to move an object from one tile to another, it sets the tile the object should move to next.
     * it has a switch case which is used to determen to which tile the object should move
     * @param frames the amount of frames used in animation while the object is moving.
     * @param direction the direction in which the object moves
     */
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

        if(nextTile == null
                || (!(this instanceof Player) && nextTile.containsAny(Planet.class, Meteorite.class, SpacePirate.class))
                || (!(this instanceof Player) && nextTile.contains(Wormhole.class) && nextTile.getFirst(Wormhole.class).isActive())) {
            direction = Direction.randomDirection();
            this.moving = false;
            this.move(frames, direction);
            return;
        }



        if (this instanceof HasDirection)
            ((HasDirection) this).setDirection(direction);

    }

    /**
     * the method takes sets the object to the next tile.
     * @return returns a double that is used in the animations
     */
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

