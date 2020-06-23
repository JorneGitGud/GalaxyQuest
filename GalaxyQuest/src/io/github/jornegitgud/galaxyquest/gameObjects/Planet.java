package io.github.jornegitgud.galaxyquest.gameObjects;

import io.github.jornegitgud.galaxyquest.Tile;
import io.github.jornegitgud.galaxyquest.sprites.SpriteList;

public class Planet extends GameObject {
    private String name;
    private Boolean visited;

    public Planet(SpriteList spriteList) {
        super(spriteList);
    }



    public void visited() {
        this.visited = true;
    }

//    public Planet(String name, Boolean visited) {
//        this.name = name;
//        this.visited = visited;
//
//    }

}