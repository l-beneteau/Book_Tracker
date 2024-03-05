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
    @NonNull private int author_id;
    @NonNull private String name;

    @ManyToMany(mappedBy = "authors")
    private final Set<org.booktracker.entity.BookEntity> books = new HashSet<>();

    public AuthorEntity(int id, String name) {
        this.author_id = id;
        this.name = name;
    }

    public AuthorEntity (){
    }
}
