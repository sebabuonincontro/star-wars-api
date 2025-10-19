package com.starwars.swapi.application.services;

import com.starwars.swapi.domain.model.People;
import com.starwars.swapi.domain.port.output.SwapiPort;
import com.starwars.swapi.domain.sorting.SortStrategy;
import com.starwars.swapi.domain.sorting.SortStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Service class for managing People entities.
 */
@Service
@Slf4j
public class PeopleService implements Search<People> {

    private final SwapiPort swapiPort;
    private final SortStrategyFactory sortStrategyFactory;

    public PeopleService(SwapiPort swapiPort, SortStrategyFactory sortStrategyFactory) {
        this.swapiPort = swapiPort;
        this.sortStrategyFactory = sortStrategyFactory;
    }

    /**
     * Retrieves a list of People entities, with optional search and sorting.
     *
     * @param search the search term to filter people by name
     * @param sortBy the field to sort by
     * @param order  the order of sorting (asc or desc)
     * @return a list of People entities
     */
    public List<People> getPeople(String search, String sortBy, String order) {
        // Fetch all pages if we need to sort or filter
        log.info("Fetching people with search='{}', sortBy='{}', order='{}'", search, sortBy, order);

        List<People> allPeople = fetchAll(search, swapiPort::getPeople);

        // Apply sorting if specified
        if (sortBy != null && !sortBy.isEmpty()) {
            log.info("Sorting people by '{}' in '{}' order", sortBy, order);
            SortStrategy<People> sortStrategy = sortStrategyFactory.getPersonSortStrategy(sortBy, order);
            sortStrategy.sort(allPeople);
        }

        log.info("Total people fetched: {}", allPeople.size());
        return allPeople;
    }

}
