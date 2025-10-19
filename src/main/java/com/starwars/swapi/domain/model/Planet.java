package com.starwars.swapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Represents a Planet entity from the Star Wars universe.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Planet implements Searchable {

    private String name;
    private String rotationPeriod;
    private String orbitalPeriod;
    private String diameter;
    private String climate;
    private String gravity;
    private String terrain;
    private String surfaceWater;
    private String population;
    private LocalDateTime created;
    private LocalDateTime edited;
    private String url;

}
