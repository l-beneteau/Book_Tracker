package org.booktracker.entity;


import org.booktracker.back.Rating;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.*;

@Entity
@Getter@Setter
@AllArgsConstructor@RequiredArgsConstructor
@Table(name = "Book")
public class BookEntity {
    @NonNull private int id;
    @NonNull private String title;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Book_Author",
            joinColumns = { @JoinColumn(name = "Book_Id") },
            inverseJoinColumns = { @JoinColumn(name = "Author_Id") }
    )
    @NonNull Set<org.booktracker.entity.AuthorEntity> authors = new HashSet<>();
    private String series;
    private int year;
    private String genre;
    private boolean french;
    private boolean library;
    private int pages;
    private boolean readed;
    private Date started;
    private Date ended;
    @Convert(converter = org.booktracker.entity.RatingConverter.class)
    private Rating rating;
    private String notes;


}
