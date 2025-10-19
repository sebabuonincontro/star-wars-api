package com.starwars.swapi.domain;

import com.starwars.swapi.domain.model.Planet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for creating test Planet instances.
 */
public class PlanetHelper {

    public static List<Planet> createTestPlanets(int count) {
        List<Planet> planets = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            planets.add(createPlanet("Planet " + i));
        }
        return planets;
    }

    public static Planet createPlanet(String name) {
        return new Planet(name, "24", "365", "12000", "temperate",
                "1 standard", "grasslands", "40", "1000000",
                LocalDateTime.now(), LocalDateTime.now(),
                "https://swapi.dev/api/planets/1/");
    }
}
