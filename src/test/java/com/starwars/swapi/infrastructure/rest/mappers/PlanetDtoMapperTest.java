package com.starwars.swapi.infrastructure.rest.mappers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanetDtoMapperTest {

    private final PlanetDtoMapper planetMapper = new PlanetDtoMapper();

    @Test
    void testToDto_NullInput() {
        assertNull(planetMapper.toDto(null));
    }

    @Test
    void testMapperSuccessful() {
        var planet = com.starwars.swapi.domain.PlanetHelper.createTestPlanets(1).get(0);

        var dto = planetMapper.toDto(planet);

        assertNotNull(dto);
        assertEquals(planet.getName(), dto.getName());
        assertEquals(planet.getRotationPeriod(), dto.getRotationPeriod());
        assertEquals(planet.getOrbitalPeriod(), dto.getOrbitalPeriod());
        assertEquals(planet.getDiameter(), dto.getDiameter());
        assertEquals(planet.getClimate(), dto.getClimate());
        assertEquals(planet.getGravity(), dto.getGravity());
        assertEquals(planet.getTerrain(), dto.getTerrain());
        assertEquals(planet.getSurfaceWater(), dto.getSurfaceWater());
        assertEquals(planet.getPopulation(), dto.getPopulation());
        assertEquals(planet.getCreated(), dto.getCreated());
        assertEquals(planet.getEdited(), dto.getEdited());
        assertEquals(planet.getUrl(), dto.getUrl());
    }
}