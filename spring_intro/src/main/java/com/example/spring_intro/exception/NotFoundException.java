package com.example.spring_intro.exception;

public class NotFoundException  extends Throwable {
    public NotFoundException(String message) {
        super(message);
    }
}
