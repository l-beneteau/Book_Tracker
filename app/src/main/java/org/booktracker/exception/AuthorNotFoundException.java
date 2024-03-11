package org.booktracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Author Not Found")
public class AuthorNotFoundException extends Exception{
    public AuthorNotFoundException(String name){
        super("Author "+name+" not found");
    }
    public AuthorNotFoundException(int id){
        super("Author "+String.valueOf(id)+" not found");
    }
}
