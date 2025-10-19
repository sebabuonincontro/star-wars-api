package com.starwars.swapi.domain.sorting;

import com.starwars.swapi.domain.model.People;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.starwars.swapi.domain.PeopleHelper.createTestPeople;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonCreatedSortStrategyTest {

    @Test
    void testSortAscending() {
        //given
        List<People> people = createTestPeople(3);

        //when
        PersonCreatedSortStrategy personCreatedSortStrategy = new PersonCreatedSortStrategy(true);
        personCreatedSortStrategy.sort(people);

        //then
        assertTrue(people.get(0).getCreated().isBefore(people.get(1).getCreated()));
        assertTrue(people.get(1).getCreated().isBefore(people.get(2).getCreated()));

    }

    @Test
    void testSortDescending() {
        //given
        List<People> people = createTestPeople(3);

        //when
        PersonCreatedSortStrategy personCreatedSortStrategy = new PersonCreatedSortStrategy(false);
        personCreatedSortStrategy.sort(people);

        //then
        assertTrue(people.get(0).getCreated().isAfter(people.get(1).getCreated()));
        assertTrue(people.get(1).getCreated().isAfter(people.get(2).getCreated()));
    }
}