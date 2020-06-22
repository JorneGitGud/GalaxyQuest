package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.gameObjects.GameObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("GameObject tests")
public class GameObjectTests {
    private GameObject gameObject;

    public GameObjectTests() {
        gameObject = new GameObject(new SimpleSpriteList(), new Tile(new Coordinate(0,0)));
    }

    @Test
    @DisplayName("Sprite list is never null")
    void hasSpriteList() {
        var spriteList = gameObject.getSpriteList();
        Assertions.assertNotNull(spriteList);
    }
}
