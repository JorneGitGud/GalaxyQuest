package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.sprites.DirectionalSpriteList;
import io.github.jornegitgud.galaxyquest.sprites.FileHelper;
import io.github.jornegitgud.galaxyquest.sprites.SimpleSpriteList;
import javafx.scene.image.Image;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;
import java.util.ArrayList;

@DisplayName("SpriteList tests")
public class SpriteListTests {

    @Test
    @DisplayName("Simple sprite list's getSprites() never returns null")
    void simpleSpriteList_GetSpritesNeverNull() {
        SimpleSpriteList simpleSpriteList = new SimpleSpriteList();
        Assertions.assertNotNull(simpleSpriteList.getSprites());
    }

    @Test
    @DisplayName("Simple sprite list adds image successfully")
    void simpleSpriteList_AddsImageSuccessfully() throws IOException {
        SimpleSpriteList simpleSpriteList = new SimpleSpriteList();
        simpleSpriteList.addSprite(FileHelper.createFxImage("assets/placeholder.png"));

        var sprites = simpleSpriteList.getSprites();
        Assertions.assertNotNull(sprites);
        Assertions.assertEquals(1, sprites.size());

        simpleSpriteList.addSprite(FileHelper.createFxImage("assets/placeholder.png"));
        Assertions.assertEquals(2, simpleSpriteList.getSprites().size());

        simpleSpriteList = new SimpleSpriteList(sprites);
        Assertions.assertNotNull(simpleSpriteList.getSprites());
        Assertions.assertEquals(simpleSpriteList.getSprites().size(), sprites.size());
    }

    @Test
    @DisplayName("Simple sprite list adds array successfully")
    void simpleSpriteList_AddsImagesSuccessfully() throws IOException {
        SimpleSpriteList simpleSpriteList = new SimpleSpriteList();

        var sprites = new ArrayList<Image>();
        sprites.add(FileHelper.createFxImage("assets/placeholder.png"));
        sprites.add(FileHelper.createFxImage("assets/placeholder.png"));
        sprites.add(FileHelper.createFxImage("assets/placeholder.png"));

        simpleSpriteList.addSprites(sprites);
        Assertions.assertNotNull(simpleSpriteList.getSprites());
        Assertions.assertEquals(simpleSpriteList.getSprites().size(), sprites.size());
    }

    @Test
    @DisplayName("Directional sprite list's getSprites() never returns null")
    void directionalSpriteList_GetSpritesNeverNull() {
        DirectionalSpriteList directionalSpriteList = new DirectionalSpriteList(Direction.RIGHT);
        Assertions.assertNotNull(directionalSpriteList.getSprites());
        Assertions.assertNotNull(directionalSpriteList.getSprites(Direction.UP));
        Assertions.assertNotNull(directionalSpriteList.getSprites(Direction.DOWN));
        Assertions.assertNotNull(directionalSpriteList.getSprites(Direction.LEFT));
        Assertions.assertNotNull(directionalSpriteList.getSprites(Direction.RIGHT));
    }

    @Test
    @DisplayName("Directional sprite list adds image successfully")
    void directionalSpriteList_AddsImageSuccessfully() throws IOException {
        DirectionalSpriteList directionalSpriteList = new DirectionalSpriteList(Direction.RIGHT);
        directionalSpriteList.addSprite(Direction.UP, FileHelper.createFxImage("assets/placeholder.png"));
        directionalSpriteList.addSprite(Direction.LEFT, FileHelper.createFxImage("assets/placeholder.png"));
        directionalSpriteList.addSprite(Direction.UP, FileHelper.createFxImage("assets/placeHolder.png"));
        Assertions.assertEquals(0, directionalSpriteList.getSprites(Direction.RIGHT).size());
        Assertions.assertEquals(1, directionalSpriteList.getSprites(Direction.LEFT).size());
        Assertions.assertEquals(0, directionalSpriteList.getSprites(Direction.DOWN).size());
        Assertions.assertEquals(2, directionalSpriteList.getSprites(Direction.UP).size());

        var hashMap = directionalSpriteList.getSprites();
        var spriteList2 = new DirectionalSpriteList(hashMap, Direction.RIGHT);

        Assertions.assertEquals(spriteList2.getSprites().keySet().size(), hashMap.keySet().size());
        Assertions.assertEquals(spriteList2.getSprites().get(Direction.LEFT).size(), directionalSpriteList.getSprites(Direction.LEFT).size());
    }

    @Test
    @DisplayName("Directional sprite list adds array successfully")
    void setDirectionalSpriteList_AddsArraySuccessfully() throws IOException {
        DirectionalSpriteList directionalSpriteList = new DirectionalSpriteList(Direction.RIGHT);

        var sprites = new ArrayList<Image>();
        sprites.add(FileHelper.createFxImage("assets/placeholder.png"));
        sprites.add(FileHelper.createFxImage("assets/placeholder.png"));
        sprites.add(FileHelper.createFxImage("assets/placeholder.png"));

        directionalSpriteList.addSprites(Direction.UP, sprites);
        directionalSpriteList.addSprites(Direction.LEFT, sprites);
        Assertions.assertEquals(0, directionalSpriteList.getSprites(Direction.RIGHT).size());
        Assertions.assertEquals(directionalSpriteList.getSprites(Direction.LEFT).size(), sprites.size());
        Assertions.assertEquals(0, directionalSpriteList.getSprites(Direction.DOWN).size());
        Assertions.assertEquals(directionalSpriteList.getSprites(Direction.UP).size(), sprites.size());
    }

    @Test
    @DisplayName("SpriteList resets to sprite 0 when going out of bounds or changing direction")
    void spriteList_resetsSuccessfully() throws IOException {
        var sSpriteList = new SimpleSpriteList();
        var dSpriteList = new DirectionalSpriteList(Direction.UP);

        sSpriteList.addSprite(FileHelper.createFxImage("assets/placeholder.png"));
        sSpriteList.addSprite(FileHelper.createFxImage("assets/placeholder.png"));
        sSpriteList.addSprite(FileHelper.createFxImage("assets/placeholder.png"));

        dSpriteList.addSprite(Direction.RIGHT, FileHelper.createFxImage("assets/placeholder.png"));
        dSpriteList.addSprite(Direction.RIGHT, FileHelper.createFxImage("assets/placeholder.png"));
        dSpriteList.addSprite(Direction.RIGHT, FileHelper.createFxImage("assets/placeholder.png"));
        dSpriteList.addSprite(Direction.LEFT, FileHelper.createFxImage("assets/placeholder.png"));

        Assertions.assertDoesNotThrow(() -> {sSpriteList.getNextSprite(Direction.UP); });
        Assertions.assertNotNull(sSpriteList.getNextSprite(Direction.LEFT));
        Assertions.assertNotNull(sSpriteList.getNextSprite());
        Assertions.assertNotNull(sSpriteList.getNextSprite());

        Assertions.assertDoesNotThrow((Executable) dSpriteList::getNextSprite);
        Assertions.assertNull(dSpriteList.getNextSprite(Direction.UP));
        Assertions.assertNotNull(dSpriteList.getNextSprite(Direction.RIGHT));
        dSpriteList.getNextSprite(Direction.RIGHT);
        Assertions.assertDoesNotThrow(() -> {dSpriteList.getNextSprite(Direction.LEFT);});
    }
}
