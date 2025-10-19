package com.starwars.swapi.application.services;

import com.starwars.swapi.domain.model.PageResponse;
import com.starwars.swapi.domain.model.People;
import com.starwars.swapi.domain.port.output.SwapiPort;
import com.starwars.swapi.domain.sorting.SortStrategyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.starwars.swapi.domain.PeopleHelper.createPeople;
import static com.starwars.swapi.domain.PeopleHelper.createTestPeople;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PeopleServiceTest {

    @Mock
    private SwapiPort swapiPort;

    private PeopleService peopleService;

    @BeforeEach
    void setUp() {
        SortStrategyFactory sortStrategyFactory = new SortStrategyFactory();
        peopleService = new PeopleService(swapiPort, sortStrategyFactory);
    }

    @Test
    void shouldGetPeopleWithPagination() throws ExecutionException, InterruptedException {
        // Given
        List<People> people = createTestPeople(20);
        PageResponse<People> mockResponse = new PageResponse<>();
        mockResponse.setResults(people);
        mockResponse.setCount(20);
        mockResponse.setNext(null);

        when(swapiPort.getPeople(anyInt(), any())).thenReturn(mockResponse);

        // When
        List<People> result = peopleService.getPeople(null, null, "asc");

        // Then
        assertThat(result).hasSize(20); // First page with 15 items
        assertThat(result).containsExactlyElementsOf(mockResponse.getResults());
    }

    @Test
    void shouldFilterPeopleBySearchTerm() throws ExecutionException, InterruptedException {
        // Given
        List<People> people = List.of(
                createPeople("Luke Skywalker"),
                createPeople("Darth Vader"),
                createPeople("Anakin Skywalker")
        );
        PageResponse<People> mockResponse = new PageResponse<>();
        mockResponse.setResults(people);
        mockResponse.setCount(3);
        mockResponse.setNext(null);

        when(swapiPort.getPeople(anyInt(), eq("sky"))).thenReturn(mockResponse);

        // When
        List<People> result = peopleService.getPeople("sky", null, "asc");

        // Then
        assertThat(result).hasSize(2);
        assertThat(result)
                .extracting(People::getName)
                .containsExactlyInAnyOrder("Luke Skywalker", "Anakin Skywalker");
    }

    @Test
    void shouldSortPeopleByNameAscending() throws ExecutionException, InterruptedException {
        // Given
        List<People> people = List.of(
                createPeople("Yoda"),
                createPeople("Luke Skywalker"),
                createPeople("Anakin Skywalker")
        );
        PageResponse<People> mockResponse = new PageResponse<>();
        mockResponse.setResults(people);
        mockResponse.setCount(3);
        mockResponse.setNext(null);

        when(swapiPort.getPeople(anyInt(), any())).thenReturn(mockResponse);

        // When
        List<People> result = peopleService.getPeople(null, "name", "asc");

        // Then
        assertThat(result).hasSize(3);
        assertThat(result.get(0).getName()).isEqualTo("Anakin Skywalker");
        assertThat(result.get(1).getName()).isEqualTo("Luke Skywalker");
        assertThat(result.get(2).getName()).isEqualTo("Yoda");
    }

    @Test
    void shouldSortPeopleByNameDescending() throws ExecutionException, InterruptedException {
        // Given
        List<People> people = List.of(
                createPeople("Anakin Skywalker"),
                createPeople("Luke Skywalker"),
                createPeople("Yoda")
        );
        PageResponse<People> mockResponse = new PageResponse<>();
        mockResponse.setResults(people);
        mockResponse.setCount(3);
        mockResponse.setNext(null);

        when(swapiPort.getPeople(anyInt(), any())).thenReturn(mockResponse);

        // When
        List<People> result = peopleService.getPeople(null, "name", "desc");

        // Then
        assertThat(result).hasSize(3);
        assertThat(result.get(0).getName()).isEqualTo("Yoda");
        assertThat(result.get(1).getName()).isEqualTo("Luke Skywalker");
        assertThat(result.get(2).getName()).isEqualTo("Anakin Skywalker");
    }
}
