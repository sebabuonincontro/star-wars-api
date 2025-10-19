package com.starwars.swapi.infrastructure.rest.mappers;

import org.junit.jupiter.api.Test;

import static com.starwars.swapi.domain.PeopleHelper.createTestPeople;
import static org.junit.jupiter.api.Assertions.*;

class PeopleDtoMapperTest {

    private final PeopleDtoMapper peopleMapper = new PeopleDtoMapper();

    @Test
    void testToDto_NullInput() {
        assertNull(peopleMapper.toDto(null));
    }

    @Test
    void testMapperSuccessful() {
        var people = createTestPeople(1).get(0);

        var dto = peopleMapper.toDto(people);

        assertNotNull(dto);
        assertEquals(people.getName(), dto.getName());
        assertEquals(people.getHeight(), dto.getHeight());
        assertEquals(people.getMass(), dto.getMass());
        assertEquals(people.getHairColor(), dto.getHairColor());
        assertEquals(people.getSkinColor(), dto.getSkinColor());
        assertEquals(people.getEyeColor(), dto.getEyeColor());
        assertEquals(people.getBirthYear(), dto.getBirthYear());
        assertEquals(people.getGender(), dto.getGender());
        assertEquals(people.getCreated(), dto.getCreated());
        assertEquals(people.getEdited(), dto.getEdited());
        assertEquals(people.getUrl(), dto.getUrl());
    }

}