package org.booktracker.service;

import org.booktracker.entity.AuthorEntity;
import org.booktracker.entity.BookEntity;
import org.booktracker.exception.AuthorNotFoundException;
import org.booktracker.exception.BookNotFoundException;
import org.booktracker.model.Author;
import org.booktracker.model.Book;
import org.booktracker.parameter.AuthorParameter;
import org.booktracker.repository.AuthorRepository;
import org.booktracker.response.AuthorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public Author findAuthorById(int id) throws AuthorNotFoundException {
        AuthorEntity authorEntity = authorRepository.findById(id);
        if (authorEntity == null){
            throw new AuthorNotFoundException(id);
        }
        return getAuthorByEntity(authorEntity);
    }

    private Author getAuthorByEntity(AuthorEntity authorEntity) {
        Author author = new Author();
        author.setAuthorId(authorEntity.getAuthorId());
        author.setName(authorEntity.getName());
        author.setBooks(getBooksFromAuthorEntity(authorEntity));
        return author;
    }
    
    private Set<Book> getBooksFromAuthorEntity(AuthorEntity authorEntity){
        Set <Book> books = new HashSet<>();
        for (BookEntity bookEntity : authorEntity.getBooks()){
            Book book = new Book();
            book.setBookId(bookEntity.getBookId());
            book.setTitle(bookEntity.getTitle());
            book.setSeries(book.getSeries());
            book.setYear(book.getYear());
            book.setGenre(book.getGenre());
            book.setPages(book.getPages());
            book.setRead(book.isRead());
            book.setRating(book.getRating());
            book.setNotes(book.getNotes());
            books.add(book);
        }
        return books;
    }


    public List<Author> findAuthors(AuthorParameter authorParameter) throws AuthorNotFoundException {
        List<AuthorEntity> authorEntities = authorRepository.find(authorParameter.getName());
        if(authorEntities.isEmpty()){
            throw new AuthorNotFoundException(authorParameter.getName());
        }
        List<Author> authors = new ArrayList<>();
        for (AuthorEntity authorEntity : authorEntities){
            authors.add(getAuthorByEntity(authorEntity));
        }
        return authors;
    }

    public AuthorEntity saveAuthor(Author newAuthor) {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setName(newAuthor.getName());
        return authorRepository.save(authorEntity);
    }

    public void deleteAuthorById(int id){
        authorRepository.deleteById(id);
    }
}
