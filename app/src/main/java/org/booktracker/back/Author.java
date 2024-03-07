package org.booktracker.back;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

//@AllArgsConstructor
//@RequiredArgsConstructor
public class Author {
    @NonNull
    private Integer author_id;
    @NonNull private String name;

    private final Set<Book> books = new HashSet<>();

    public Author(@NonNull Integer author_id, @NonNull String name) {
        this.author_id = author_id;
        this.name = name;
    }

    public Author(@NonNull Integer author_id, @NonNull String name, Set<Book> books) {
        this.author_id = author_id;
        this.name = name;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }
}
