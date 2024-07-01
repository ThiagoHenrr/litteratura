package com.thi.challenge.litteratura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData(@JsonProperty("title")String title,
                       @JsonProperty("subjects") List<String> subject,
                       List<AuthorData> authors,
                       List<String> languages,
                       @JsonProperty("download_count") String downloadCount) {

    @Override
    public String toString(){
        return " --------- BOOK ---------" + "\n" +
                " Title: " + title + "\n" +
                " Author: " + authors.getFirst().name() + "\n" +
                " Languages " + languages.toString() + "\n" +
                " Downloads: " + downloadCount +"\n" +
                "------------------------";
    }
}
