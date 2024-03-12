package org.booktracker.response;

import lombok.Data;
import org.booktracker.model.Author;
import org.booktracker.model.Book;
import org.booktracker.model.Rating;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class BookResponse {
    private Integer book_id;
    private String title;
    Set<AuthorOfBookResponse> authors = new HashSet<>();
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
    
    public static BookResponse from(Book book){
        BookResponse bookResponse = new BookResponse();
        bookResponse.book_id = book.getBook_id();
        bookResponse.title = book.getTitle();
        bookResponse.series = book.getSeries();
        bookResponse.year = book.getYear();
        bookResponse.genre=book.getGenre();
        bookResponse.french= book.isFrench();
        bookResponse.library=book.isLibrary();
        bookResponse.pages=book.getPages();
        bookResponse.read=book.isRead();
        bookResponse.started=book.getStarted();
        bookResponse.ended=book.getEnded();
        bookResponse.rating=book.getRating();
        bookResponse.notes=book.getNotes();
        for(Author author : book.getAuthors()){
            bookResponse.authors.add(AuthorOfBookResponse.from(author));
        }
        return bookResponse;
        
    }
}
