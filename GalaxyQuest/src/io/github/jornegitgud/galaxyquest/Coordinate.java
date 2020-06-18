package io.github.jornegitgud.galaxyquest;

public class Coordinate {
    public int x;
    public int y;

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
