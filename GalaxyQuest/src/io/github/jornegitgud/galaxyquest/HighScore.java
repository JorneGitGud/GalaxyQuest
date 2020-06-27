package io.github.jornegitgud.galaxyquest;


public class HighScore implements Comparable {

    private String name = "default";
    private int score = 0;

    private final double SET_SEC_PER_TILE = 0.5;
    private final int PLANET_VALUE = 100;
    private final int OBSTACLE_VALUE = 100;

    public HighScore() {
    }

    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public HighScore(int elapsedSeconds, GalaxySettings galaxySettings) {
        this.score = calculateScore(elapsedSeconds, galaxySettings);

    }

    public void setName(String name) {
        if (name.length() > 6)
            name = name.substring(0, 6);
        this.name = name.toUpperCase();
    }


    private int calculateScore(int elapsedSeconds, GalaxySettings galaxySettings) {
        double gridSize = galaxySettings.getGalaxySize();
        double numberOfObstacles = galaxySettings.getPirateCount() + galaxySettings.getMeteoriteCount();
        double setPlanets = galaxySettings.getPlanetCount();
        double maxPlanets = (galaxySettings.getGalaxySize() * galaxySettings.getPercentPopulated() - numberOfObstacles);


        double setSecondsForGrid = (gridSize * 0.85) * SET_SEC_PER_TILE;
        double timeScoreMultiplier = setSecondsForGrid - elapsedSeconds;
        if (timeScoreMultiplier < 0) timeScoreMultiplier = 0.5;
        double planetMultiplier = setPlanets / maxPlanets;
        double planetPoints = (PLANET_VALUE * planetMultiplier) * setPlanets;
        double extraPoints = numberOfObstacles * OBSTACLE_VALUE;

        double totalScore = (planetPoints + extraPoints) * timeScoreMultiplier;
        System.out.println(name);
        System.out.println("the highscore was calculated using the folowing variables: ");
        System.out.println("set seconds for grid : " + setSecondsForGrid);
        System.out.println("elapsed time : " + elapsedSeconds);
        System.out.println("timescore multiplier : " + timeScoreMultiplier);
        System.out.println("planet multiplier : " + planetMultiplier);
        System.out.println("planet points : " + planetPoints);
        System.out.println("extra points : " + extraPoints);
        System.out.println((int) Math.round(totalScore) + "\n");
        return (int) Math.round(totalScore);
    }

    public String toString() {
        return this.name + " : " + this.score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Object other) {
        if(!(other instanceof HighScore))
            return 1;
        return ((HighScore) other).getScore() - this.getScore();
    }
}
