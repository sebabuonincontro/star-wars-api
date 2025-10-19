package com.starwars.swapi.domain.sorting;

import com.starwars.swapi.domain.model.Planet;

import java.util.Comparator;
import java.util.List;

/**
 * Concrete strategy for sorting Planets by creation date.
 */
public class PlanetCreatedSortStrategy implements SortStrategy<Planet> {
    private final boolean ascending;

    public PlanetCreatedSortStrategy(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public void sort(List<Planet> items) {
        Comparator<Planet> comparator = Comparator.comparing(Planet::getCreated);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        items.sort(comparator);
    }
}
