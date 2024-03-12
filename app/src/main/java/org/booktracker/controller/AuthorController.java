package org.booktracker.controller;

import org.booktracker.entity.AuthorEntity;
import org.booktracker.exception.AuthorNotFoundException;
import org.booktracker.model.Author;
import org.booktracker.parameter.AuthorParameter;
import org.booktracker.response.AuthorResponse;
import org.booktracker.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @GetMapping(value = "/{id}", produces = "application/json")
    public AuthorResponse getBookById(@PathVariable int id) {
        try {
            Author author = authorService.findAuthorById(id);
            return AuthorResponse.from(author);
        } catch (AuthorNotFoundException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping
    public List<AuthorResponse> getAuthors(AuthorParameter authorParameter) {
        List<AuthorResponse> authorResponses = new ArrayList<>();
        try{
            List<Author> authors = authorService.findAuthors(authorParameter);
            for (Author author : authors){
                authorResponses.add(AuthorResponse.from(author));
            }

        } catch (AuthorNotFoundException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }

        return authorResponses;
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
