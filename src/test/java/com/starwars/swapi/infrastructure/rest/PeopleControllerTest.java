package com.starwars.swapi.infrastructure.rest;

import com.starwars.swapi.application.services.PeopleService;
import com.starwars.swapi.domain.model.People;
import com.starwars.swapi.infrastructure.rest.mappers.PeopleDtoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.starwars.swapi.domain.PeopleHelper.createPeople;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PeopleController.class)
@Import(PeopleDtoMapper.class)
public class PeopleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeopleService peopleService;

    @Test
    void shouldSearchPeopleByName() throws Exception {
        // Given
        People people = createPeople("Luke Skywalker");
        People people2 = createPeople("Darth Vader");
        People people3 = createPeople("Leia Organa");

        when(peopleService.getPeople(eq("sky"), any(), any())).thenReturn(List.of(people, people3, people2));

        // When & Then
        mockMvc.perform(get("/api/v1/people")
                        .param("page", "1")
                        .param("search", "sky"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results[0].name").value("Luke Skywalker"));
    }

    @Test
    void shouldSortPeopleByName() throws Exception {
        // Given
        People people1 = createPeople("Anakin Skywalker");
        People people2 = createPeople("Luke Skywalker");
        People people3 = createPeople("Obi-Wan Kenobi");

        when(peopleService.getPeople(any(), eq("name"), eq("asc")))
                .thenReturn(List.of(people1, people2, people3));

        // When & Then
        mockMvc.perform(get("/api/v1/people")
                        .param("page", "1")
                        .param("sortBy", "name")
                        .param("order", "asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results[0].name").value("Anakin Skywalker"))
                .andExpect(jsonPath("$.results[1].name").value("Luke Skywalker"))
                .andExpect(jsonPath("$.results[2].name").value("Obi-Wan Kenobi"));
    }

    @Test
    void shouldSearchPeopleByNameDesc() throws Exception {
        // Given
        People people1 = createPeople("Luke Skywalker");
        People people2 = createPeople("Obi-Wan Kenobi");
        People people3 = createPeople("Anakin Skywalker");

        when(peopleService.getPeople(any(), eq("name"), eq("desc")))
                .thenReturn(List.of(people1, people2, people3));

        // When & Then
        mockMvc.perform(get("/api/v1/people")
                        .param("page", "1")
                        .param("sortBy", "name")
                        .param("order", "desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results[0].name").value("Luke Skywalker"))
                .andExpect(jsonPath("$.results[1].name").value("Obi-Wan Kenobi"))
                .andExpect(jsonPath("$.results[2].name").value("Anakin Skywalker"));
    }
}
