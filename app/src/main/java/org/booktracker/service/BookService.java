package org.booktracker.service;

import org.booktracker.exception.BookNotFoundException;
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

    public Book findBookById(int id) throws BookNotFoundException{
        BookEntity bookEntity = bookRepository.findById(id);
        if (bookEntity == null){
            throw new BookNotFoundException(id);
        }
        return getBookFromEntity(bookEntity);
    }

//    public Book findBookByTitle(String title) throws BookNotFoundException{
//        BookEntity bookEntity = bookRepository.findByTitle(title);
//        if (bookEntity == null){
//            throw new BookNotFoundException(title);
//        }
//        return getBookFromEntity(bookEntity);
//    }

    public List<Book> findBooks(BookParameter bookParameter) throws BookNotFoundException {
//        List<BookEntity> bookEntities = bookRepository.find(bookParameter.getTitle(), bookParameter.getSeries(),
//                bookParameter.getYear(), bookParameter.getGenre(), bookParameter.getPages(), bookParameter.isRead(),
//                bookParameter.getRating());
        List<BookEntity> bookEntities = bookRepository.find(bookParameter.getTitle(), bookParameter.getSeries(),
                bookParameter.getYear(), bookParameter.getGenre(), bookParameter.isRead(), bookParameter.getRating());
        if (bookEntities.isEmpty()){
            throw new BookNotFoundException("Book");
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
        return book;
    }

//    public List<Book> findAllBooks(){
//       List<Book> books = new ArrayList<>();
//       Iterable<BookEntity> bookEntities = bookRepository.findAll();
//       for (BookEntity bookEntity : bookEntities){
//           books.add(getBookFromEntity(bookEntity));
//       }
//       return books;
//    }

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


    public BookEntity saveBook(Book newBook) {
        if(bookRepository.findByTitle(newBook.getTitle()) != null){
            return bookRepository.findByTitle(newBook.getTitle());
        }
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(newBook.getTitle());
        bookEntity.setAuthors(getAuthorEntitiesFromBook(newBook));
        return bookRepository.save(bookEntity);
    }

    private Set<AuthorEntity> getAuthorEntitiesFromBook(Book book){
        Set<AuthorEntity> authorEntities = new HashSet<>();
        for(Author author : book.getAuthors()) {
            AuthorEntity authorEntity = getAuthorEntityFromAuthor(author);
            authorEntities.add(authorEntity);
        }
        return authorEntities;
    }

    private static AuthorEntity getAuthorEntityFromAuthor(Author author) {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setName(author.getName());
        return authorEntity;
    }

    public void deleteBookById(int id){
        bookRepository.deleteById(id);
    }
}
