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
    @Column(name = "author_id")
    @NonNull private Integer authorId;
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

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
