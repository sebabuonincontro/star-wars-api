package com.starwars.swapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Generic class to represent paginated responses from the SWAPI.
 *
 * @param <T> the type of results contained in the page
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {

    private int count;
    private String next;
    private String previous;
    private List<T> results;
    private int currentPage;
    private int totalPages;
    private int pageSize;

}
