package io.github.jornegitgud.galaxyquest.mockups;

import io.github.jornegitgud.galaxyquest.*;
import io.github.jornegitgud.galaxyquest.gameObjects.Planet;
import io.github.jornegitgud.galaxyquest.gameObjects.Wormhole;
import javafx.stage.Stage;

import java.io.IOException;

public class GameManagerMock extends GameManager {
    private GalaxyRendererMock mock;
    private int visitedCounter = 0;

    public GameManagerMock(Stage stage, GalaxySettings galaxySettings, GalaxyRendererMock mock) throws IOException {
        super(stage, galaxySettings);
        this.mock = mock;
    }

    public Galaxy getGalaxy() {
        return super.getGalaxy(this.mock);
    }

    public void setPlanetVisited(Planet planet) {
        planet.setVisited();
        visitedCounter++;

        var galaxy = super.getGalaxy(this.mock);
        if(visitedCounter == galaxy.getSettings().getPlanetCount()) {
            for(var object : galaxy.getObjects())
                if(object instanceof Wormhole)
                    ((Wormhole) object).activate();
        }
    }
}
