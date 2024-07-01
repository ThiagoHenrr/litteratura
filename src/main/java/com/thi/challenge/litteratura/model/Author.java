package com.thi.challenge.litteratura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String birthYear;
    private String deathYear;
    @OneToMany(mappedBy = "authors", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();

    public Author(){}

    public Author(BookData bookData){{
        this.name = bookData.authors().getFirst().name();
        this.birthYear = bookData.authors().getFirst().birthYear();
        this.deathYear = bookData.authors().getFirst().deathYear();
    }

    }
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public String getDeathYear() {
        return deathYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return  "Name: '" + getName() + '\'' + "\n" +
                "Birth year: '" + getBirthYear() + '\'' + "\n" +
                "Death year: '" + getDeathYear() + '\'' + "\n" +
                "Books: " + getBooks().stream().map(Book::getTitle).collect(Collectors.toList());
    }
}
