package org.booktracker.repository;

import org.booktracker.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookEntity, Integer> {
    BookEntity findById(int id);
    BookEntity findByTitle(String title);

}
