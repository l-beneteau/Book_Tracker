package org.booktracker.service;

import org.booktracker.entity.BookEntity;
import org.booktracker.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public BookEntity findBookById(int id) {
        return bookRepository.findById(id);
    }
}
