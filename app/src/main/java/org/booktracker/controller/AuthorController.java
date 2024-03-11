package org.booktracker.controller;

import org.booktracker.exception.AuthorNotFoundException;
import org.booktracker.exception.BookNotFoundException;
import org.booktracker.response.AuthorResponse;
import org.booktracker.response.BookResponse;
import org.booktracker.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @GetMapping(value = "/{id}", produces = "application/json")
    public AuthorResponse getBookById(@PathVariable int id) {
        try {
            return AuthorResponse.from(authorService.findAuthorById(id));
        } catch (AuthorNotFoundException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
