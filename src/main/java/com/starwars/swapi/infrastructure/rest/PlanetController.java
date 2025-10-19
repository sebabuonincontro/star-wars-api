package com.starwars.swapi.infrastructure.rest;

import com.starwars.swapi.application.services.PlanetService;
import com.starwars.swapi.domain.model.PageResponse;
import com.starwars.swapi.domain.model.Planet;
import com.starwars.swapi.infrastructure.rest.dtos.PlanetResponseDto;
import com.starwars.swapi.infrastructure.rest.mappers.PlanetDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * REST controller for managing Planet entities.
 */
@RestController
@RequestMapping("/api/v1/planet")
public class PlanetController implements Pageable<PlanetResponseDto> {

    private static final int ITEMS_PER_PAGE = 15;

    private final PlanetService planetService;
    private final PlanetDtoMapper planetMapper;

    public PlanetController(PlanetService planetService, PlanetDtoMapper planetMapper) {
        this.planetService = planetService;
        this.planetMapper = planetMapper;
    }

    /**
     * Retrieves a paginated list of Planet entities, with optional search and sorting.
     *
     * @param page   the page number to retrieve
     * @param search the search term to filter planets by name
     * @param sortBy the field to sort by
     * @param order  the order of sorting (asc or desc)
     * @return a paginated response containing PlanetResponseDto objects
     */
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<PlanetResponseDto>> getPlanets (
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "sortBy", required = false) String sortBy,
            @RequestParam(name = "order", defaultValue = "asc") String order) {

        List<Planet> response = planetService.getPlanets(search, sortBy, order);
        List<PlanetResponseDto> dtoList = response.stream().map(planetMapper::toDto).toList();
        return ResponseEntity.ok(paginateResults(dtoList, page, ITEMS_PER_PAGE));
    }
}
