package com.euprava.izradadokumenata.util.exceptions;

public class UserMissingException extends RuntimeException {

    @Override
    public String getMessage() {
        return "User doesn't exist!";
    }
}
