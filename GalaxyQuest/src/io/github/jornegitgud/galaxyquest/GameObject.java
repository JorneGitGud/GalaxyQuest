package io.github.jornegitgud.galaxyquest;

public class GameObject {
    private SpriteList spriteList;

    public GameObject(SpriteList spriteList) {
        this.spriteList = spriteList;
    }

    public SpriteList getSpriteList() {
        return spriteList;
    }
}
