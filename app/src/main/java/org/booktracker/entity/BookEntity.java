package org.booktracker.entity;


import org.booktracker.back.Rating;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
//@RequiredArgsConstructor
//@NoArgsConstructor
@Table(name = "book", schema = "public")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull private Integer book_id;
    @NonNull private String title;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "book_author",
            joinColumns = { @JoinColumn(name = "book_id") },
            inverseJoinColumns = { @JoinColumn(name = "author_id") }
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

    public BookEntity(String title, Set<AuthorEntity> authors) {
        this.title = title;
        this.authors = authors;
    }

    public BookEntity() {

    }

    public Integer getBook_id() {
        return book_id;
    }

    public String getTitle() {
        return title;
    }

    public Set<AuthorEntity> getAuthors() {
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
}
