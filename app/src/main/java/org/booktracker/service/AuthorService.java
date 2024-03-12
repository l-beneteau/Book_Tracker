package org.booktracker.service;

import org.booktracker.entity.AuthorEntity;
import org.booktracker.entity.BookEntity;
import org.booktracker.exception.AuthorNotFoundException;
import org.booktracker.exception.BookNotFoundException;
import org.booktracker.model.Author;
import org.booktracker.model.Book;
import org.booktracker.repository.AuthorRepository;
import org.booktracker.response.AuthorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
        return author;
    }

    public Author findAuthorByName(String name) throws AuthorNotFoundException {
        AuthorEntity authorEntity = authorRepository.findByName(name);
        if (authorEntity == null){
            throw new AuthorNotFoundException(name);
        }
        return getAuthorByEntity(authorEntity);
    }

    public AuthorEntity saveAuthor(Author newAuthor) {
        if(authorRepository.findByName(newAuthor.getName()) != null){
            return authorRepository.findByName(newAuthor.getName());
        }
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setName(newAuthor.getName());
        authorEntity.setBooks(getBookEntitiesFromAuthor(newAuthor));
        return authorRepository.save(authorEntity);
    }

    private Set<BookEntity> getBookEntitiesFromAuthor(Author newAuthor) {
        Set<BookEntity> bookEntities = new HashSet<>();
        for(Book book : newAuthor.getBooks()){
            BookEntity bookEntity = new BookEntity();
            bookEntity.setBookId(book.getBookId());
            bookEntity.setTitle(book.getTitle());
            bookEntities.add(bookEntity);
        }
        return bookEntities;
    }
}
