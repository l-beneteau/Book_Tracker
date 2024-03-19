package org.booktracker.exception;

import java.util.Date;

public class InvalidDateException extends Exception{
    public InvalidDateException(){
        super("The entered dates are incompatibles");
    }
}
