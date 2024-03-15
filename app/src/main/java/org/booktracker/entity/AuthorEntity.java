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
//    @Column(name = "authorid")
    private Integer authorId;
    private String name;

    @ManyToMany(mappedBy = "authors")
    private final Set<BookEntity> books = new HashSet<>();

    public void addBook(BookEntity book){
        books.add(book);
    }

    public void setBooks(Set<BookEntity> books){
        for (BookEntity book : books){
            addBook(book);
        }
    }
}
