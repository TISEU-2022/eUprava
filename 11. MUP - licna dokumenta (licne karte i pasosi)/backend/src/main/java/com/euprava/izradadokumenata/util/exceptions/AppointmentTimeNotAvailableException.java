package com.euprava.izradadokumenata.util.exceptions;

public class AppointmentTimeNotAvailableException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Requested appointment time is not available!";
    }
}
