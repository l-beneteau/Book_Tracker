package org.booktracker.exception;


import org.booktracker.parameter.BookParameter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Book Not Found")
public class BookNotFoundException extends Exception {
    public BookNotFoundException(int id){
        super("Book "+String.valueOf(id)+" not found");
    }
    public BookNotFoundException(BookParameter bookParameter){
        super("No book with properties "+bookParameter.toString() +" found");
    }

}

