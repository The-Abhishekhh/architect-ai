package com.abhishek.architectai.exception;

import org.springframework.http.HttpStatus;

public class InterviewNotFoundException
        extends ApplicationException {

    public InterviewNotFoundException(String message) {

        super(
                message,
                HttpStatus.NOT_FOUND
        );

    }

}