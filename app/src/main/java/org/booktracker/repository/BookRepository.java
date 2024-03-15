package org.booktracker.repository;

import org.booktracker.entity.BookEntity;
import org.booktracker.model.Genre;
import org.booktracker.model.Rating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends CrudRepository<BookEntity, Integer> {
    BookEntity findById(int id);

    @Query("SELECT b FROM BookEntity b WHERE (:title is null or b.title = :title) and " +
            "(:series is null or b.series = :series) and " +
            "(:year=0 or b.year = :year) and " +
            "(:genre is null or b.genre = :genre) and" +
            "(:read is null or b.read = :read) and " +
            "(:rating is null or b.rating = :rating)")
    List<BookEntity> find (@Param("title") String title, @Param("series") String series, @Param("year") int year,
                           @Param("genre") Genre genre, @Param("read") Boolean read, @Param("rating") Rating rating);

}
