package com.starwars.swapi.domain.sorting;

import com.starwars.swapi.domain.model.People;
import com.starwars.swapi.domain.model.Planet;
import org.springframework.stereotype.Component;

@Component
public class SortStrategyFactory {

    public SortStrategy<People> getPersonSortStrategy(String sortBy, String order) {
        boolean ascending = "asc".equalsIgnoreCase(order);

        return switch (sortBy.toLowerCase()) {
            case "name" -> new PersonNameSortStrategy(ascending);
            case "created" -> new PersonCreatedSortStrategy(ascending);
            default -> new PersonNameSortStrategy(ascending);
        };
    }

    public SortStrategy<Planet> getPlanetSortStrategy(String sortBy, String order) {
        boolean ascending = "asc".equalsIgnoreCase(order);

        return switch (sortBy.toLowerCase()) {
            case "name"-> new PlanetNameSortStrategy(ascending);
            case "created" -> new PlanetCreatedSortStrategy(ascending);
            default -> new PlanetNameSortStrategy(ascending);
        };
    }
}
