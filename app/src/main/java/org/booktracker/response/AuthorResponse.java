package org.booktracker.response;


import lombok.Data;
import lombok.Getter;
import org.booktracker.model.Author;
import org.booktracker.model.Book;

import java.util.HashSet;
import java.util.Set;

@Data
public class AuthorResponse {
    private Integer authorId;
    private String name;
    private final Set<BookOfAuthorResponse> books = new HashSet<>();

    public static AuthorResponse from (Author author){
        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.authorId = author.getAuthorId();
        authorResponse.name = author.getName();
        for (Book book : author.getBooks()){
            authorResponse.books.add(BookOfAuthorResponse.from(book));
        }
        return authorResponse;
    }
}
