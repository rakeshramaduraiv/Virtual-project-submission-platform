package com.examly.springapp.exception;

public class InappropriateContentException extends RuntimeException {
    public InappropriateContentException(String message) {
        super(message);
    }
}