package com.starwars.swapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Represents a People entity from the SWAPI.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class People implements Searchable {

    private String name;
    private String height;
    private String mass;
    private String hairColor;
    private String skinColor;
    private String eyeColor;
    private String birthYear;
    private String gender;
    private LocalDateTime created;
    private LocalDateTime edited;
    private String url;

}
