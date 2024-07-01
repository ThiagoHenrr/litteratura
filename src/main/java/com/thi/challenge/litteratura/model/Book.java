package com.thi.challenge.litteratura.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private List<String> subject;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author authors;
    private List<String> languages;
    private String downloadCount;

    public Book(){}
    public Book(BookData bookData){
        this.title = bookData.title();
        this.subject = bookData.subject();
        this.authors = new Author(bookData);
        this.languages = bookData.languages();
        this.downloadCount = bookData.downloadCount();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getSubject() {
        return subject.stream().toList();
    }

    public Author getAuthors() {
        return authors;
    }

    public void setAuthors(Author authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(String downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString(){
        return  "Title: " + getTitle() +  "\n" +
                "Authors: " + getAuthors().getName() + "\n" +
                "Languages: " + getLanguages() + "\n" +
                "Downloads: " + getDownloadCount();
    }

}
