package com.ftn.KomunalnaPolicijaIInspekcija.exception;

public class NotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public NotFoundException() {}

    public NotFoundException(String message)
    {
        super(message);
    }

    public NotFoundException (Throwable cause) {
        super (cause);
    }

    public NotFoundException (String message, Throwable cause) {
        super (message, cause);
    }

}
