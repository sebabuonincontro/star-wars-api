package com.starwars.swapi.domain.sorting;

import com.starwars.swapi.domain.model.People;

import java.util.Comparator;
import java.util.List;

/**
 * Concrete strategies for People sorting
 */
class PersonCreatedSortStrategy implements SortStrategy<People> {
    private final boolean ascending;

    public PersonCreatedSortStrategy(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public void sort(List<People> items) {
        Comparator<People> comparator = Comparator.comparing(People::getCreated);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        items.sort(comparator);
    }
}
