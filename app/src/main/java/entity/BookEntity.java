package entity;


import back.Rating;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import java.util.Date;

import lombok.*;

@Entity
@Getter@Setter
@AllArgsConstructor@RequiredArgsConstructor
public class BookEntity {
    @NonNull private int id;
    @NonNull private String title;
    @NonNull private AuthorEntity author;
    private String series;
    private int year;
    private String genre;
    private boolean french;
    private boolean library;
    private int pages;
    private boolean readed;
    private Date started;
    private Date ended;
    @Convert(converter = RatingConverter.class)
    private Rating rating;
    private String notes;


}
