package com.starwars.swapi.infrastructure.adapter.mappers;

import com.starwars.swapi.domain.model.PageResponse;
import com.starwars.swapi.domain.model.People;
import com.starwars.swapi.infrastructure.adapter.dtos.PeopleDto;
import com.starwars.swapi.infrastructure.adapter.dtos.SwapiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PeopleMapperTest {

    private PeopleMapper peopleMapper;

    @BeforeEach
    void setUp() {
        peopleMapper = new PeopleMapper();
    }

    @Test
    void testShouldMapAllFieldsCorrectly() {
        // Given
        PeopleDto dto = new PeopleDto();
        dto.name = "Luke Skywalker";
        dto.height = "172";
        dto.mass = "77";
        dto.hairColor = "blond";
        dto.skinColor = "fair";
        dto.eyeColor = "blue";
        dto.birthYear = "19BBY";
        dto.gender = "male";
        dto.created = "2014-12-09T13:50:51.644000Z";
        dto.edited = "2014-12-20T21:17:56.891000Z";
        dto.url = "https://swapi.dev/api/people/1/";

        // When
        People result = peopleMapper.toDomain(dto);

        // Then
        assertNotNull(result);
        assertEquals("Luke Skywalker", result.getName());
        assertEquals("172", result.getHeight());
        assertEquals("77", result.getMass());
        assertEquals("blond", result.getHairColor());
        assertEquals("fair", result.getSkinColor());
        assertEquals("blue", result.getEyeColor());
        assertEquals("19BBY", result.getBirthYear());
        assertEquals("male", result.getGender());
        assertEquals("https://swapi.dev/api/people/1/", result.getUrl());
        assertNotNull(result.getCreated());
        assertNotNull(result.getEdited());
    }

    @Test
    void testShouldReturnEmptyPageResponseWhenResponseIsNull() {
        // When
        PageResponse<People> result = peopleMapper.mapToPeoplePageResponse(null, 1);

        // Then
        assertNotNull(result);
        assertEquals(0, result.getCount());
        assertTrue(result.getResults().isEmpty());
    }

    @Test
    void testMapToPeoplePageResponseShouldMapResponseToDomainObjects() {
        // Given
        PeopleDto dto1 = new PeopleDto();
        dto1.name = "Luke Skywalker";
        dto1.created = "2014-12-09T13:50:51.644000Z";
        dto1.edited = "2014-12-20T21:17:56.891000Z";
        dto1.url = "https://swapi.dev/api/people/1/";

        PeopleDto dto2 = new PeopleDto();
        dto2.name = "Leia Organa";
        dto2.created = "2014-12-09T13:50:51.644000Z";
        dto2.edited = "2014-12-20T21:17:56.891000Z";
        dto2.url = "https://swapi.dev/api/people/5/";

        SwapiResponse<PeopleDto> response = new SwapiResponse<>();
        response.count = 2;
        response.next = "next-url";
        response.previous = "prev-url";
        response.results = List.of(dto1, dto2);

        // When
        PageResponse<People> result = peopleMapper.mapToPeoplePageResponse(response, 1);

        // Then
        assertNotNull(result);
        assertEquals(2, result.getCount());
        assertEquals("next-url", result.getNext());
        assertEquals("prev-url", result.getPrevious());
        assertEquals(2, result.getResults().size());

        People first = result.getResults().get(0);
        assertEquals("Luke Skywalker", first.getName());
        assertEquals("https://swapi.dev/api/people/1/", first.getUrl());
    }

    @Test
    void mapToPeoplePageResponse_ShouldHandleEmptyResults() {
        // Given
        SwapiResponse<PeopleDto> response = new SwapiResponse<>();
        response.count = 0;
        response.next = null;
        response.previous = null;
        response.results = List.of();

        // When
        PageResponse<People> result = peopleMapper.mapToPeoplePageResponse(response, 3);

        // Then
        assertNotNull(result);
        assertEquals(0, result.getCount());
        assertTrue(result.getResults().isEmpty());
    }
}
