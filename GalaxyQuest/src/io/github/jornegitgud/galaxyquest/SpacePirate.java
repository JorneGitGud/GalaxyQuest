package io.github.jornegitgud.galaxyquest;

public class SpacePirate  extends MovableObject{
    Direction direction;
    String name;
    DirectionalSpriteList directionalSpriteList;

    public SpacePirate(Direction direction, String name, DirectionalSpriteList directionalSpriteList) {
        this.direction = direction;
        this.name = name;
        this.directionalSpriteList = directionalSpriteList;
    }
}
