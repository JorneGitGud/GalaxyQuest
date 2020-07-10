package io.github.jornegitgud.galaxyquest.mockups;

import io.github.jornegitgud.galaxyquest.*;
import javafx.stage.Stage;

import java.io.IOException;

public class GalaxyRendererMock extends GalaxyRenderer {

    public GalaxyRendererMock(Stage stage, GalaxySettings settings) throws IOException {
        super(stage, settings);
    }

    public Coordinate getCoordinate(Tile tile) {
        return tile.getCoordinate();
    }

    @Override
    public void addSprite(Galaxy galaxy, Tile tile, String spritePath) { }
}
