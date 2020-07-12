package io.github.jornegitgud.galaxyquest.mockups;

import io.github.jornegitgud.galaxyquest.*;
import javafx.stage.Stage;

import java.io.IOException;

public class GalaxyRendererMock extends GalaxyRenderer {

    public GalaxyRendererMock(Stage stage, GalaxySettings settings) throws IOException {
        super(stage, settings);
    }

    @SuppressWarnings("unused")
    public Coordinate getCoordinate(Tile tile) {
        return tile.getCoordinate();
    }

    @Override //overriding this because we cannot add sprites on the fly from Unit tests. JavaFX runs in another thread and throws an IllegalAccessException when we try to do this.
    public void addSprite(Galaxy galaxy, Tile tile, String spritePath) { }
}
