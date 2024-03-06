package org.booktracker.service;

import jakarta.annotation.PostConstruct;
import org.booktracker.entity.AuthorEntity;
import org.booktracker.entity.BookEntity;
import org.booktracker.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MocheService {
    BookRepository bookRepository;

    @Autowired
    public MocheService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    void init() {
        bookRepository.save(
                new BookEntity("Picha le BG", Set.of(new AuthorEntity("Les mamans de Picha")))
        );
        //bookRepository.findByTitle("Picha le BG");
    }
}

