package com.starwars.swapi.domain.sorting;

import com.starwars.swapi.domain.model.Planet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.starwars.swapi.domain.PlanetHelper.createTestPlanets;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlanetCreatedSortStrategyTest {

    @Test
    void testSortAscending() {
        //given
        List<Planet> planets = createTestPlanets(3);

        //when
        PlanetCreatedSortStrategy planetCreatedSortStrategy = new PlanetCreatedSortStrategy(true);
        planetCreatedSortStrategy.sort(planets);

        //then
        assertTrue(planets.get(0).getCreated().isBefore(planets.get(1).getCreated()));
        assertTrue(planets.get(1).getCreated().isBefore(planets.get(2).getCreated()));
    }

    @Test
    void testSortDescending() {
        //given
        List<Planet> planets = createTestPlanets(3);

        //when
        PlanetCreatedSortStrategy planetCreatedSortStrategy = new PlanetCreatedSortStrategy(false);
        planetCreatedSortStrategy.sort(planets);

        //then
        assertTrue(planets.get(0).getCreated().isAfter(planets.get(1).getCreated()));
        assertTrue(planets.get(1).getCreated().isAfter(planets.get(2).getCreated()));
    }
}