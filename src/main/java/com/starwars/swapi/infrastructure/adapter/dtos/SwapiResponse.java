package com.starwars.swapi.infrastructure.adapter.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SwapiResponse<T> {
    public int count;
    public String next;
    public String previous;
    public List<T> results;
}

