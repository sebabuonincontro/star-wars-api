package com.starwars.swapi.domain.sorting;


import com.starwars.swapi.domain.model.People;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class SortStrategyFactoryTest {

    private SortStrategyFactory sortStrategyFactory = new SortStrategyFactory();

    @Test
    void testGetPersonSortStrategy_NameAsc() {
        var strategy = sortStrategyFactory.getPersonSortStrategy("name", "asc");
        assertThat(strategy).isInstanceOf(PersonNameSortStrategy.class);
    }

    @Test
    void testGetPersonSortStrategy_CreatedDesc() {
        SortStrategy<People> strategy = sortStrategyFactory.getPersonSortStrategy("created", "desc");
        assertThat(strategy).isInstanceOf(PersonCreatedSortStrategy.class);
    }

    @Test
    void testGetPlanetSortStrategy_NameAsc() {
        var strategy = sortStrategyFactory.getPlanetSortStrategy("name", "asc");
        assertThat(strategy).isInstanceOf(PlanetNameSortStrategy.class);
    }

    @Test
    void testGetPlanetSortStrategy_CreatedDesc() {
        var strategy = sortStrategyFactory.getPlanetSortStrategy("created", "desc");
        assertThat(strategy).isInstanceOf(PlanetCreatedSortStrategy.class);
    }
}