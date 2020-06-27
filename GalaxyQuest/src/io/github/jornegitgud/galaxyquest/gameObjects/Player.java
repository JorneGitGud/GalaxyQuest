package io.github.jornegitgud.galaxyquest.gameObjects;

import io.github.jornegitgud.galaxyquest.Direction;
import io.github.jornegitgud.galaxyquest.HasDirection;
import io.github.jornegitgud.galaxyquest.sprites.DirectionalSpriteList;

import java.util.function.Consumer;

/**
 * this is the player object, it extends from the {@link MovableObject} and implements the {@link HasDirection}
 *
 */
public class Player extends MovableObject implements HasDirection {

    private Direction direction;

    public Consumer<Player> onDirectionChanged;


// even checken hoe we de player naam op halen en setten in de game
    public Player( Direction direction, DirectionalSpriteList directionalSpriteList) {
        super(directionalSpriteList);


        this.direction = direction;
    }
    @Override
    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        this.onDirectionChanged.accept(this);
    }
}
