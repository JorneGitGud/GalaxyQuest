package io.github.jornegitgud.galaxyquest.gameObjects;

import io.github.jornegitgud.galaxyquest.sprites.SpriteList;

/**
 * this is the wormhole object, it extends from the {@link GameObject}
 * The wormhole has a boolean called active, this is used to check if the wormhole should be active in the galaxy.
 */

public class Wormhole extends GameObject {
    private boolean active = false;


    public Wormhole(SpriteList spriteList) {
        super(spriteList);
    }

    public boolean isActive() {
        return active;
    }

    public void activate() {
        this.active = true;
    }
}
