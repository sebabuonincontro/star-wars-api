package com.starwars.swapi.infrastructure.rest;

import com.starwars.swapi.domain.model.PageResponse;
import com.starwars.swapi.domain.model.Planet;
import com.starwars.swapi.domain.model.Searchable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * Interface providing pagination functionality for lists of items.
 *
 * @param <T> the type of items to be paginated
 */
public interface Pageable<T> {

    /**
     * Paginates the given list of items based on the specified page number and page size.
     *
     * @param items    the list of items to paginate
     * @param page     the current page number (1-based)
     * @param pageSize the number of items per page
     * @return a PageResponse containing the paginated results and metadata
     */
    default PageResponse<T> paginateResults(List<T> items, int page, int pageSize) {
        int totalItems = items.size();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        // Validate page number
        if (page < 1) page = 1;
        if (page > totalPages && totalPages > 0) page = totalPages;

        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalItems);

        List<T> pageResults = (startIndex < totalItems)
                ? items.subList(startIndex, endIndex)
                : new ArrayList<>();

        PageResponse<T> response = new PageResponse<>();
        response.setCount(totalItems);
        response.setResults(pageResults);
        response.setCurrentPage(page);
        response.setTotalPages(totalPages);
        response.setPageSize(pageSize);
        response.setNext(page < totalPages ? String.valueOf(page + 1) : null);
        response.setPrevious(page > 1 ? String.valueOf(page - 1) : null);

        return response;
    }

}
