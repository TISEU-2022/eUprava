package com.euprava.izradadokumenata.util.exceptions;

public class DocumentRemovedException extends RuntimeException{

    @Override
    public String getMessage() {
        return "Document has already been removed!";
    }
}
