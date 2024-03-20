package org.booktracker.service;

import org.booktracker.exception.AuthorNotFoundException;
import org.booktracker.exception.BookNotFoundException;
import org.booktracker.exception.InvalidDateException;
import org.booktracker.exception.NoAuthorException;
import org.booktracker.model.Author;
import org.booktracker.model.Book;
import org.booktracker.entity.AuthorEntity;
import org.booktracker.entity.BookEntity;
import org.booktracker.parameter.BookParameter;
import org.booktracker.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorService authorService;

    public Book findBookById(int id) throws BookNotFoundException{
        BookEntity bookEntity = bookRepository.findById(id);
        if (bookEntity == null){
            throw new BookNotFoundException(id);
        }
        return getBookFromEntity(bookEntity);
    }
    public List<Book> findBooks(BookParameter bookParameter) throws BookNotFoundException {
//        List<BookEntity> bookEntities = bookRepository.find(bookParameter.getTitle(), bookParameter.getSeries(),
//                bookParameter.getYear(), bookParameter.getGenre(), bookParameter.getRead(), bookParameter.getStarted(),
//                bookParameter.getEnded(), bookParameter.getRating());
        List<BookEntity> bookEntities = bookRepository.find(bookParameter.getTitle(), bookParameter.getSeries(),
                bookParameter.getYear(), bookParameter.getGenre(), bookParameter.getRead(), bookParameter.getRating());
        if (bookEntities.isEmpty()){
            throw new BookNotFoundException(bookParameter);
        }
        List<Book> books = new ArrayList<>();
        for(BookEntity bookEntity : bookEntities){
            books.add(getBookFromEntity(bookEntity));
        }
        return books;
    }

    private Book getBookFromEntity(BookEntity bookEntity) {
        Book book = new Book();
        book.setBookId(bookEntity.getBookId());
        book.setTitle(bookEntity.getTitle());
        book.setAuthors(getAuthorsFromBookEntity(bookEntity));
        book.setSeries(bookEntity.getSeries());
        book.setYear(bookEntity.getYear());
        book.setGenre(bookEntity.getGenre());
        book.setPages(bookEntity.getPages());
        book.setRead(bookEntity.isRead());
        book.setStarted(bookEntity.getStarted());
        book.setEnded(bookEntity.getEnded());
        book.setRating(bookEntity.getRating());
        book.setNotes(bookEntity.getNotes());
        return book;
    }

    private Set<Author> getAuthorsFromBookEntity(BookEntity bookEntity){
        Set<Author> authors = new HashSet<>();
        for (AuthorEntity authorEntity : bookEntity.getAuthors()) {
            Author author = new Author();
            author.setAuthorId(authorEntity.getAuthorId());
            author.setName(authorEntity.getName());
            authors.add(author);
        }
        return authors;
    }


    public BookEntity saveBook(BookParameter bookParameter) throws AuthorNotFoundException, NoAuthorException, InvalidDateException {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(bookParameter.getTitle());
        if(getAuthorEntitiesFromBook(bookParameter).isEmpty()){
            throw new NoAuthorException();
        }
        bookEntity.setAuthors(getAuthorEntitiesFromBook(bookParameter));
        bookEntity.setSeries(bookParameter.getSeries());
        bookEntity.setYear(bookParameter.getYear());
        bookEntity.setGenre(bookParameter.getGenre());
        bookEntity.setPages(bookParameter.getPages());
        if(bookParameter.getEnded() != null){
            bookEntity.setRead(true);
        }
        else if(bookParameter.getRead() != null){
            bookEntity.setRead(bookParameter.getRead());
        }
        bookEntity.setStarted(bookParameter.getStarted());
        if(bookParameter.getStarted() != null && bookParameter.getEnded() != null &&
                bookParameter.getStarted().after(bookParameter.getEnded())){
            throw new InvalidDateException();
        }
        bookEntity.setEnded(bookParameter.getEnded());
        bookEntity.setRating(bookParameter.getRating());
        bookEntity.setNotes(bookParameter.getNotes());
        return bookRepository.save(bookEntity);
    }

    private Set<AuthorEntity> getAuthorEntitiesFromBook(BookParameter bookParameter) throws AuthorNotFoundException {
        Set<AuthorEntity> authorEntities = new HashSet<>();
        for (int authorId : bookParameter.getAuthors())
            authorEntities.add(authorService.findAuthorEntityById(authorId));
        return authorEntities;
    }



}
