package io.github.jornegitgud.galaxyquest.gameObjects;

import io.github.jornegitgud.galaxyquest.Direction;
import io.github.jornegitgud.galaxyquest.HasDirection;
import io.github.jornegitgud.galaxyquest.sprites.DirectionalSpriteList;
import io.github.jornegitgud.galaxyquest.Tile;

import java.util.function.Consumer;

public class Player extends MovableObject implements HasDirection {
    private String name;
    private Direction direction;

    public Consumer<HasDirection> onDirectionChanged;



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

    public void setDirection(Direction direction) {
        this.direction = direction;
        this.onDirectionChanged.accept(this);
    }
}
