package org.booktracker.repository;

import org.booktracker.entity.AuthorEntity;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<AuthorEntity, Integer> {
    AuthorEntity findById(int id);
    AuthorEntity findByName(String name);
}
