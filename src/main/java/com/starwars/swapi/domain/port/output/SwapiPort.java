package com.starwars.swapi.domain.port.output;

import com.starwars.swapi.domain.model.PageResponse;
import com.starwars.swapi.domain.model.People;
import com.starwars.swapi.domain.model.Planet;

public interface SwapiPort {

    /**
     * Fetches a paginated list of people from the SWAPI.
     *
     * @param page   the page number to retrieve
     * @param search an optional search term to filter results
     * @return a PageResponse containing a list of Person objects and pagination details
     */
    PageResponse<People> getPeople(int page, String search);

    /**
     * Fetches a paginated list of planets from the SWAPI.
     *
     * @param page   the page number to retrieve
     * @param search an optional search term to filter results
     * @return a PageResponse containing a list of Planet objects and pagination details
     */
    PageResponse<Planet> getPlanets(int page, String search);
}
