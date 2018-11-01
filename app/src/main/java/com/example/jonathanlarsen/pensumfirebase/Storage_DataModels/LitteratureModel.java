package com.example.jonathanlarsen.pensumfirebase.Storage_DataModels;

import java.io.Serializable;

public class LitteratureModel implements Serializable {

    private String title;
    private String author;
    private String period;
    private String genre;
    private String publisher;
    private int writenYear;
    private int publishedYear;
    private int pages;

    public LitteratureModel () {

    }

    public LitteratureModel
            (String title, String author, String period, String genre,
             String publisher, int writenYear, int publishedYear, int pages) {

        this.title = title;
        this.author = author;
        this.period = period;
        this.genre = genre;
        this.publisher = publisher;
        this.writenYear = writenYear;
        this.publishedYear = publishedYear;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPeriod() {
        return period;
    }

    public String getGenre() {
        return genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getWritenYear() {
        return writenYear;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public int getPages() {
        return pages;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setWritenYear(int writenYear) {
        this.writenYear = writenYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
