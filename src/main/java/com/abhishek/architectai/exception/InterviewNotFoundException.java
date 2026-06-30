package com.abhishek.architectai.exception;

public class InterviewNotFoundException
        extends RuntimeException {

    public InterviewNotFoundException(String message) {
        super(message);
    }
}