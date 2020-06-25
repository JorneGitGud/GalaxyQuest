package io.github.jornegitgud.galaxyquest.gameObjects;

import io.github.jornegitgud.galaxyquest.Direction;
import io.github.jornegitgud.galaxyquest.sprites.SimpleSpriteList;
import io.github.jornegitgud.galaxyquest.Tile;

/**
 * this is the meteorite object it extends from the {@link MovableObject}
 */
public class Meteorite extends MovableObject {
    Direction direction;


    public Meteorite(Direction direction, SimpleSpriteList simpleSpriteList) {
        super(simpleSpriteList);
        this.direction = direction;
    }
}
