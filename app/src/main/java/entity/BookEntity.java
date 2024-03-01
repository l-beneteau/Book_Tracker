package entity;


import jakarta.persistence.Entity;
import java.util.Date;
import lombok.Getter;
@Entity
public class BookEntity {
    @Getter private int id;
    @Getter private String title;
    @Getter private AuthorEntity author;
    @Getter private String series;
    @Getter private int year;
    @Getter private String genre;
    @Getter private boolean french;
    @Getter private boolean library;
    @Getter private int pages;
    @Getter private boolean readed;
    @Getter private Date started;
    @Getter private Date ended;
    private int rating;
    @Getter private String notes;

}
