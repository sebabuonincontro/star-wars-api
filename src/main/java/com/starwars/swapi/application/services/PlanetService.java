package com.starwars.swapi.application.services;

import com.starwars.swapi.domain.model.Planet;
import com.starwars.swapi.domain.port.output.SwapiPort;
import com.starwars.swapi.domain.sorting.SortStrategy;
import com.starwars.swapi.domain.sorting.SortStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing Planet entities.
 */
@Service
@Slf4j
public class PlanetService implements Search<Planet> {

    private final SwapiPort swapiPort;
    private final SortStrategyFactory sortStrategyFactory;

    public PlanetService(SwapiPort swapiPort, SortStrategyFactory sortStrategyFactory) {
        this.swapiPort = swapiPort;
        this.sortStrategyFactory = sortStrategyFactory;
    }

    /**
     * Retrieves a list of Planet entities, with optional search and sorting.
     *
     * @param search the search term to filter planets by name
     * @param sortBy the field to sort by
     * @param order  the order of sorting (asc or desc)
     * @return a list of Planet entities
     */
    public List<Planet> getPlanets(String search, String sortBy, String order) {
        // Fetch all pages if we need to sort or filter
        log.info("Fetching planets with search='{}', sortBy='{}', order='{}'", search, sortBy, order);
        List<Planet> allPlanets = fetchAll(search, swapiPort::getPlanets);

        // Apply sorting if specified
        if (sortBy != null && !sortBy.isEmpty()) {
            log.info("Sorting planets by '{}' in '{}' order", sortBy, order);
            SortStrategy<Planet> sortStrategy = sortStrategyFactory.getPlanetSortStrategy(sortBy, order);
            sortStrategy.sort(allPlanets);
        }

        log.info("Total planets fetched: {}", allPlanets.size());
        return allPlanets;
    }

}
