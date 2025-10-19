package com.starwars.swapi.domain.sorting;

import com.starwars.swapi.domain.model.People;
import java.util.Comparator;
import java.util.List;

/**
 * Concrete strategies for Person sorting
 */
public class PersonNameSortStrategy implements SortStrategy<People> {

    private final boolean ascending;

    public PersonNameSortStrategy(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public void sort(List<People> items) {
        Comparator<People> comparator = Comparator.comparing(People::getName,
                String.CASE_INSENSITIVE_ORDER);
        if (!ascending) {
            comparator = comparator.reversed();
        }
        items.sort(comparator);
    }
}