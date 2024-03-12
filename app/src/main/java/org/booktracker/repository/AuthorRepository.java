package org.booktracker.repository;

import org.booktracker.entity.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends CrudRepository<AuthorEntity, Integer> {
    AuthorEntity findById(int id);
    //List<AuthorEntity> findByName(String name);

    @Query("SELECT a FROM AuthorEntity a WHERE (:name is null or a.name = :name)")
    List<AuthorEntity> find(@Param("name") String name);
}
