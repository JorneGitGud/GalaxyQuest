package io.github.jornegitgud.galaxyquest.gameObjects;

import io.github.jornegitgud.galaxyquest.Direction;
import io.github.jornegitgud.galaxyquest.DirectionalSpriteList;
import io.github.jornegitgud.galaxyquest.Tile;

public class Player extends MovableObject {
    private String name;
    private Direction direction;

    public Player(String name, Tile tile, Direction direction, DirectionalSpriteList directionalSpriteList) {
        super(directionalSpriteList, tile);

        this.name = name;
        this.direction = direction;
    }

    public Player(String name, Direction direction, DirectionalSpriteList directionalSpriteList) {
        super(directionalSpriteList);

        this.name = name;
        this.direction = direction;
    }

    public String getName() {
        return name;
    }
}
