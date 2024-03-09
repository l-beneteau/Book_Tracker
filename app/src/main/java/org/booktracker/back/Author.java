package org.booktracker.back;

import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;

//@AllArgsConstructor
//@RequiredArgsConstructor
public class Author {
    private Integer authorId;
    @NonNull private String name;

    private final Set<Book> books = new HashSet<>();

    public Author(@NonNull Integer authorId, @NonNull String name) {
        this.authorId = authorId;
        this.name = name;
    }

    public Author(@NonNull Integer authorId, @NonNull String name, Set<Book> books) {
        this.authorId = authorId;
        this.name = name;
        this.books.addAll(books);
    }

    public Author(@NonNull String name) {
        this.name = name;
    }

    public Author() {

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

    public Set<Book> getBooks() {
        return books;
    }
}
