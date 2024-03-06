package org.booktracker.controller;

import org.booktracker.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.booktracker.service.BookService;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    BookService bookService;
    @GetMapping(value = "/{id}", produces = "application/json")
    public BookEntity getBook(@PathVariable int id) {
        return bookService.findBookById(id);
    }


}
