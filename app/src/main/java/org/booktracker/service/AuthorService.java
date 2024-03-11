package org.booktracker.service;

import org.booktracker.entity.AuthorEntity;
import org.booktracker.exception.AuthorNotFoundException;
import org.booktracker.model.Author;
import org.booktracker.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
