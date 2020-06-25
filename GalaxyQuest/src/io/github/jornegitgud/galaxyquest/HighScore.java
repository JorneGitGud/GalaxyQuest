package io.github.jornegitgud.galaxyquest;


public class HighScore {

    String name;
    int score;
    int setSecPerTile = 2;
    int planetValue = 100;
    int obstacleValue = 200;
    GalaxySettings galaxySettings;


    public HighScore(String name, int elapsedSeconds, GalaxySettings galaxySettings) {
        this.name = name.toUpperCase();
        this.galaxySettings = galaxySettings;
        this.score = calculateScore(elapsedSeconds);

    }


    private int calculateScore(int elapsedSeconds) {
        double gridSize = galaxySettings.getGalaxySize();
        double numberOfObstacles = galaxySettings.getPirateCount() + galaxySettings.getMeteoriteCount();
        double setPlanets = galaxySettings.getPlanetCount();
        double maxPlanets = (galaxySettings.getGalaxySize() * galaxySettings.getPercentPopulated() - numberOfObstacles);


        double setSecondsForGrid = (gridSize * 0.75) * setSecPerTile;
        double timeScoreMultiplier = setSecondsForGrid - elapsedSeconds;
        double planetMultiplier = setPlanets / maxPlanets;
        double planetPoints = planetValue * planetMultiplier;
        double extraPoints = numberOfObstacles * obstacleValue;

        double totalScore = (planetPoints + extraPoints) * timeScoreMultiplier;

        return (int) Math.round(totalScore);

    }
}
