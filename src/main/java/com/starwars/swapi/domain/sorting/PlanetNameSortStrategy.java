package com.starwars.swapi.domain.sorting;

import com.starwars.swapi.domain.model.Planet;
import java.util.Comparator;
import java.util.List;

/**
 * Concrete strategies for Planet sorting
 */
class PlanetNameSortStrategy implements SortStrategy<Planet> {
    private final boolean ascending;

    public PlanetNameSortStrategy(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public void sort(List<Planet> items) {
        Comparator<Planet> comparator = Comparator.comparing(Planet::getName,
                String.CASE_INSENSITIVE_ORDER);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        items.sort(comparator);
    }
}
