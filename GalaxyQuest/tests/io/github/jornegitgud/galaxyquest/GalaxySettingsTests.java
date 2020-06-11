package io.github.jornegitgud.galaxyquest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GalaxySettingsTests {

    @Test
    @DisplayName("constructor right settings")
    void constructorTest() {
        GalaxySettings gameObject = new GalaxySettings(5, 5, 3, 2, false, 2);
        Assertions.assertEquals(5, gameObject.getWidth());
        Assertions.assertEquals(5, gameObject.getHeight());
        Assertions.assertEquals(3, gameObject.getPlanetCount());
        Assertions.assertEquals(2, gameObject.getPirateCount());
        Assertions.assertEquals(2, gameObject.getMeteoriteCount());
        Assertions.assertEquals(false, gameObject.getSettingsFrozen());

    }
    @Test
    @DisplayName("constructor wrong settings")
    void constructorTest2() {
        GalaxySettings gameObject = new GalaxySettings(4, 4, 4, 4, true, 0);
        Assertions.assertEquals(Defaults.GALAXY_WIDTH, gameObject.getWidth());
        Assertions.assertEquals(Defaults.GALAXY_HEIGHT, gameObject.getHeight());
        Assertions.assertEquals(Defaults.PLANET_COUNT, gameObject.getPlanetCount());
        Assertions.assertEquals(Defaults.PIRATE_COUNT, gameObject.getPirateCount());
        Assertions.assertEquals(Defaults.METEORITE_COUNT, gameObject.getMeteoriteCount());
        Assertions.assertEquals(true, gameObject.getSettingsFrozen());

    }
    @Test
    @DisplayName("constructor impossible settings")
    void constructorTest3() {
        GalaxySettings gameObject = new GalaxySettings(50, 50, 30, 20, true, 20);
        Assertions.assertEquals(Defaults.GALAXY_WIDTH, gameObject.getWidth());
        Assertions.assertEquals(Defaults.GALAXY_HEIGHT, gameObject.getHeight());
        Assertions.assertEquals(Defaults.PLANET_COUNT, gameObject.getPlanetCount());
        Assertions.assertEquals(Defaults.PIRATE_COUNT, gameObject.getPirateCount());
        Assertions.assertEquals(Defaults.METEORITE_COUNT, gameObject.getMeteoriteCount());
        Assertions.assertEquals(true, gameObject.getSettingsFrozen());
    }

    @Test
    @DisplayName("Setters test")
    void settersTest(){
        GalaxySettings gameObject = new GalaxySettings(50, 50, 30, 20, false, 20);
        gameObject.setWidth(10);
        gameObject.setHeight(10);
        gameObject.setPlanetCount(5);
        gameObject.setPirateCount(3);
        gameObject.setMeteoriteCount(3);

        //should work
        Assertions.assertEquals(10,gameObject.getWidth());
        Assertions.assertEquals(10,gameObject.getHeight());
        Assertions.assertEquals(5,gameObject.getPlanetCount());
        Assertions.assertEquals(3,gameObject.getPirateCount());
        Assertions.assertEquals(3,gameObject.getMeteoriteCount());


        //shouldn't work
        gameObject.setWidth(200);
        gameObject.setHeight(200);
        gameObject.setPlanetCount(200);
        gameObject.setPirateCount(300);
        gameObject.setMeteoriteCount(30000000);

        Assertions.assertEquals(10,gameObject.getWidth());
        Assertions.assertEquals(10,gameObject.getHeight());
        Assertions.assertEquals(5,gameObject.getPlanetCount());
        Assertions.assertEquals(3,gameObject.getPirateCount());
        Assertions.assertEquals(3,gameObject.getMeteoriteCount());

        gameObject.freezeSetting();
        Assertions.assertEquals(false, gameObject.getCanBeAltered());
        gameObject.setWidth(12);
        gameObject.setHeight(12);
        gameObject.setPlanetCount(8);
        gameObject.setPirateCount(7);
        gameObject.setMeteoriteCount(9);

        //should work
        Assertions.assertEquals(10,gameObject.getWidth());
        Assertions.assertEquals(10,gameObject.getHeight());
        Assertions.assertEquals(5,gameObject.getPlanetCount());
        Assertions.assertEquals(3,gameObject.getPirateCount());
        Assertions.assertEquals(3,gameObject.getMeteoriteCount());
    }
}
