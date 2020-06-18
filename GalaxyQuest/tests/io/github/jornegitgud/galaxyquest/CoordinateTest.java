package io.github.jornegitgud.galaxyquest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CoordinateTest {
    @Test
    @DisplayName("get distance to right settings")
    public void getDistanceToTest(){
        // test met juiste waarde & result
        Coordinate testCoord1 = new Coordinate(2,2);
        Coordinate testCoord2 = new Coordinate(5,5);
        int testResult = testCoord1.getDistanceTo(testCoord2);
        Assertions.assertEquals(6,testResult);

        //test met verkeerde waarde
        Coordinate testCoord3 = new Coordinate(-2,-1);
        Coordinate testCoord4 = new Coordinate(5,5);
        int testResult2 = testCoord3.getDistanceTo(testCoord4);
        Assertions.assertEquals(13,testResult2);

        //test met zelfde coordinaten
        Coordinate testCoord5 = new Coordinate(5,5);
        Coordinate testCoord6 = new Coordinate(5,5);
        int testResult3 = testCoord5.getDistanceTo(testCoord6);
        Assertions.assertEquals(0,testResult3);

    }

}
