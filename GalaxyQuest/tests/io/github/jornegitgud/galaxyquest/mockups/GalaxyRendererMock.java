package io.github.jornegitgud.galaxyquest.mockups;

import io.github.jornegitgud.galaxyquest.Coordinate;
import io.github.jornegitgud.galaxyquest.GalaxyRenderer;
import io.github.jornegitgud.galaxyquest.GalaxySettings;
import io.github.jornegitgud.galaxyquest.Tile;
import javafx.stage.Stage;

import java.io.IOException;

public class GalaxyRendererMock extends GalaxyRenderer {
    public GalaxyRendererMock(Stage stage, GalaxySettings settings) throws IOException {
        super(stage, settings);
    }

    //black magic to prevent loosening up production code.
    public Coordinate getCoordinate(Tile tile) {
        return tile.getCoordinate(this);
    }
}
