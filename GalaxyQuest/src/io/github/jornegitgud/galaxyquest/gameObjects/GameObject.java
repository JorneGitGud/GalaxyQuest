package io.github.jornegitgud.galaxyquest.gameObjects;

import io.github.jornegitgud.galaxyquest.SpriteList;
import io.github.jornegitgud.galaxyquest.Tile;

import java.util.function.Function;

public class GameObject {
    private SpriteList spriteList;
    private Tile tile;

    public GameObject(SpriteList spriteList, Tile tile) {
        this.spriteList = spriteList;
        this.tile = tile;
    }

    public SpriteList getSpriteList() {
        return spriteList;
    }

    public Tile getTile() {
        return tile;
    }

    protected void setTile(Tile tile) {
        this.tile = tile;
    }


}
