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
@NoArgsConstructor
@Table(name = "book", schema = "public")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull private int book_id;
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

    public BookEntity(int id, String title, Set<AuthorEntity> authors) {
        this.book_id = id;
        this.title = title;
        this.authors = authors;
    }

    public BookEntity(){
    }

}
