package io.github.jornegitgud.galaxyquest.mockups;

import io.github.jornegitgud.galaxyquest.*;
import javafx.stage.Stage;

import java.io.IOException;

public class GameManagerMock extends GameManager {
    private GalaxyRendererMock mock;

    public GameManagerMock(Stage stage, GalaxySettings galaxySettings, GalaxyRenderer galaxyRenderer) throws IOException {
        super(galaxySettings, galaxyRenderer);
    }

    public Galaxy getGalaxy() {
        return super.getGalaxy();
    }
}
