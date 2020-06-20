package io.github.jornegitgud.galaxyquest;

public class Galaxy {

    private GalaxySettings settings;
    private KeyboardListener keyboardListener;

    private String name;


    public Galaxy(String name, GalaxySettings settings) {
        this.name = name;
        this.settings = settings;
        this.settings.freezeSettings();
    }

    public GalaxySettings getSettings() {
        return settings;
    }

}
