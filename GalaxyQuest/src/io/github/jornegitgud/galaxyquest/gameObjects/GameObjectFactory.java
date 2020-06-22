package io.github.jornegitgud.galaxyquest.gameObjects;

import io.github.jornegitgud.galaxyquest.Direction;
import io.github.jornegitgud.galaxyquest.sprites.*;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GameObjectFactory {
    private static HashMap<Direction, ArrayList<Image>> playerSprites;
    private static SimpleSpriteList planetSprites;
    private static SimpleSpriteList meteoriteSprites;
    private static DirectionalSpriteList spacePirateSprites;
    private static SimpleSpriteList wormholeSprites;

    public static Player createPlayer(Direction spawnDirection) throws IOException {
        if(playerSprites == null) {
            var leftSprites = FileHelper.createImage("assets/MovableObjects/Player_Left.png");
            var rightSprites = FileHelper.createImage("assets/MovableObjects/Player_Right.png");
            var upSprites = FileHelper.createImage("assets/MovableObjects/Player_Up.png");
            var downSprites = FileHelper.createImage("assets/MovableObjects/Player_Down.png");
            playerSprites = new HashMap<>();
            playerSprites.put(Direction.LEFT, SpriteMapParser.parseSpriteMapToImages(leftSprites, 64, 64));
            playerSprites.put(Direction.RIGHT, SpriteMapParser.parseSpriteMapToImages(rightSprites, 64, 64));
            playerSprites.put(Direction.UP, SpriteMapParser.parseSpriteMapToImages(upSprites, 64, 64));
            playerSprites.put(Direction.DOWN, SpriteMapParser.parseSpriteMapToImages(downSprites, 64,64));
        }

        return new Player("Test", spawnDirection, new DirectionalSpriteList(playerSprites, spawnDirection));
    }

//    public static Planet createPlanet() {
//
//    }
//
//    public static Meteorite createMeteorite() {
//
//    }
//
//    public static SpacePirate createSpacePirate() {
//
//    }
//
//    public static Wormhole createWormhole() {
//
//    }
}
