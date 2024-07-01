package com.thi.challenge.litteratura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorData(String name,
                     @JsonAlias("birth_year") String birthYear,
                     @JsonAlias("death_year") String deathYear) {
}
