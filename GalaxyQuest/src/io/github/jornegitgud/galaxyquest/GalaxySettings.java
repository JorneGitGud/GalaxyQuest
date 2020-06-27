package io.github.jornegitgud.galaxyquest;

public class GalaxySettings {
    private final double PERCENT_POPULATED = 0.5;
    //private final int WORMHOLE_COUNT = 1;
    private final int LIMIT_MIN_LENGTH = 3;
    private final int LIMIT_MAX_LENGTH = 24;

    private int totalObjects;
    private int galaxySize;
    private int width = Defaults.GALAXY_WIDTH;
    private int height = Defaults.GALAXY_HEIGHT;
    private int planetCount = Defaults.PLANET_COUNT;
    private int pirateCount = Defaults.PIRATE_COUNT;
    private int meteoriteCount = Defaults.METEORITE_COUNT;


    private boolean settingsFrozen;

    public GalaxySettings() {
        totalObjects = planetCount + pirateCount + meteoriteCount;
        galaxySize = width * height;
    }


    public GalaxySettings (int width, int height, int planetCount, int pirateCount, Boolean settingsFrozen, int meteoriteCount) {

        if (width > LIMIT_MIN_LENGTH && width < LIMIT_MAX_LENGTH) this.width = width;
        if (height > LIMIT_MIN_LENGTH && height < LIMIT_MAX_LENGTH) this.height = height;

        totalObjects = planetCount + pirateCount + meteoriteCount;
        galaxySize = this.width * this.height;

        if (totalObjects < galaxySize * PERCENT_POPULATED) {
            this.planetCount = planetCount;
            this.meteoriteCount = meteoriteCount;
            this.pirateCount = pirateCount;
        }

        this.settingsFrozen = settingsFrozen;
    }

    public boolean getCanBeAltered() {
        return !settingsFrozen;
    }

    public void freezeSettings() {
        settingsFrozen = true;
    }

    public void unfreezeSettings() { settingsFrozen = false;}

    public void setWidth(int width) {
        if (width > LIMIT_MIN_LENGTH && width < LIMIT_MAX_LENGTH && !settingsFrozen && totalObjects < this.height * width * PERCENT_POPULATED) {
            this.width = width;
            this.galaxySize = this.height * this.width;
        }
    }

    public void setHeight(int height) {
        if (height > LIMIT_MIN_LENGTH && height < LIMIT_MAX_LENGTH && !settingsFrozen && totalObjects < this.width * height * PERCENT_POPULATED) {
            this.height = height;
            this.galaxySize = this.height * this.width;
        }
    }

    public void setPlanetCount(int planetCount) {
        if (!settingsFrozen && planetCount + pirateCount + meteoriteCount < galaxySize * PERCENT_POPULATED) {
            this.planetCount = planetCount;
            this.totalObjects = planetCount + pirateCount + meteoriteCount;
        }
    }

    public void setPirateCount(int pirateCount) {
        if (!settingsFrozen && planetCount + pirateCount + meteoriteCount < galaxySize * PERCENT_POPULATED) {
            this.pirateCount = pirateCount;
            this.totalObjects = planetCount + pirateCount + meteoriteCount;
        }
    }

    public void setMeteoriteCount(int meteoriteCount) {
        if (!settingsFrozen && planetCount + pirateCount + meteoriteCount < galaxySize * PERCENT_POPULATED) {
            this.meteoriteCount = meteoriteCount;
            this.totalObjects = planetCount + pirateCount + meteoriteCount;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPlanetCount() {
        return planetCount;
    }

    public int getPirateCount() {
        return pirateCount;
    }

    public Boolean getSettingsFrozen() {
        return settingsFrozen;
    }

    public int getMeteoriteCount() {
        return meteoriteCount;
    }

    public int getGalaxySize() {
        return galaxySize;
    }

    public int getTotalObjects() {
        return totalObjects;
    }

    public double getPercentPopulated() {
        return PERCENT_POPULATED;
    }

}
