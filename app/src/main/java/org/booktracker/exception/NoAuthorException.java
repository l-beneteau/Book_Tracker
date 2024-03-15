package org.booktracker.exception;

public class NoAuthorException extends Exception{
    public NoAuthorException(){super("No author id has been entered, the author id list can not be empty");}
}
