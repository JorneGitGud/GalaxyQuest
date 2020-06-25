package io.github.jornegitgud.galaxyquest;


public class HighScore {

    String name = "";
    int score;
    double setSecPerTile = 0.5;
    int planetValue = 100;
    int obstacleValue = 100;
    GalaxySettings galaxySettings;


    public HighScore(int elapsedSeconds, GalaxySettings galaxySettings) {
        this.galaxySettings = galaxySettings;
        this.score = calculateScore(elapsedSeconds);

    }

    public void setName(String name) {
        if(name.length() > 6)
            name = name.substring(0, 6);
        this.name = name.toUpperCase();
    }





    private int calculateScore(int elapsedSeconds) {
        double gridSize = galaxySettings.getGalaxySize();
        double numberOfObstacles = galaxySettings.getPirateCount() + galaxySettings.getMeteoriteCount();
        double setPlanets = galaxySettings.getPlanetCount();
        double maxPlanets = (galaxySettings.getGalaxySize() * galaxySettings.getPercentPopulated() - numberOfObstacles);


        double setSecondsForGrid = (gridSize * 0.85) * setSecPerTile;
        double timeScoreMultiplier = setSecondsForGrid - elapsedSeconds;
        if(timeScoreMultiplier<0) timeScoreMultiplier = 0.5;
        double planetMultiplier = setPlanets / maxPlanets;
        double planetPoints = (planetValue * planetMultiplier)*setPlanets;
        double extraPoints = numberOfObstacles * obstacleValue;

        double totalScore = (planetPoints + extraPoints) * timeScoreMultiplier;

        System.out.println( "the highscore was calculated using the folowing variables: ");
        System.out.println("set seconds for grid : "+  setSecondsForGrid);
        System.out.println( "elapsed time : " + elapsedSeconds);
        System.out.println( "timescore multiplier : "+ timeScoreMultiplier);
        System.out.println( "planet multiplier : "+ planetMultiplier);
        System.out.println( "planet points : " + planetPoints);
        System.out.println( "extra points : "+  extraPoints);
        System.out.println( (int) Math.round(totalScore)+ "\n");
        return (int) Math.round(totalScore);

    }
}
