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
        GalaxySettings gameObject = new GalaxySettings(2, 2, 4, 4, true, 3);
        Assertions.assertEquals(Defaults.GALAXY_WIDTH, gameObject.getWidth());
        Assertions.assertEquals(Defaults.GALAXY_HEIGHT, gameObject.getHeight());
        Assertions.assertEquals(4, gameObject.getPlanetCount());
        Assertions.assertEquals(4, gameObject.getPirateCount());
        Assertions.assertEquals(3, gameObject.getMeteoriteCount());
        Assertions.assertEquals(true, gameObject.getSettingsFrozen());

    }
    @Test
    @DisplayName("constructor impossible settings")
    void constructorTest3() {
        GalaxySettings gameObject = new GalaxySettings(50, 50, 60, 20, true, 20);
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
        GalaxySettings galaxySettings = new GalaxySettings(50, 50, 10, 10, false, 10);
        galaxySettings.setWidth(10);
        galaxySettings.setHeight(10);
        galaxySettings.setPlanetCount(5);
        galaxySettings.setPirateCount(3);
        galaxySettings.setMeteoriteCount(6);

        //should work
        Assertions.assertEquals(10,galaxySettings.getWidth());
        Assertions.assertEquals(10,galaxySettings.getHeight());
        Assertions.assertEquals(5,galaxySettings.getPlanetCount());
        Assertions.assertEquals(3,galaxySettings.getPirateCount());
        Assertions.assertEquals(6,galaxySettings.getMeteoriteCount());


        //shouldn't work
        galaxySettings.setWidth(200);
        galaxySettings.setHeight(200);
        galaxySettings.setPlanetCount(200);
        galaxySettings.setPirateCount(300);
        galaxySettings.setMeteoriteCount(30000000);

        Assertions.assertEquals(10,galaxySettings.getWidth());
        Assertions.assertEquals(10,galaxySettings.getHeight());
        Assertions.assertEquals(5,galaxySettings.getPlanetCount());
        Assertions.assertEquals(3,galaxySettings.getPirateCount());
        Assertions.assertEquals(6,galaxySettings.getMeteoriteCount());

        galaxySettings.freezeSettings();
        Assertions.assertFalse(galaxySettings.getCanBeAltered());
        galaxySettings.setWidth(12);
        galaxySettings.setHeight(12);
        galaxySettings.setPlanetCount(8);
        galaxySettings.setPirateCount(7);
        galaxySettings.setMeteoriteCount(9);

        //should work
        Assertions.assertEquals(10,galaxySettings.getWidth());
        Assertions.assertEquals(10,galaxySettings.getHeight());
        Assertions.assertEquals(5,galaxySettings.getPlanetCount());
        Assertions.assertEquals(3,galaxySettings.getPirateCount());
        Assertions.assertEquals(6,galaxySettings.getMeteoriteCount());
        galaxySettings.unfreezeSettings();

        //requirement: always at least 3 planets
        galaxySettings.setPlanetCount(0);
        Assertions.assertTrue(galaxySettings.getPlanetCount() >= Defaults.PLANET_COUNT);

        //requirement: always at least 5 meteorites
        galaxySettings.setMeteoriteCount(0);
        Assertions.assertTrue(galaxySettings.getMeteoriteCount() >= Defaults.METEORITE_COUNT);
    }
}
