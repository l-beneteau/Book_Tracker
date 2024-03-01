package repository;

import entity.AuthorEntity;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<AuthorEntity, Integer> {
    AuthorEntity findByID(int id);
    AuthorEntity findByName(String name);
}
