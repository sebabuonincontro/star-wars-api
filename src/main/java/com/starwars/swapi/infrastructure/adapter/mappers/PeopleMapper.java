package com.starwars.swapi.infrastructure.adapter.mappers;

import com.starwars.swapi.domain.model.PageResponse;
import com.starwars.swapi.domain.model.People;
import com.starwars.swapi.infrastructure.adapter.dtos.PeopleDto;
import com.starwars.swapi.infrastructure.adapter.dtos.SwapiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.starwars.swapi.infrastructure.adapter.LocalDateUtils.parseDateTime;

/**
 * Mapper to convert PeopleDto to People domain model
 */
@Component
@Slf4j
public class PeopleMapper {

    public People toDomain(PeopleDto dto) {
        return new People(
                dto.name,
                dto.height,
                dto.mass,
                dto.hairColor,
                dto.skinColor,
                dto.eyeColor,
                dto.birthYear,
                dto.gender,
                parseDateTime(dto.created),
                parseDateTime(dto.edited),
                dto.url
        );
    }

    public PageResponse<People> mapToPeoplePageResponse(SwapiResponse<PeopleDto> response, int page) {
        if (response == null) {
            return new PageResponse<>(0, null, null, List.of(), page, 0, 0);
        }

        List<People> people = response.results.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
        log.debug("Mapped {} PeopleDto to People domain models", people.size());

        return new PageResponse<>(
                response.count,
                response.next,
                response.previous,
                people,
                page,
                0,
                people.size()
        );
    }
}
