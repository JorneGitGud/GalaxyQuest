package io.github.jornegitgud.galaxyquest.gameObjects;

import io.github.jornegitgud.galaxyquest.sprites.SpriteList;

/**
 * this is the planet object, it extends from the {@link GameObject}
 */
public class Planet extends GameObject {
    private boolean visited;

    public Planet(SpriteList spriteList) {
        super(spriteList);
    }

    public void setVisited() {
        this.visited = true;
    }

    public boolean hasBeenVisited() {
        return this.visited;
    }

}