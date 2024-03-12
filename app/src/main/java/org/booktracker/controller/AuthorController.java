package org.booktracker.controller;

import org.booktracker.entity.AuthorEntity;
import org.booktracker.entity.BookEntity;
import org.booktracker.exception.AuthorNotFoundException;
import org.booktracker.model.Author;
import org.booktracker.model.Book;
import org.booktracker.response.AuthorResponse;
import org.booktracker.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping()
    public AuthorResponse getAuthorByName(@RequestParam String name) {
        try {
            return AuthorResponse.from(authorService.findAuthorByName(name));
        } catch (AuthorNotFoundException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PostMapping(value = "/add")
    public AuthorEntity newAuthor(@RequestBody Author author) {
        return authorService.saveAuthor(author);
    }

    @DeleteMapping("/{id}")
    void deleteAuthorById(@PathVariable int id) {
        authorService.deleteAuthorById(id);
    }

}
