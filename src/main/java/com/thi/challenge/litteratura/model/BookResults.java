package com.thi.challenge.litteratura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookResults(BookData[] results) {
}
