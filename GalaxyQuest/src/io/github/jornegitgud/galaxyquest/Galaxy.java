package io.github.jornegitgud.galaxyquest;

public class Galaxy {

    private GalaxySettings settings;
    private String name;


    public Galaxy(String name, GalaxySettings settings) {
        this.name = name;
        this.settings = settings;
    }

    public GalaxySettings getSettings() {
        return settings;
    }
}
