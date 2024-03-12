package org.booktracker.model;

import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class Book {
    private Integer bookId;
    private String title;
    Set<Author> authors = new HashSet<>();
    private String series;
    private int year;
    private String genre;
    private int pages;
    private boolean read;
    private Rating rating;
    private String notes;

}
