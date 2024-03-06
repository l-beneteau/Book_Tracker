package org.booktracker.entity;


import jakarta.persistence.*;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
//@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "author", schema = "public")
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull private Integer author_id;
    @NonNull private String name;

    @ManyToMany(mappedBy = "authors")
    private final Set<BookEntity> books = new HashSet<>();

    public AuthorEntity(@NonNull String name) {
        this.name = name;
    }

    public AuthorEntity (){
    }

    public Set<BookEntity> getBooks() {
        return books;
    }
}
