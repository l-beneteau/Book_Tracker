package org.booktracker.response;

import org.booktracker.model.Book;
import org.booktracker.model.Rating;

import java.util.Date;

public class BookOfAuthorResponse {
    private Integer book_id;
    private String title;
    private String series;
    private Integer year;
    private String genre;
    private boolean french;
    private boolean library;
    private Integer pages;
    private boolean read;
    private Date started;
    private Date ended;
    private Rating rating;
    private String notes;

    public static BookOfAuthorResponse from(Book book) {
        BookOfAuthorResponse bookOfAuthorResponse = new BookOfAuthorResponse();
        bookOfAuthorResponse.book_id = book.getBook_id();
        bookOfAuthorResponse.title = book.getTitle();
        bookOfAuthorResponse.series = book.getSeries();
        bookOfAuthorResponse.year = book.getYear();
        bookOfAuthorResponse.genre=book.getGenre();
        bookOfAuthorResponse.french= book.isFrench();
        bookOfAuthorResponse.library=book.isLibrary();
        bookOfAuthorResponse.pages=book.getPages();
        bookOfAuthorResponse.read=book.isRead();
        bookOfAuthorResponse.started=book.getStarted();
        bookOfAuthorResponse.ended=book.getEnded();
        bookOfAuthorResponse.rating=book.getRating();
        bookOfAuthorResponse.notes=book.getNotes();
        return bookOfAuthorResponse;
    }
}
