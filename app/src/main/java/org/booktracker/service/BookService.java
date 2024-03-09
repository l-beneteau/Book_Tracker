package org.booktracker.service;

import org.booktracker.back.Author;
import org.booktracker.back.Book;
import org.booktracker.entity.AuthorEntity;
import org.booktracker.entity.BookEntity;
import org.booktracker.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book findBookById(int id) {
        BookEntity bookEntity = bookRepository.findById(id);
        return new Book(bookEntity.getBook_id(), bookEntity.getTitle(), getAuthorsFromBookEntity(bookEntity));
    }

    public List<Book> findAllBooks(){
       List<Book> books = new ArrayList<>();
       Iterable<BookEntity> bookEntities = bookRepository.findAll();
       for (BookEntity bookEntity : bookEntities){
           books.add(new Book(bookEntity.getBook_id(), bookEntity.getTitle(), getAuthorsFromBookEntity(bookEntity)));
       }
       return books;
    }

    private Set<Author> getAuthorsFromBookEntity(BookEntity bookEntity){
        Set<Author> authors = new HashSet<>();
        for (AuthorEntity author : bookEntity.getAuthors())
            authors.add(new Author(author.getAuthorId(), author.getName()));
        return authors;
    }


    public BookEntity saveBook(@RequestBody Book newBook) {
        BookEntity bookEntity = new BookEntity(newBook.getTitle(), getAuthorEntitiesFromBook(newBook));
        return bookRepository.save(bookEntity);
    }

    private Set<AuthorEntity> getAuthorEntitiesFromBook(Book book){
        Set<AuthorEntity> authorEntities = new HashSet<>();
        for(Author author : book.getAuthors())
            authorEntities.add(new AuthorEntity(author.getName()));
        return authorEntities;
    }
}
