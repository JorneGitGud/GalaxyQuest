package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.gameObjects.GameObject;
import io.github.jornegitgud.galaxyquest.sprites.SimpleSpriteList;

public class Planet extends GameObject {
    Boolean visited;


    public Planet(SimpleSpriteList spriteList) {
        super(spriteList);
        this.visited = false;
    }

}
