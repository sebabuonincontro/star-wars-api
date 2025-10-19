package com.starwars.swapi.application.services;

import com.starwars.swapi.domain.model.PageResponse;
import com.starwars.swapi.domain.model.Planet;
import com.starwars.swapi.domain.port.output.SwapiPort;
import com.starwars.swapi.domain.sorting.SortStrategyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.starwars.swapi.domain.PlanetHelper.createTestPlanets;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {

    @Mock
    private SwapiPort swapiPort;

    private PlanetService planetService;

    @BeforeEach
    void setUp() {
        SortStrategyFactory sortStrategyFactory = new SortStrategyFactory();
        planetService = new PlanetService(swapiPort, sortStrategyFactory);
    }

    @Test
    public void shouldGetPlanetsWithPagination() throws ExecutionException, InterruptedException {
        // Given
        List<Planet> planets = createTestPlanets(20);
        PageResponse<Planet> mockResponse = new PageResponse<>();
        mockResponse.setResults(planets);
        mockResponse.setCount(20);
        mockResponse.setNext(null);

        when(swapiPort.getPlanets(anyInt(), any())).thenReturn(mockResponse);

        // When
        List<Planet> result = planetService.getPlanets(null, null, "asc");

        // Then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(20);
        assertThat(result.get(0).getName()).isEqualTo("Planet 1");
    }

    @Test
    public void shouldFilterPlanetsBySearchTerm() throws ExecutionException, InterruptedException {
        // Given
        List<Planet> planets = createTestPlanets(5);
        PageResponse<Planet> mockResponse = new PageResponse<>();
        mockResponse.setResults(planets);
        mockResponse.setCount(5);
        mockResponse.setNext(null);

        when(swapiPort.getPlanets(anyInt(), any())).thenReturn(mockResponse);

        // When
        List<Planet> result = planetService.getPlanets("Planet 3", null, "asc");

        // Then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void shouldSortPeopleByNameDescending() throws ExecutionException, InterruptedException {
        // Given
        List<Planet> planets = createTestPlanets(3);
        PageResponse<Planet> mockResponse = new PageResponse<>();
        mockResponse.setResults(planets);
        mockResponse.setCount(3);
        mockResponse.setNext(null);

        when(swapiPort.getPlanets(anyInt(), any())).thenReturn(mockResponse);

        // When
        List<Planet> result = planetService.getPlanets(null, "name", "desc");

        // Then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0).getName()).isEqualTo("Planet 3");
        assertThat(result.get(1).getName()).isEqualTo("Planet 2");
        assertThat(result.get(2).getName()).isEqualTo("Planet 1");
    }

    @Test
    public void shouldSortPeopleByNameAscending() throws ExecutionException, InterruptedException {
        // Given
        List<Planet> planets = createTestPlanets(3);
        PageResponse<Planet> mockResponse = new PageResponse<>();
        mockResponse.setResults(planets);
        mockResponse.setCount(3);
        mockResponse.setNext(null);

        when(swapiPort.getPlanets(anyInt(), any())).thenReturn(mockResponse);

        // When
        List<Planet> result = planetService.getPlanets(null, "name", "asc");

        // Then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0).getName()).isEqualTo("Planet 1");
        assertThat(result.get(1).getName()).isEqualTo("Planet 2");
        assertThat(result.get(2).getName()).isEqualTo("Planet 3");
    }
}
