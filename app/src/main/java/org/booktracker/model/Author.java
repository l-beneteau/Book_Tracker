package org.booktracker.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class Author {
    private Integer authorId;
    private String name;

    private final Set<Book> books = new HashSet<>();

}
