package org.booktracker.back;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@RequiredArgsConstructor
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


}
