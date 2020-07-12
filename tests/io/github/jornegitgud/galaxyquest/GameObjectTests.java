package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.gameObjects.GameObject;
import io.github.jornegitgud.galaxyquest.sprites.SimpleSpriteList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("GameObject tests")
public class GameObjectTests {
    private final GameObject gameObject;

    public GameObjectTests() {
        gameObject = new GameObject(new SimpleSpriteList());
    }

    @Test
    @DisplayName("Sprite list is never null")
    void hasSpriteList() {
        var spriteList = gameObject.getSpriteList();
        Assertions.assertNotNull(spriteList);
    }
}
