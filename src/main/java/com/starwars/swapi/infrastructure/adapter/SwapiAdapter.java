package com.starwars.swapi.infrastructure.adapter;

import com.starwars.swapi.domain.model.PageResponse;
import com.starwars.swapi.domain.model.People;
import com.starwars.swapi.domain.model.Planet;
import com.starwars.swapi.domain.port.output.SwapiPort;
import com.starwars.swapi.infrastructure.adapter.dtos.PeopleDto;
import com.starwars.swapi.infrastructure.adapter.dtos.PersonPageResponse;
import com.starwars.swapi.infrastructure.adapter.dtos.PlanetDto;
import com.starwars.swapi.infrastructure.adapter.dtos.PlanetPageResponse;
import com.starwars.swapi.infrastructure.adapter.dtos.SwapiResponse;
import com.starwars.swapi.infrastructure.adapter.mappers.PeopleMapper;
import com.starwars.swapi.infrastructure.adapter.mappers.PlanetMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Adapter class to interact with the SWAPI (Star Wars API) for fetching people and planet data.
 */
@Component
@Slf4j
public class SwapiAdapter implements SwapiPort {

    private final String baseUrl;

    private final RestTemplate restTemplate;
    private final PeopleMapper peopleMapper;
    private final PlanetMapper planetMapper;


    public SwapiAdapter(RestTemplate restTemplate,
                        @Value("${swapi.base-url:https://swapi.dev/api}") String baseUrl,
                        PeopleMapper peopleMapper, PlanetMapper planetMapper) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.peopleMapper = peopleMapper;
        this.planetMapper = planetMapper;
    }

    @Override
    public PageResponse<People> getPeople(int page, String search) {
        String url = buildUrl("/people/", page, search);
        log.info("Fetching people from URL: {}", url);

        SwapiResponse<PeopleDto> response = restTemplate.getForObject(url,
                PersonPageResponse.class);
        log.debug("Received people response: {}", response);

        return peopleMapper.mapToPeoplePageResponse(response, page);
    }

    @Override
    public PageResponse<Planet> getPlanets(int page, String search) {
        String url = buildUrl("/planets/", page, search);
        log.info("Fetching planets from URL: {}", url);

        SwapiResponse<PlanetDto> response = restTemplate.getForObject(url,
                PlanetPageResponse.class);
        log.debug("Received planets response: {}", response);

        return planetMapper.mapToPlanetPageResponse(response, page);
    }

    private String buildUrl(String endpoint, int page, String search) {
        StringBuilder url = new StringBuilder(baseUrl + endpoint + "?page=" + page);
        if (search != null && !search.isEmpty()) {
            url.append("&search=").append(search);
        }

        log.debug("Built URL: {}", url);
        return url.toString();
    }

}