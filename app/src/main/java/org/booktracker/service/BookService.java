package org.booktracker.service;

import org.booktracker.back.Book;
import org.booktracker.entity.BookEntity;
import org.booktracker.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book findBookById(int id) {
        BookEntity bookEntity = bookRepository.findById(id);
        //return bookRepository.findById(id);
        return new Book(bookEntity.getBook_id(), bookEntity.getTitle(), bookEntity.getAuthors());
    }
}
