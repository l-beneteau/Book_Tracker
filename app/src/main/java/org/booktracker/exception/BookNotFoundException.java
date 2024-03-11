package org.booktracker.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Actor Not Found")
public class BookNotFoundException extends Exception {
    public BookNotFoundException(String book){
        super("Book "+book+" not found");
    }
    public BookNotFoundException(int id){
        super("Book "+String.valueOf(id)+" not found");
    }

}

