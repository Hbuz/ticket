package com.marco.ticket.exception;

public class ValueNotValidException extends RuntimeException {
    public ValueNotValidException() {
        super("The value is not valid");
    }
}