package io.github.jornegitgud.galaxyquest.mockups;

import io.github.jornegitgud.galaxyquest.*;
import javafx.stage.Stage;

import java.io.IOException;

@SuppressWarnings({"ALL", "unused"})
public class GameManagerMock extends GameManager {

    public GameManagerMock(Stage stage, GalaxySettings galaxySettings, GalaxyRenderer galaxyRenderer) throws IOException {
        super(galaxySettings, galaxyRenderer);
    }
}
