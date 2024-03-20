package org.booktracker.service;

import org.booktracker.exception.BookNotFoundException;
import org.booktracker.model.Book;
import org.booktracker.model.Stat;
import org.booktracker.parameter.BookParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatService {

    @Autowired
    BookService bookService;

    public Stat getStat() throws BookNotFoundException {
        Stat stat = new Stat();
        stat.setNbBook(bookService.findBooks(new BookParameter()).size());
        return stat;
    }
}
