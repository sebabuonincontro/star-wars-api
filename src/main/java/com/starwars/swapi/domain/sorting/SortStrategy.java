package com.starwars.swapi.domain.sorting;

import java.util.List;

/**
 * Strategy interface for sorting algorithms.
 *
 * @param <T> the type of items to be sorted
 */
public interface SortStrategy<T> {

    void sort(List<T> items);

}