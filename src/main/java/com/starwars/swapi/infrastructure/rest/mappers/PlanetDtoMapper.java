package com.starwars.swapi.infrastructure.rest.mappers;

import com.starwars.swapi.domain.model.Planet;
import com.starwars.swapi.infrastructure.rest.dtos.PlanetResponseDto;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting Planet entities to PlanetResponseDto.
 */
@Component
public class PlanetDtoMapper {

    public PlanetResponseDto toDto(Planet planet) {
        if (planet == null) {
            return null;
        }

        return PlanetResponseDto.builder()
                .name(planet.getName())
                .rotationPeriod(planet.getRotationPeriod())
                .orbitalPeriod(planet.getOrbitalPeriod())
                .diameter(planet.getDiameter())
                .climate(planet.getClimate())
                .gravity(planet.getGravity())
                .terrain(planet.getTerrain())
                .surfaceWater(planet.getSurfaceWater())
                .population(planet.getPopulation())
                .created(planet.getCreated())
                .edited(planet.getEdited())
                .url(planet.getUrl())
                .build();
    }

}
