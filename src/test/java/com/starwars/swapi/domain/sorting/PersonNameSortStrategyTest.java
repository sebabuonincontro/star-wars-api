package com.starwars.swapi.domain.sorting;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.starwars.swapi.domain.PeopleHelper.createPeople;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonNameSortStrategyTest {

    @Test
    void testSortAscending() {
        //given
        var people = Arrays.asList(
                createPeople("Alice"),
                createPeople("Bob"),
                createPeople("Charlie"));

        //when
        PersonNameSortStrategy personNameSortStrategy = new PersonNameSortStrategy(true);
        personNameSortStrategy.sort(people);

        //then
        assertEquals("Alice", people.get(0).getName());
        assertEquals("Bob", people.get(1).getName());
        assertEquals("Charlie", people.get(2).getName());
    }

    @Test
    void testSortDescending() {
        //given
        var people = Arrays.asList(
                createPeople("Alice"),
                createPeople("Bob"),
                createPeople("Charlie"));

        //when
        PersonNameSortStrategy personNameSortStrategy = new PersonNameSortStrategy(false);
        personNameSortStrategy.sort(people);

        //then
        assertEquals("Charlie", people.get(0).getName());
        assertEquals("Bob", people.get(1).getName());
        assertEquals("Alice", people.get(2).getName());
    }

}