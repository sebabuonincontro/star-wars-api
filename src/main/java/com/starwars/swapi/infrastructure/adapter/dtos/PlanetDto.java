package com.starwars.swapi.infrastructure.adapter.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object (DTO) representing a Planet.
 */
public class PlanetDto {

    public String name;
    @JsonProperty("rotation_period")
    public String rotationPeriod;
    @JsonProperty("orbital_period")
    public String orbitalPeriod;
    public String diameter;
    public String climate;
    public String gravity;
    public String terrain;
    @JsonProperty("surface_water")
    public String surfaceWater;
    public String population;
    public String created;
    public String edited;
    public String url;

}