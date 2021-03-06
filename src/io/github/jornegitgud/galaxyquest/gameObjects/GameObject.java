package io.github.jornegitgud.galaxyquest.gameObjects;

import io.github.jornegitgud.galaxyquest.Tile;
import io.github.jornegitgud.galaxyquest.sprites.SpriteList;

/**
 * this is the super class of{@link MovableObject} ,{@link Meteorite}, {@link SpacePirate}, {@link Player}.
 * this class is called by the {@link GameObjectFactory}
 */
public class GameObject {
    private final SpriteList SPRITE_LIST;
    private Tile tile;

    public GameObject(SpriteList spriteList) {
        this.SPRITE_LIST = spriteList;
    }


    public SpriteList getSpriteList() {
        return SPRITE_LIST;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        if(this.tile != null)
            this.tile.removeGameObject(this);
        tile.addGameObject(this);
        this.tile = tile;
    }


}
