package org.example.connectingtodatabase.exceptions;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message){
        super(message);
    }
}
