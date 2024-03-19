package org.booktracker.response;

import lombok.Data;
import org.booktracker.entity.BookEntity;
import org.booktracker.model.Book;
import org.booktracker.model.Genre;
import org.booktracker.model.Rating;

import java.util.Date;

@Data
public class BookOfAuthorResponse {
    private Integer bookId;
    private String title;
    private String series;
    private Integer year;
    private Genre genre;
    private Integer pages;
    private boolean read;
    private Date started;
    private Date ended;
    private Rating rating;
    private String notes;

    public static BookOfAuthorResponse from(Book book) {
        BookOfAuthorResponse bookOfAuthorResponse = new BookOfAuthorResponse();
        bookOfAuthorResponse.bookId = book.getBookId();
        bookOfAuthorResponse.title = book.getTitle();
        bookOfAuthorResponse.series = book.getSeries();
        bookOfAuthorResponse.year = book.getYear();
        bookOfAuthorResponse.genre=book.getGenre();
        bookOfAuthorResponse.pages=book.getPages();
        bookOfAuthorResponse.read=book.isRead();
        bookOfAuthorResponse.started=book.getStarted();
        bookOfAuthorResponse.ended=book.getEnded();
        bookOfAuthorResponse.rating=book.getRating();
        bookOfAuthorResponse.notes=book.getNotes();
        return bookOfAuthorResponse;
    }

    public static BookOfAuthorResponse from(BookEntity book) {
        BookOfAuthorResponse bookOfAuthorResponse = new BookOfAuthorResponse();
        bookOfAuthorResponse.bookId = book.getBookId();
        bookOfAuthorResponse.title = book.getTitle();
        bookOfAuthorResponse.series = book.getSeries();
        bookOfAuthorResponse.year = book.getYear();
        bookOfAuthorResponse.genre=book.getGenre();
        bookOfAuthorResponse.pages=book.getPages();
        bookOfAuthorResponse.read=book.isRead();
        bookOfAuthorResponse.started=book.getStarted();
        bookOfAuthorResponse.ended=book.getEnded();
        bookOfAuthorResponse.rating=book.getRating();
        bookOfAuthorResponse.notes=book.getNotes();
        return bookOfAuthorResponse;
    }
}
