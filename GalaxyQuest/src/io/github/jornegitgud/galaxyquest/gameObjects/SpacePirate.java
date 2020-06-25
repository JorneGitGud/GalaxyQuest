package io.github.jornegitgud.galaxyquest.gameObjects;

import io.github.jornegitgud.galaxyquest.Direction;
import io.github.jornegitgud.galaxyquest.HasDirection;
import io.github.jornegitgud.galaxyquest.sprites.DirectionalSpriteList;
import io.github.jornegitgud.galaxyquest.Tile;

/**
 * this is the space pirate object, it extends from the {@link MovableObject}
 */
public class SpacePirate  extends MovableObject implements HasDirection {
    Direction direction;

    public SpacePirate(Direction direction, DirectionalSpriteList directionalSpriteList) {
        super(directionalSpriteList);
        this.direction = direction;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
