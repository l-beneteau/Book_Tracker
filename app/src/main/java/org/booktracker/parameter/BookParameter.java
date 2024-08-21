package org.booktracker.parameter;

import lombok.Data;
import org.booktracker.model.Genre;
import org.booktracker.model.Rating;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class BookParameter {
    private Integer bookId;
    private String title;
    Set<Integer> authors = new HashSet<>();
    private String series;
    private int year;
    private Genre genre;
    private int pages;
    private Boolean read;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date started;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date ended;
    private Rating rating;
    private String notes;
}
