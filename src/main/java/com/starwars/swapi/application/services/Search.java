package com.starwars.swapi.application.services;

import com.starwars.swapi.domain.model.PageResponse;
import com.starwars.swapi.domain.model.Searchable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * Interface providing search functionality for searchable entities.
 *
 * @param <T> the type of searchable entities
 */
public interface Search<T extends Searchable> {

    /**
     * Fetches all items matching the search criteria using the provided SWAPI call function.
     *
     * @param search     the search string
     * @param swapiCall  a function that takes page number and search string, and returns a PageResponse of T
     * @return a list of all items matching the search criteria
     */
    default List<T> fetchAll(String search, BiFunction<Integer, String, PageResponse<T>> swapiCall) {
        List<T> results = new ArrayList<>();
        int currentPage = 1;
        PageResponse<T> response;

        do {
            // Call SWAPI to get the current page and search
            response = swapiCall.apply(currentPage, search);
            if (response.getResults() != null) {
                results.addAll(response.getResults());
            }
            currentPage++;
        } while (response.getNext() != null);

        // Additional local filtering for case-insensitive partial match
        if (search != null && !search.isEmpty()) {
            String lowerSearch = search.toLowerCase();
            results = results.stream()
                    .filter(p -> p.getName().toLowerCase().contains(lowerSearch))
                    .collect(Collectors.toList());
        }

        return results;
    }
}
