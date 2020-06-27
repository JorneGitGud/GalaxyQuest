package io.github.jornegitgud.galaxyquest;

import io.github.jornegitgud.galaxyquest.gameObjects.Meteorite;
import io.github.jornegitgud.galaxyquest.gameObjects.Planet;
import io.github.jornegitgud.galaxyquest.gameObjects.Wormhole;
import io.github.jornegitgud.galaxyquest.mockups.GalaxyRendererMock;
import io.github.jornegitgud.galaxyquest.mockups.GameManagerMock;
import javafx.stage.Stage;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.ArrayList;

@ExtendWith(ApplicationExtension.class)
public class GameManagerTests {
    private GameManagerMock gameManager;
    private GameManagerMock gameManager2;
    private GalaxyRendererMock mockRenderer;
    private GalaxySettings galaxySettings;
    private Galaxy galaxy;

    @Start
    private void start(Stage stage) {
        this.galaxySettings = new GalaxySettings();
        this.galaxy = new Galaxy(galaxySettings);
        try {
            this.mockRenderer = new GalaxyRendererMock(stage, galaxySettings);
            this.gameManager = new GameManagerMock(stage, galaxySettings, mockRenderer);
            this.gameManager2 = new GameManagerMock(stage, new GalaxySettings(16, 16, 5, 2, false, 5), mockRenderer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("All tiles know their neighbours correctly")
    public void TilesKnowTheirNeighboursTest() {
        for (var x = 0; x < galaxySettings.getWidth(); x++) {
            for (var y = 0; y < galaxySettings.getHeight(); y++) {
                var tile = galaxy.getGalaxyTile(mockRenderer, x, y);
                var coordinate = mockRenderer.getCoordinate(tile);

                var tileAbove = tile.getTileAbove();
                var tileRight = tile.getTileRight();
                var tileBelow = tile.getTileBelow();
                var tileLeft = tile.getTileLeft();

                if (coordinate.x == 0)
                    Assertions.assertNull(tileLeft);
                else
                    Assertions.assertEquals(mockRenderer.getCoordinate(tileLeft).x, coordinate.x - 1);

                if (coordinate.y == 0)
                    Assertions.assertNull(tileAbove);
                else
                    Assertions.assertEquals(mockRenderer.getCoordinate(tileAbove).y, coordinate.y - 1);

                if (coordinate.x == galaxySettings.getWidth() - 1)
                    Assertions.assertNull(tileRight);
                else
                    Assertions.assertEquals(mockRenderer.getCoordinate(tileRight).x, coordinate.x + 1);

                if (coordinate.y == galaxySettings.getHeight() - 1)
                    Assertions.assertNull(tileBelow);
                else
                    Assertions.assertEquals(mockRenderer.getCoordinate(tileBelow).y, coordinate.y + 1);

            }
        }
    }

    @Test
    @DisplayName("spaceShip spawns in upper left")
    public void SpaceShipSpawnsInUpperLeft() {
        //gameManager created in start method because javaFX actions need to happen via testFX framework.

        var galaxy1ShipCoord = mockRenderer.getCoordinate(gameManager.getGalaxy().getPlayer().getTile());
        var galaxy2ShipCoord = mockRenderer.getCoordinate(gameManager2.getGalaxy().getPlayer().getTile());

        Assertions.assertTrue(
                galaxy1ShipCoord.x == 0 && galaxy1ShipCoord.y == 0
                        && galaxy2ShipCoord.x == 0 && galaxy2ShipCoord.y == 0
        );
    }

    @Test
    @DisplayName("Meteorites spawn in random locations")
    public void MeteoritesSpawnInRandomLocation() {
        String galaxy1MeteoriteCoords = "";
        String galaxy2MeteoriteCoords = "";

        for (var x = 0; x < gameManager.getGalaxy().getSettings().getWidth(); x++)
            for (var y = 0; y < gameManager.getGalaxy().getSettings().getHeight(); y++) {
                var tile = gameManager.getGalaxy().getGalaxyTile(mockRenderer, x, y);
                if (tile.contains(Meteorite.class)) {
                    var coordinate = mockRenderer.getCoordinate(tile);
                    galaxy1MeteoriteCoords += coordinate.x + "," + coordinate.y + "|";
                }
            }

        for (var x = 0; x < gameManager2.getGalaxy().getSettings().getWidth(); x++)
            for (var y = 0; y < gameManager2.getGalaxy().getSettings().getHeight(); y++) {
                var tile = gameManager2.getGalaxy().getGalaxyTile(mockRenderer, x, y);
                if (tile.contains(Meteorite.class)) {
                    var coordinate = mockRenderer.getCoordinate(tile);
                    galaxy2MeteoriteCoords += coordinate.x + "," + coordinate.y + "|";
                }
            }

        Assertions.assertNotEquals(galaxy1MeteoriteCoords, galaxy2MeteoriteCoords);
    }

    @Test
    @DisplayName("Wormhole spawns in random location")
    public void WormholeSpawnsInRandomLocation() {
        Coordinate wormhole1Coordinate = null;
        Coordinate wormhole2Coordinate = null;
        for (var x = 0; x < gameManager.getGalaxy().getSettings().getWidth(); x++)
            for (var y = 0; y < gameManager.getGalaxy().getSettings().getHeight(); y++) {
                var tile = gameManager.getGalaxy().getGalaxyTile(mockRenderer, x, y);
                if (tile.contains(Wormhole.class)) {
                    wormhole1Coordinate = mockRenderer.getCoordinate(tile);
                }
            }

        for (var x = 0; x < gameManager2.getGalaxy().getSettings().getWidth(); x++)
            for (var y = 0; y < gameManager2.getGalaxy().getSettings().getHeight(); y++) {
                var tile = gameManager2.getGalaxy().getGalaxyTile(mockRenderer, x, y);
                if (tile.contains(Wormhole.class)) {
                    wormhole2Coordinate = mockRenderer.getCoordinate(tile);
                }
            }

        Assertions.assertNotNull(wormhole1Coordinate);
        Assertions.assertNotNull(wormhole2Coordinate);

        if (wormhole1Coordinate != null && wormhole2Coordinate != null)
            Assertions.assertFalse(wormhole1Coordinate.x == wormhole2Coordinate.x && wormhole1Coordinate.y == wormhole2Coordinate.y);
    }

    @Test
    @DisplayName("Wormhole only visible after all planets have been visited")
    public void WormholeOnlyVisibleAfterAllPlanetsHaveBeenVisited() {
        var galaxy = gameManager2.getGalaxy();
        var objects = galaxy.getObjects();
        Wormhole wormhole = null;
        for (var object : objects) {
            if (object instanceof Wormhole) {
                wormhole = (Wormhole) object;
                break;
            }
        }

        Assertions.assertFalse(wormhole.isActive());
        var player = galaxy.getPlayer();
        var planetsActivated = 0;

        for(var object : objects)
            if(object instanceof Planet) {
                gameManager2.setPlanetVisited((Planet)object);
                Assertions.assertTrue(((Planet) object).hasBeenVisited());
                planetsActivated++;
                if(planetsActivated < galaxy.getSettings().getPlanetCount())
                    Assertions.assertFalse(wormhole.isActive());
                else
                    Assertions.assertTrue(wormhole.isActive());
            }
    }
}