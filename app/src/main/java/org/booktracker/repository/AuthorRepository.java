package org.booktracker.repository;

import org.booktracker.entity.AuthorEntity;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<AuthorEntity, Integer> {
    public AuthorEntity findByID(int id);
    public AuthorEntity findByName(String name);
}
