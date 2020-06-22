package io.github.jornegitgud.galaxyquest.gameObjects;

import io.github.jornegitgud.galaxyquest.Direction;
import io.github.jornegitgud.galaxyquest.HasDirection;
import io.github.jornegitgud.galaxyquest.sprites.DirectionalSpriteList;
import io.github.jornegitgud.galaxyquest.Tile;

public class Player extends MovableObject implements HasDirection {
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

    @Override
    public Direction getDirection() {
        return this.direction;
    }
}
