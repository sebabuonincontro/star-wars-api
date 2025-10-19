package com.starwars.swapi.infrastructure.adapter.mappers;

import com.starwars.swapi.domain.model.PageResponse;
import com.starwars.swapi.domain.model.Planet;
import com.starwars.swapi.infrastructure.adapter.dtos.PlanetDto;
import com.starwars.swapi.infrastructure.adapter.dtos.SwapiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.starwars.swapi.infrastructure.adapter.LocalDateUtils.parseDateTime;

/**
 * Mapper class for converting PlanetDto objects to Planet domain models
 * and mapping paginated SWAPI responses to PageResponse objects.
 */
@Component
@Slf4j
public class PlanetMapper {

    public Planet toDomain(PlanetDto dto) {
        return new Planet(
                dto.name,
                dto.rotationPeriod,
                dto.orbitalPeriod,
                dto.diameter,
                dto.climate,
                dto.gravity,
                dto.terrain,
                dto.surfaceWater,
                dto.population,
                parseDateTime(dto.created),
                parseDateTime(dto.edited),
                dto.url
        );
    }

    public PageResponse<Planet> mapToPlanetPageResponse(SwapiResponse<PlanetDto> response, int page) {
        if (response == null) {
            return new PageResponse<>(0, null, null, List.of(), page, 0, 0);
        }

        List<Planet> planets = response.results.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
        log.debug("Mapped {} PlanetDto to Planet domain models", planets.size());

        return new PageResponse<>(
                response.count,
                response.next,
                response.previous,
                planets,
                page,
                0,
                planets.size()
        );
    }
}
