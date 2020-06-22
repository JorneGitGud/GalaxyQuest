package io.github.jornegitgud.galaxyquest.gameObjects;

import io.github.jornegitgud.galaxyquest.Direction;
import io.github.jornegitgud.galaxyquest.sprites.SimpleSpriteList;
import io.github.jornegitgud.galaxyquest.Tile;

public class Meteorite extends MovableObject {
    Direction direction;

    public Meteorite(Direction direction, Tile tile, SimpleSpriteList simpleSpriteList) {
        super(simpleSpriteList, tile);
        this.direction = direction;
    }

    public Meteorite(Direction direction, SimpleSpriteList simpleSpriteList) {
        super(simpleSpriteList);
        this.direction = direction;
    }
}
