package org.booktracker.response;

import org.booktracker.model.Book;
import org.booktracker.model.Rating;

import java.util.Date;

public class BookOfAuthorResponse {
    private Integer bookId;
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
        bookOfAuthorResponse.bookId = book.getBookId();
        bookOfAuthorResponse.title = book.getTitle();
        bookOfAuthorResponse.series = book.getSeries();
        bookOfAuthorResponse.year = book.getYear();
        bookOfAuthorResponse.genre=book.getGenre();
        bookOfAuthorResponse.french= book.isAvalaibleInFrench();
        bookOfAuthorResponse.library=book.isAvalaibleInLibrary();
        bookOfAuthorResponse.pages=book.getPages();
        bookOfAuthorResponse.read=book.isRead();
        bookOfAuthorResponse.rating=book.getRating();
        bookOfAuthorResponse.notes=book.getNotes();
        return bookOfAuthorResponse;
    }
}
