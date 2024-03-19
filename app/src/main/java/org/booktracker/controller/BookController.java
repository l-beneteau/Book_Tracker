package org.booktracker.controller;

import org.booktracker.exception.AuthorNotFoundException;
import org.booktracker.exception.BookNotFoundException;
import org.booktracker.exception.InvalidDateException;
import org.booktracker.exception.NoAuthorException;
import org.booktracker.model.Book;
import org.booktracker.parameter.BookParameter;
import org.booktracker.response.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.booktracker.service.BookService;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    BookService bookService;
    @GetMapping(value = "/{id}", produces = "application/json")
    public BookResponse getBookById(@PathVariable int id) {
        try {
           return BookResponse.from(bookService.findBookById(id));
        } catch (BookNotFoundException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping()
    public List<BookResponse> getBooks(BookParameter bookParameter) {
        List<BookResponse> bookResponses = new ArrayList<>();
        try {
            List<Book> books = bookService.findBooks(bookParameter);
            for (Book book : books){
                bookResponses.add(BookResponse.from(book));
            }
        } catch (BookNotFoundException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return bookResponses;
    }


    @PostMapping(value = "/add")
    BookResponse newBook(@RequestBody BookParameter book) {
        try{
            return BookResponse.from(bookService.saveBook(book));
        } catch (AuthorNotFoundException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (NoAuthorException | InvalidDateException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

    }



}
