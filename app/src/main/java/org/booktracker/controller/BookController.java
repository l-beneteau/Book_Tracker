package org.booktracker.controller;

import org.booktracker.exception.BookNotFoundException;
import org.booktracker.model.Book;
import org.booktracker.entity.BookEntity;
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


//    @GetMapping(produces = "application/json")
//    public List<BookResponse> getAllBooks() {
//        List<Book> books = bookService.findAllBooks();
//        List<BookResponse> bookResponses = new ArrayList<>();
//        for (Book book : books){
//            bookResponses.add(BookResponse.from(book));
//        }
//        return bookResponses;
//    }

    @PostMapping(value = "/add")
    BookEntity newBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @DeleteMapping("/{id}")
    void deleteBookById(@PathVariable int id) {
        bookService.deleteBookById(id);
    }


}
