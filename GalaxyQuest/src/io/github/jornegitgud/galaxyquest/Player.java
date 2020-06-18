package io.github.jornegitgud.galaxyquest;

public class Player extends MovableObject {
    String name;
    Direction direction;
    DirectionalSpriteList directionalSpriteList;


    public Player(String name, Direction direction, DirectionalSpriteList directionalSpriteList) {
        this.name = name;
        this.direction = direction;
        this.directionalSpriteList = directionalSpriteList;
    }
}
