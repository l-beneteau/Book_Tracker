package org.booktracker.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class Author {
    private Integer authorId;
    private String name;

    private Set<Book> books = new HashSet<>();

    public void addBook(Book book) {
        books.add(book);
    }
}
