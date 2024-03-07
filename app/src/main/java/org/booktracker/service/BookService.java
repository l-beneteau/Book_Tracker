package org.booktracker.service;

import org.booktracker.back.Author;
import org.booktracker.back.Book;
import org.booktracker.entity.AuthorEntity;
import org.booktracker.entity.BookEntity;
import org.booktracker.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book findBookById(int id) {
        BookEntity bookEntity = bookRepository.findById(id);
        Set<Author> authors = new HashSet<>();
        for (AuthorEntity author : bookEntity.getAuthors())
            authors.add(new Author(author.getAuthor_id(), author.getName()));
        return new Book(bookEntity.getBook_id(), bookEntity.getTitle(), authors);
    }
}
