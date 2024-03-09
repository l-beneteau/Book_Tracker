package org.booktracker.back;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.booktracker.entity.AuthorEntity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Book {
    private Integer book_id;
    @NonNull private String title;
    @NonNull Set<Author> authors = new HashSet<>();
    private String series;
    private int year;
    private String genre;
    private boolean french;
    private boolean library;
    private int pages;
    private boolean readed;
    private Date started;
    private Date ended;
    private Rating rating;
    private String notes;

    public Book(@NonNull Integer book_id, @NonNull String title, @NonNull Set<Author> authors) {
        this.book_id = book_id;
        this.title = title;
        this.authors = authors;
    }

    public Book(@NonNull Integer book_id, @NonNull String title, @NonNull Set<Author> authors, String series, int year, String genre, boolean french, boolean library, int pages, boolean readed, Date started, Date ended, Rating rating, String notes) {
        this.book_id = book_id;
        this.title = title;
        this.authors = authors;
        this.series = series;
        this.year = year;
        this.genre = genre;
        this.french = french;
        this.library = library;
        this.pages = pages;
        this.readed = readed;
        this.started = started;
        this.ended = ended;
        this.rating = rating;
        this.notes = notes;
    }

    public Book(@NonNull String title, @NonNull Set<Author> authors) {
        this.title = title;
        this.authors = authors;
    }

    public Book() {
    }

    public Integer getBook_id() {
        return book_id;
    }

    public String getTitle() {
        return title;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public String getSeries() {
        return series;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isFrench() {
        return french;
    }

    public boolean isLibrary() {
        return library;
    }

    public int getPages() {
        return pages;
    }

    public boolean isReaded() {
        return readed;
    }

    public Date getStarted() {
        return started;
    }

    public Date getEnded() {
        return ended;
    }

    public Rating getRating() {
        return rating;
    }

    public String getNotes() {
        return notes;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setFrench(boolean french) {
        this.french = french;
    }

    public void setLibrary(boolean library) {
        this.library = library;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setReaded(boolean readed) {
        this.readed = readed;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public void setEnded(Date ended) {
        this.ended = ended;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
