package org.booktracker.entity;


import jakarta.persistence.*;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "author", schema = "public")
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Integer authorId;
    private String name;

    @ManyToMany(mappedBy = "authors")
    private final Set<BookEntity> books = new HashSet<>();
}
