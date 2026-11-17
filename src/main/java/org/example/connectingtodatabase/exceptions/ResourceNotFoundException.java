package org.example.connectingtodatabase.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException (String message){
        super(message);
    }
}
