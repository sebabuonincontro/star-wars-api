package com.starwars.swapi.infrastructure.adapter.mappers;

import com.starwars.swapi.domain.model.PageResponse;
import com.starwars.swapi.domain.model.Planet;
import com.starwars.swapi.infrastructure.adapter.dtos.PlanetDto;
import com.starwars.swapi.infrastructure.adapter.dtos.SwapiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlanetMapperTest {

    private PlanetMapper planetMapper;

    @BeforeEach
    void setUp() {
        planetMapper = new PlanetMapper();
    }

    @Test
    void testToDomain() {
        // Given
        PlanetDto dto = new PlanetDto();
        dto.name = "Tatooine";
        dto.rotationPeriod = "23";
        dto.orbitalPeriod = "304";
        dto.diameter = "10465";
        dto.climate = "arid";
        dto.gravity = "1 standard";
        dto.terrain = "desert";
        dto.surfaceWater = "1";
        dto.population = "200000";
        dto.created = "2014-12-09T13:50:49.641000Z";
        dto.edited = "2014-12-20T20:58:18.411000Z";
        dto.url = "https://swapi.dev/api/planets/1/";

        // When
        Planet result = planetMapper.toDomain(dto);

        // Then
        assertNotNull(result);
        assertEquals("Tatooine", result.getName());
        assertEquals("23", result.getRotationPeriod());
        assertEquals("304", result.getOrbitalPeriod());
        assertEquals("10465", result.getDiameter());
        assertEquals("arid", result.getClimate());
        assertEquals("1 standard", result.getGravity());
        assertEquals("desert", result.getTerrain());
        assertEquals("1", result.getSurfaceWater());
        assertEquals("200000", result.getPopulation());
        assertEquals("https://swapi.dev/api/planets/1/", result.getUrl());
        assertNotNull(result.getCreated());
        assertNotNull(result.getEdited());
    }

    @Test
    void TestMapToPlanetPageResponse() {
        // When
        PageResponse<Planet> result = planetMapper.mapToPlanetPageResponse(null, 2);

        // Then
        assertNotNull(result);
        assertEquals(0, result.getCount());
        assertTrue(result.getResults().isEmpty());
        assertNull(result.getNext());
        assertNull(result.getPrevious());
    }

    @Test
    void testMapShouldMapResponseToDomainObjects() {
        // Given
        PlanetDto tatooine = new PlanetDto();
        tatooine.name = "Tatooine";
        tatooine.created = "2014-12-09T13:50:49.641000Z";
        tatooine.edited = "2014-12-20T20:58:18.411000Z";
        tatooine.url = "https://swapi.dev/api/planets/1/";

        PlanetDto alderaan = new PlanetDto();
        alderaan.name = "Alderaan";
        alderaan.created = "2014-12-09T13:50:49.641000Z";
        alderaan.edited = "2014-12-20T20:58:18.411000Z";
        alderaan.url = "https://swapi.dev/api/planets/2/";

        SwapiResponse<PlanetDto> response = new SwapiResponse<>();
        response.count = 2;
        response.next = "next-url";
        response.previous = "prev-url";
        response.results = List.of(tatooine, alderaan);

        // When
        PageResponse<Planet> result = planetMapper.mapToPlanetPageResponse(response, 1);

        // Then
        assertNotNull(result);
        assertEquals(2, result.getCount());
        assertEquals("next-url", result.getNext());
        assertEquals("prev-url", result.getPrevious());
        assertEquals(2, result.getResults().size());

        Planet first = result.getResults().get(0);
        assertEquals("Tatooine", first.getName());
        assertEquals("https://swapi.dev/api/planets/1/", first.getUrl());

        Planet second = result.getResults().get(1);
        assertEquals("Alderaan", second.getName());
        assertEquals("https://swapi.dev/api/planets/2/", second.getUrl());
    }

    @Test
    void testMapShouldHandleEmptyResults() {
        // Given
        SwapiResponse<PlanetDto> response = new SwapiResponse<>();
        response.count = 0;
        response.results = List.of();
        response.next = null;
        response.previous = null;

        // When
        PageResponse<Planet> result = planetMapper.mapToPlanetPageResponse(response, 5);

        // Then
        assertNotNull(result);
        assertEquals(0, result.getCount());
        assertTrue(result.getResults().isEmpty());
    }
}
