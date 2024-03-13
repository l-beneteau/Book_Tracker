package org.booktracker.response;

import lombok.Getter;
import org.booktracker.entity.AuthorEntity;
import org.booktracker.model.Author;

@Getter
public class AuthorOfBookResponse {
    private Integer authorId;
    private String name;


    public static AuthorOfBookResponse from(Author author) {
        AuthorOfBookResponse authorOfBookResponse = new AuthorOfBookResponse();
        authorOfBookResponse.authorId = author.getAuthorId();
        authorOfBookResponse.name = author.getName();
        return authorOfBookResponse;
    }

    public static AuthorOfBookResponse from(AuthorEntity author) {
        AuthorOfBookResponse authorOfBookResponse = new AuthorOfBookResponse();
        authorOfBookResponse.authorId = author.getAuthorId();
        authorOfBookResponse.name = author.getName();
        return authorOfBookResponse;
    }
}
