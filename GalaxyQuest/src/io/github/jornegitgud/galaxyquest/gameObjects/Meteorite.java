package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.gameObjects.MovableObject;

public class Meteorite extends MovableObject {
    Direction direction;

    public Meteorite(Direction direction, Tile tile, SimpleSpriteList simpleSpriteList) {
        super(simpleSpriteList, tile);
        this.direction = direction;
    }
}
