package io.github.jornegitgud.galaxyquest.gameObjects;

import io.github.jornegitgud.galaxyquest.sprites.SpriteList;


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
