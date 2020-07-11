package io.github.jornegitgud.galaxyquest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TileTests {
    @Test
    @DisplayName("tile to tile directions test")
    public void getDirectionsToTest(){
        // test met juiste waarde & result
        Tile testTile1 = new Tile(new Coordinate(2,2));
        Tile testTile2 = new Tile(new Coordinate(5,5));
        Direction testDir1 = testTile1.getDirectionTo(testTile2);
        Assertions.assertEquals(Direction.RIGHT, testDir1);

        //test met verkeerde waarde
        Tile testTile3 = new Tile(new Coordinate(-2,1));
        Tile testTile4 = new Tile(new Coordinate(1,5));
        Direction testDir2 = testTile3.getDirectionTo(testTile4);
        Assertions.assertEquals(Direction.DOWN, testDir2);

        //test met zelfde coordinaten
        Tile testTile5 = new Tile(new Coordinate(11,11));
        Tile testTile6 = new Tile(new Coordinate(11,11));
        Direction testDir3 = testTile5.getDirectionTo(testTile6);
        Assertions.assertEquals(null, testDir3);

    }

}
