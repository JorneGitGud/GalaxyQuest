package io.github.jornegitgud.galaxyquest;

public class Meteorite extends MovableObject {
    Direction direction;
    SimpleSpriteList simpleSpriteList;

    public Meteorite(Direction direction, SimpleSpriteList simpleSpriteList) {
        this.direction = direction;
        this.simpleSpriteList = simpleSpriteList;
    }
}
