package org.booktracker.entity;


import org.booktracker.model.Genre;
import org.booktracker.model.Rating;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "book", schema = "public")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    private String title;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "book_author",
            joinColumns = { @JoinColumn(name = "bookId") },
            inverseJoinColumns = { @JoinColumn(name = "authorId") }
    )
    Set<org.booktracker.entity.AuthorEntity> authors = new HashSet<>();
    private String series;
    private int year;
    @Convert(converter = org.booktracker.entity.GenreConverter.class)
    private Genre genre;
    private int pages;
    private boolean read;
    @Convert(converter = org.booktracker.entity.RatingConverter.class)
    private Rating rating;
    private String notes;
}
