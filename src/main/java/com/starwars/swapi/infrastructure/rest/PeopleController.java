package com.starwars.swapi.infrastructure.rest;

import com.starwars.swapi.application.services.PeopleService;
import com.starwars.swapi.domain.model.PageResponse;
import com.starwars.swapi.domain.model.People;
import com.starwars.swapi.infrastructure.rest.dtos.PeopleResponseDto;
import com.starwars.swapi.infrastructure.rest.mappers.PeopleDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * REST controller for managing People entities.
 */
@RestController
@RequestMapping("/api/v1/people")
public class PeopleController implements Pageable<PeopleResponseDto> {

    private static final int ITEMS_PER_PAGE = 15;

    private final PeopleService peopleService;
    private final PeopleDtoMapper peopleDtoMapper;

    public PeopleController(PeopleService peopleService, PeopleDtoMapper peopleDtoMapper) {
        this.peopleService = peopleService;
        this.peopleDtoMapper = peopleDtoMapper;
    }

    /**
     * Endpoint to retrieve a paginated list of People.
     *
     * @param page   the page number to retrieve (default is 1)
     * @param search optional search term to filter people by name
     * @param sortBy optional field to sort by (e.g., "name", "height")
     * @param order  sort order, either "asc" or "desc" (default is "asc")
     * @return a paginated response containing PeopleResponseDto objects
     * @throws ExecutionException   if an error occurs during data fetching
     * @throws InterruptedException if the operation is interrupted
     */
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<PeopleResponseDto>> getPeople(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "sortBy", required = false) String sortBy,
            @RequestParam(name = "order", defaultValue = "asc") String order) throws ExecutionException, InterruptedException {

        List<People> response = peopleService.getPeople(search, sortBy, order);
        List<PeopleResponseDto> dtoList = response.stream().map(peopleDtoMapper::toDto).toList();
        return ResponseEntity.ok(paginateResults(dtoList, page, ITEMS_PER_PAGE));
    }
}
