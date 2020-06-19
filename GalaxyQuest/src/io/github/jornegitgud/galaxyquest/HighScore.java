package io.github.jornegitgud.galaxyquest;


public class HighScore {

    String name;
    int score;
    int setSecPerTile = 2;
    int planetVallue = 100;
    int obstacleVallue = 200;


    public HighScore() {
        this.name = "ABCABC";
        this.score = 5000;
    }

    public HighScore(String name, int elapsedSeconds) {
        this.name = name;
        this.score = calculateScore(elapsedSeconds);

    }


    //should be the actual galaxy settings this is only a temp fix.
    GalaxySettings tempGalaxySettings = new GalaxySettings(5, 5, 5, 3, false, 2);

    private int calculateScore(int elapsedSeconds) {
        double gridSize = tempGalaxySettings.getGalaxySize();
        double numberOfObstacles = tempGalaxySettings.getPirateCount() + tempGalaxySettings.getMeteoriteCount();
        double setPlanets = tempGalaxySettings.getPlanetCount();
        double maxPlanets = (tempGalaxySettings.getGalaxySize() * tempGalaxySettings.getPERCENT_POPULATED() - numberOfObstacles);


        double setSecondsForGrid = (gridSize * 0.75) * setSecPerTile;
        double timeScoreMultiplier = setSecondsForGrid - elapsedSeconds;
        double planetMultiplier = setPlanets / maxPlanets;
        double planetPoints = planetVallue * planetMultiplier;
        double extraPoints = numberOfObstacles * obstacleVallue;

        double totalScore = (planetPoints + extraPoints) * timeScoreMultiplier;
        return (int) totalScore;
    }
}
