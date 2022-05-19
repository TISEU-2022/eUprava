package com.euprava.izradadokumenata.util.exceptions;

public class DocumentMissingException extends RuntimeException{

    @Override
    public String getMessage() {
        return "The requested document does not exist!";
    }
}
