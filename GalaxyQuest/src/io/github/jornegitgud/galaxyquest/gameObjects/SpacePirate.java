package io.github.jornegitgud.galaxyquest.gameObjects;

import io.github.jornegitgud.galaxyquest.Direction;
import io.github.jornegitgud.galaxyquest.sprites.DirectionalSpriteList;
import io.github.jornegitgud.galaxyquest.Tile;

public class SpacePirate  extends MovableObject{
    Direction direction;

    public SpacePirate(Direction direction, DirectionalSpriteList directionalSpriteList) {
        super(directionalSpriteList);
        this.direction = direction;
    }
}
