package io.github.jornegitgud.galaxyquest.gameObjects;

import io.github.jornegitgud.galaxyquest.sprites.SpriteList;
import io.github.jornegitgud.galaxyquest.Tile;

public class GameObject {
    private SpriteList spriteList;
    private Tile tile;

    public GameObject(SpriteList spriteList, Tile tile) {
        this.spriteList = spriteList;
        this.tile = tile;
    }

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
