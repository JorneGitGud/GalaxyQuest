package io.github.jornegitgud.galaxyquest.gameObjects;

import io.github.jornegitgud.galaxyquest.Direction;
import io.github.jornegitgud.galaxyquest.sprites.*;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * the GameObjectFactory creates all the objects that are spawned on the gameScene.
 */
public class GameObjectFactory {
    private static HashMap<Direction, ArrayList<Image>> playerSprites;
    private static ArrayList<Image> planetSprites;
    private static ArrayList<Image> meteoriteSprites;
    private static HashMap<Direction, ArrayList<Image>> spacePirateSprites;
    private static ArrayList<Image> wormholeSprites;
    private static Random random = new Random();
    private static int planetChoice = 0;

    /**
     * this method creates the Player object, it sets the spawn direction and sets the sprites and size.
     * @param spawnDirection the direction the player is facing when first initialised.
     * @return a player object
     * @throws IOException throws an exception if the file is not found.
     */
    public static Player createPlayer(Direction spawnDirection) throws IOException {
        if (playerSprites == null) {
            var leftSprites = FileHelper.createImage("assets/MovableObjects/Player_Left.png");
            var rightSprites = FileHelper.createImage("assets/MovableObjects/Player_Right.png");
            var upSprites = FileHelper.createImage("assets/MovableObjects/Player_Up.png");
            var downSprites = FileHelper.createImage("assets/MovableObjects/Player_Down.png");
            playerSprites = new HashMap<>();
            playerSprites.put(Direction.LEFT, SpriteMapParser.parseSpriteMapToImages(leftSprites, 64, 64));
            playerSprites.put(Direction.RIGHT, SpriteMapParser.parseSpriteMapToImages(rightSprites, 64, 64));
            playerSprites.put(Direction.UP, SpriteMapParser.parseSpriteMapToImages(upSprites, 64, 64));
            playerSprites.put(Direction.DOWN, SpriteMapParser.parseSpriteMapToImages(downSprites, 64, 64));
        }

        return new Player( spawnDirection, new DirectionalSpriteList(playerSprites, spawnDirection));
    }

    /**
     * this method creates the planet objects.
     * it adds all images of the planets to planetSprites arraylist.
     * the creates a SimpleSpriteList adds a image from the planetsprites arraylist from the location dicided by the planetChoice variable.
     * @return  a new Planet object with the simpleSpriteList variable.
     * @throws IOException throws IOExpeption if the file could not be found
     */
    public static Planet createPlanet() throws IOException {
        if (planetSprites == null) {
            planetSprites = new ArrayList<>();
            planetSprites.add(FileHelper.createFxImage("assets/Planets/Andoria.png"));
            planetSprites.add(FileHelper.createFxImage("assets/Planets/DeathStar.png"));
            planetSprites.add(FileHelper.createFxImage("assets/Planets/Earth.png"));
            planetSprites.add(FileHelper.createFxImage("assets/Planets/Juno.png"));
            planetSprites.add(FileHelper.createFxImage("assets/Planets/Kai.png"));
            planetSprites.add(FileHelper.createFxImage("assets/Planets/Myra.png"));
            planetSprites.add(FileHelper.createFxImage("assets/Planets/Pluto.png"));
            planetSprites.add(FileHelper.createFxImage("assets/Planets/Ryla.png"));
            planetSprites.add(FileHelper.createFxImage("assets/Planets/Zenn.png"));
            planetSprites.add(FileHelper.createFxImage("assets/Planets/Zodd.png"));
        }

        SimpleSpriteList planetList = new SimpleSpriteList();
        planetList.addSprite(planetSprites.get(planetChoice));
        planetChoice++;
        if(planetChoice > planetSprites.size()-1) planetChoice = 0;
        return new Planet(planetList);
    }

    /**
     * creates Meteorite objects. makes new arraylist with the meteorite imange and adds this to a new meteorite.
     *
     * @return a new meteorite
     * @throws IOException throws IOExpeption if the file could not be found
     */
    public static Meteorite createMeteorite() throws IOException {
        if (meteoriteSprites == null) {
            meteoriteSprites = new ArrayList<>();
            meteoriteSprites.add(FileHelper.createFxImage("assets/MovableObjects/Meteorite.png"));
        }
        return new Meteorite(Direction.UP, new SimpleSpriteList(meteoriteSprites));
    }

    /**
     * this method creates the SpacePirate object, it sets the spawn direction to UP and sets the sprites and size.
     * @return a SpacePirate object
     * @throws IOException throws an exception if the file is not found.
     */
    public static SpacePirate createSpacePirate() throws IOException {
        if (spacePirateSprites == null) {
            spacePirateSprites = new HashMap<>();
            spacePirateSprites.put(Direction.LEFT, new ArrayList<Image>());
            spacePirateSprites.put(Direction.RIGHT, new ArrayList<Image>());
            spacePirateSprites.put(Direction.UP, new ArrayList<Image>());
            spacePirateSprites.put(Direction.DOWN, new ArrayList<Image>());

            spacePirateSprites.get(Direction.LEFT).add(FileHelper.createFxImage("assets/MovableObjects/Pirate_Left.png"));
            spacePirateSprites.get(Direction.RIGHT).add(FileHelper.createFxImage("assets/MovableObjects/Pirate_Right.png"));
            spacePirateSprites.get(Direction.UP).add(FileHelper.createFxImage("assets/MovableObjects/Pirate_Up.png"));
            spacePirateSprites.get(Direction.DOWN).add(FileHelper.createFxImage("assets/MovableObjects/Pirate_Down.png"));
        }
        return new SpacePirate(Direction.UP, new DirectionalSpriteList(spacePirateSprites, Direction.UP));
    }

    /**
     * creates a worhmhole object, sets the sprite and size;
     * @return a Wormhole object
     * @throws IOException throws an exception if the file is not found.
     */
    public static Wormhole createWormhole() throws IOException {
        if (wormholeSprites == null) {
            var wormHole = FileHelper.createImage("assets/Wormhole/wormhole.png");
            wormholeSprites = SpriteMapParser.parseSpriteMapToImages(wormHole, 64, 64);
        }
        return new Wormhole(new SimpleSpriteList(wormholeSprites));
    }
}
