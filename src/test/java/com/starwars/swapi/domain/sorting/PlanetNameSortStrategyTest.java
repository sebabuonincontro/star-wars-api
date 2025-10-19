package com.starwars.swapi.domain.sorting;

import org.junit.jupiter.api.Test;

import static com.starwars.swapi.domain.PlanetHelper.createPlanet;
import static org.junit.jupiter.api.Assertions.*;

class PlanetNameSortStrategyTest {

    @Test
    void testSortAscending() {
        //given
        var planets = java.util.Arrays.asList(
                createPlanet("Tatooine"),
                createPlanet("Alderaan"),
                createPlanet("Hoth"));

        //when
        PlanetNameSortStrategy planetNameSortStrategy = new PlanetNameSortStrategy(true);
        planetNameSortStrategy.sort(planets);

        //then
        assertEquals("Alderaan", planets.get(0).getName());
        assertEquals("Hoth", planets.get(1).getName());
        assertEquals("Tatooine", planets.get(2).getName());
    }

    @Test
    void testSortDescending() {
        //given
        var planets = java.util.Arrays.asList(
                createPlanet("Tatooine"),
                createPlanet("Alderaan"),
                createPlanet("Hoth"));

        //when
        PlanetNameSortStrategy planetNameSortStrategy = new PlanetNameSortStrategy(false);
        planetNameSortStrategy.sort(planets);

        //then
        assertEquals("Alderaan", planets.get(2).getName());
        assertEquals("Hoth", planets.get(1).getName());
        assertEquals("Tatooine", planets.get(0).getName());
    }
}