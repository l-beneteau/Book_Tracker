package repository;

import entity.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookEntity, Integer> {
    public BookEntity findById(int id);
    public BookEntity findByTitle(String title);
}
