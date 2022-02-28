package com.marco.ticket.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("No ticket found");
    }
}