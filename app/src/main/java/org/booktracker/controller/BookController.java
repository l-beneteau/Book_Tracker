package org.booktracker.controller;

import org.booktracker.exception.BookNotFoundException;
import org.booktracker.model.Book;
import org.booktracker.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.booktracker.service.BookService;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    BookService bookService;
    @GetMapping(value = "/{id}", produces = "application/json")
    public Book getBookById(@PathVariable int id) {
        try {
           return bookService.findBookById(id);
        } catch (BookNotFoundException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping()
    public Book getBookByTitle(@RequestParam String title) {
        try {
            return bookService.findBookByTitle(title);
        } catch (BookNotFoundException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }


    @GetMapping(value = "/all", produces = "application/json")
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @PostMapping(value = "/add")
    BookEntity newBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @DeleteMapping("/{id}")
    void deleteBookById(@PathVariable int id) {
        bookService.deleteBookById(id);
    }


}
