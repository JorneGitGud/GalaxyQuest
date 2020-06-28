package io.github.jornegitgud.galaxyquest;

/**
 * This is the class that holds all the settings for the galaxy.
 * this class is also responsible for changing these settings.
 */
public class GalaxySettings {
    private final double PERCENT_POPULATED = 0.5;
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


    /**
     * Default constructor for the galaxy settings
     * sets the totalObjects and the galaxySize used for calculations.
     */
    public GalaxySettings() {
        totalObjects = planetCount + pirateCount + meteoriteCount;
        galaxySize = width * height;
    }

    /**
     * Constructor to build a custom galaxy.
     *
     * @param width sets the width of the galaxy but, checks if it is within the min and max width limits of the galaxy first
     * @param height sets the height of the galaxy but, checks if it is within the min and max height limits of the galaxy first
     * @param planetCount sets the planetCount of the galaxy but, checks if the galaxy wont be to populated first
     * @param pirateCount sets the pirateCount of the galaxy but, checks if the galaxy wont be to populated first
     * @param settingsFrozen freezes the galaxy settings so the numbers cant be edited in the middle of a game.
     * @param meteoriteCount sets the meteoriteCount of the galaxy but, checks if the galaxy wont be to populated first
     */
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

    /**
     * a method to check if the settings for the galaxy can be altered.
     * @return boolean
     */
    public boolean getCanBeAltered() {
        return !settingsFrozen;
    }

    /**
     * A method to freeze the game settings.
     */
    public void freezeSettings() {
        settingsFrozen = true;
    }

    /**
     * a method to open up the settings for the galaxy
     */
    public void unfreezeSettings() { settingsFrozen = false;}


    /**
     * This method sets the width of the galaxy but, checks if it is within the min and max width limits of the galaxy first
     * @param width used to set the width of the galaxy.
     */
    public void setWidth(int width) {
        if (width > LIMIT_MIN_LENGTH && width < LIMIT_MAX_LENGTH && !settingsFrozen && totalObjects < this.height * width * PERCENT_POPULATED) {
            this.width = width;
            this.galaxySize = this.height * this.width;
        }
    }

    /**
     * This method sets the height of the galaxy but, checks if it is within the min and max height limits of the galaxy first
     * @param height used to set the width of the galaxy.
     */
    public void setHeight(int height) {
        if (height > LIMIT_MIN_LENGTH && height < LIMIT_MAX_LENGTH && !settingsFrozen && totalObjects < this.width * height * PERCENT_POPULATED) {
            this.height = height;
            this.galaxySize = this.height * this.width;
        }
    }

    /**
     * sets the planetCount of the galaxy but, checks if the galaxy wont be to populated first
     * @param planetCount used to set the planet count.
     */
    public void setPlanetCount(int planetCount) {
        if(planetCount < Defaults.PLANET_COUNT)
            planetCount = Defaults.PLANET_COUNT;
        if (!settingsFrozen && planetCount + pirateCount + meteoriteCount < galaxySize * PERCENT_POPULATED) {
            this.planetCount = planetCount;
            this.totalObjects = planetCount + pirateCount + meteoriteCount;
        }
    }

    /**
     * sets the pirateCount of the galaxy but, checks if the galaxy wont be to populated first
     * @param pirateCount used to set the pirate count.
     */
    public void setPirateCount(int pirateCount) {
        if (!settingsFrozen && planetCount + pirateCount + meteoriteCount < galaxySize * PERCENT_POPULATED) {
            this.pirateCount = pirateCount;
            this.totalObjects = planetCount + pirateCount + meteoriteCount;
        }
    }

    /**
     * sets the meteoriteCount of the galaxy but, checks if the galaxy wont be to populated first
     * @param meteoriteCount used to set the pirate count.
     */
    public void setMeteoriteCount(int meteoriteCount) {
        if(meteoriteCount < Defaults.METEORITE_COUNT)
            meteoriteCount = Defaults.METEORITE_COUNT;
        if (!settingsFrozen && planetCount + pirateCount + meteoriteCount < galaxySize * PERCENT_POPULATED) {
            this.meteoriteCount = meteoriteCount;
            this.totalObjects = planetCount + pirateCount + meteoriteCount;
        }
    }

    /**
     * This method is used to get the width of the galaxy
     * @return the width of the galaxy
     */
    public int getWidth() {
        return width;
    }

    /**
     * This method is used to get the height of the galaxy
     * @return the height of the galaxy
     */
    public int getHeight() {
        return height;
    }

    /**
     * This method is used to get the planetCount of the galaxy
     * @return the planetCount of the galaxy
     */
    public int getPlanetCount() {
        return planetCount;
    }

    /**
     * This method is used to get the pirateCount of the galaxy
     * @return the pirateCount of the galaxy
     */
    public int getPirateCount() {
        return pirateCount;
    }

    /**
     * This method is used to get the settingsFrozen of the galaxy
     * @return the settingsFrozen of the galaxy
     */
    public Boolean getSettingsFrozen() {
        return settingsFrozen;
    }

    /**
     * This method is used to get the meteoriteCount of the galaxy
     * @return the meteoriteCount of the galaxy
     */
    public int getMeteoriteCount() {
        return meteoriteCount;
    }

    /**
     * This method is used to get the galaxySize of the galaxy
     * @return the galaxySize of the galaxy
     */
    public int getGalaxySize() {
        return galaxySize;
    }

    /**
     * This method is used to get the PERCENT_POPULATED in the galaxy
     * @return the PERCENT_POPULATED in the galaxy
     */
    public double getPercentPopulated() {
        return PERCENT_POPULATED;
    }

}
