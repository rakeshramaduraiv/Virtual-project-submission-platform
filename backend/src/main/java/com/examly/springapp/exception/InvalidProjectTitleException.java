package com.examly.springapp.exception;

public class InvalidProjectTitleException extends RuntimeException {
    public InvalidProjectTitleException(String message) {
        super(message);
    }
}