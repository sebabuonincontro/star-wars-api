package com.starwars.swapi.infrastructure.rest.mappers;

import com.starwars.swapi.domain.model.People;
import com.starwars.swapi.infrastructure.rest.dtos.PeopleResponseDto;
import org.springframework.stereotype.Component;

/**
 * Mapper class to convert People domain model to PeopleResponseDto.
 */
@Component
public class PeopleDtoMapper {

    public PeopleResponseDto toDto(People people) {
        if(people == null) {
            return null;
        }

        return PeopleResponseDto.builder()
                .name(people.getName())
                .height(people.getHeight())
                .mass(people.getMass())
                .hairColor(people.getHairColor())
                .skinColor(people.getSkinColor())
                .eyeColor(people.getEyeColor())
                .birthYear(people.getBirthYear())
                .gender(people.getGender())
                .created(people.getCreated())
                .edited(people.getEdited())
                .url(people.getUrl())
                .build();
    }
}
