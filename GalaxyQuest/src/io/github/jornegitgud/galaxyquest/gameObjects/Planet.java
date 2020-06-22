package io.github.jornegitgud.galaxyquest.gameObjects;

import io.github.jornegitgud.galaxyquest.Tile;
import io.github.jornegitgud.galaxyquest.sprites.SpriteList;

public class Planet extends GameObject{
    String name;
    Boolean visited;

    public Planet(SpriteList spriteList) {
        super(spriteList);
    }

    public Planet(SpriteList spriteList, Tile tile) {
        super(spriteList, tile);
    }



//    public Planet(String name, Boolean visited) {
//        this.name = name;
//        this.visited = visited;
//
//    }

}