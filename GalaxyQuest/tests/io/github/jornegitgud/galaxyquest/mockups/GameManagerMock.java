package io.github.jornegitgud.galaxyquest.mockups;

import io.github.jornegitgud.galaxyquest.*;
import io.github.jornegitgud.galaxyquest.gameObjects.Planet;
import io.github.jornegitgud.galaxyquest.gameObjects.Wormhole;
import javafx.stage.Stage;

import java.io.IOException;

public class GameManagerMock extends GameManager {
    private GalaxyRendererMock mock;

    public GameManagerMock(Stage stage, GalaxySettings galaxySettings, GalaxyRenderer galaxyRenderer) throws IOException {
        super(stage, galaxySettings, galaxyRenderer);
    }

    public Galaxy getGalaxy() {
        return super.getGalaxy(this.mock);
    }
}
