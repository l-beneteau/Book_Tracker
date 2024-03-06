package org.booktracker.controller;

import org.booktracker.entity.BookEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book")
public class BookController {
    @GetMapping(value = "/{id}", produces = "application/json")
    public BookEntity getBook(@PathVariable int id) {
        return findBookById(id);
    }

    private BookEntity findBookById(int id) {
        return null;
    }
}
