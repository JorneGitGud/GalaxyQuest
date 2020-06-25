package io.github.jornegitgud.galaxyquest.gameObjects;

import io.github.jornegitgud.galaxyquest.sprites.SpriteList;
import io.github.jornegitgud.galaxyquest.Tile;

/**
 * this is the super class of{@link MovableObject} ,{@link Meteorite}, {@link SpacePirate}, {@link Player}.
 * this class is called by the {@link GameObjectFactory}
 */
public class GameObject {
    private SpriteList spriteList;
    private Tile tile;

    public GameObject(SpriteList spriteList) {
        this.spriteList = spriteList;
    }


    public SpriteList getSpriteList() {
        return spriteList;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }


}
