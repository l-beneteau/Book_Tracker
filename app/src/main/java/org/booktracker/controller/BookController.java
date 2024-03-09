package org.booktracker.controller;

import org.booktracker.back.Book;
import org.booktracker.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.booktracker.service.BookService;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    BookService bookService;
    @GetMapping(value = "/{id}", produces = "application/json")
    public Book getBook(@PathVariable int id) {
        return bookService.findBookById(id);
    }

    @GetMapping(value = "/all", produces = "application/json")
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @PostMapping(value = "/add")
    BookEntity newBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }


}
