package com.starwars.swapi.domain.model;

/**
 * Interface representing a searchable entity with params that could be searched.
 */
public interface Searchable {

    /**
     * Gets the name of the searchable entity.
     *
     * @return the name of the entity
     */
    String getName();

}
