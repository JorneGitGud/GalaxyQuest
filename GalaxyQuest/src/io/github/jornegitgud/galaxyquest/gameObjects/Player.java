package io.github.jornegitgud.galaxyquest.gameObjects;

import io.github.jornegitgud.galaxyquest.Direction;
import io.github.jornegitgud.galaxyquest.DirectionalSpriteList;
import io.github.jornegitgud.galaxyquest.Tile;

public class Player extends MovableObject {
    String name;
    Direction direction;

    public Player(String name, Tile tile, Direction direction, DirectionalSpriteList directionalSpriteList) {
        super(directionalSpriteList, tile);

        this.name = name;
        this.direction = direction;
    }
}
