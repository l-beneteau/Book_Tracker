package org.booktracker.back;

import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class Book {
    private Integer book_id;
    private String title;
    Set<Author> authors = new HashSet<>();
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

}
