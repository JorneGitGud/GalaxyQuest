package io.github.jornegitgud.galaxyquest;

/**
 * the Coordinate class is used on the {@link Tile} class.
 * it has x & y coordinates
 *
 */
public class Coordinate {
    public int x;
    public int y;

    /**
     * this method calculates the distance between this coordinate and the coordinate that it takes in.
     *
     * @param coordinate the coordinate that the distance to should calculated.
     * @return returns an int that is the amount of tiles between the two coordinates.
     */
    public int getDistanceTo(Coordinate coordinate) {
        int difX = Math.abs(this.x - coordinate.x);
        int difY = Math.abs(this.y - coordinate.y);
        return difY + difX;
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
