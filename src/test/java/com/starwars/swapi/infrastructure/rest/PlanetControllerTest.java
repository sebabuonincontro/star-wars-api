package com.starwars.swapi.infrastructure.rest;

import com.starwars.swapi.application.services.PlanetService;
import com.starwars.swapi.domain.model.Planet;
import com.starwars.swapi.infrastructure.rest.mappers.PlanetDtoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.starwars.swapi.domain.PlanetHelper.createPlanet;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlanetController.class)
@Import(PlanetDtoMapper.class)
public class PlanetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlanetService planetService;

    @Test
    void shouldSearchPlanetsByName() throws Exception {
        // Given
        Planet planet = createPlanet("Tatooine");

        when(planetService.getPlanets(any(), any(), any())).thenReturn(List.of(planet));

        // When & Then
        mockMvc.perform(get("/api/v1/planet")
                        .param("page", "1")
                        .param("search", "tatooine"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(1))
                .andExpect(jsonPath("$.results[0].name").value("Tatooine"));
    }

    @Test
    void shouldSortPlanetsByName() throws Exception {
        // Given
        Planet planet1 = createPlanet("Alderaan");
        Planet planet2 = createPlanet("Tatooine");
        when(planetService.getPlanets(any(), any(), any())).thenReturn(List.of(planet1, planet2));

        // When & Then
        mockMvc.perform(get("/api/v1/planet")
                        .param("page", "1")
                        .param("sortBy", "name")
                        .param("order", "asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results[0].name").value("Alderaan"))
                .andExpect(jsonPath("$.results[1].name").value("Tatooine"));
    }

    @Test
    void shouldSearchPlanetsByNameDesc() throws Exception {
        // Given
        Planet planet1 = createPlanet("Tatooine");
        Planet planet2 = createPlanet("Alderaan");
        when(planetService.getPlanets(any(), any(), any())).thenReturn(List.of(planet1, planet2));

        // When & Then
        mockMvc.perform(get("/api/v1/planet")
                        .param("page", "1")
                        .param("sortBy", "name")
                        .param("order", "desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results[0].name").value("Tatooine"))
                .andExpect(jsonPath("$.results[1].name").value("Alderaan"));
    }
}
