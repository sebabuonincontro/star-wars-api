package com.starwars.swapi.infrastructure.adapter.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing a person in the Star Wars universe.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PeopleDto {

    public String name;
    public String height;
    public String mass;
    @JsonProperty("hair_color")
    public String hairColor;
    @JsonProperty("skin_color")
    public String skinColor;
    @JsonProperty("eye_color")
    public String eyeColor;
    @JsonProperty("birth_year")
    public String birthYear;
    public String gender;
    public String created;
    public String edited;
    public String url;

}
