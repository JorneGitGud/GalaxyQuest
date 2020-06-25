package io.github.jornegitgud.galaxyquest.gameObjects;

import io.github.jornegitgud.galaxyquest.Direction;
import io.github.jornegitgud.galaxyquest.sprites.DirectionalSpriteList;
import io.github.jornegitgud.galaxyquest.Tile;

/**
 * this is the space pirate object, it extends from the {@link MovableObject}
 */
public class SpacePirate  extends MovableObject{
    Direction direction;

    public SpacePirate(Direction direction, DirectionalSpriteList directionalSpriteList) {
        super(directionalSpriteList);
        this.direction = direction;
    }
}
