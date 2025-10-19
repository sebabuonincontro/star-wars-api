package com.starwars.swapi.domain;

import com.starwars.swapi.domain.model.People;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for creating People test data.
 */
public class PeopleHelper {

    // Helper methods
    public static List<People> createTestPeople(int count) {
        List<People> people = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            people.add(createPeople("Person " + i));
        }
        return people;
    }

    public static People createPeople(String name) {
        return new People(name, "180", "80", "brown", "fair", "blue",
                "20BBY", "male", LocalDateTime.now(), LocalDateTime.now(),
                "https://swapi.dev/api/people/1/");
    }
}
