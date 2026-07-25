package com.abhishek.architectai.exception;

import org.springframework.http.HttpStatus;

public class InvalidAiResponseException
        extends ApplicationException {

    public InvalidAiResponseException(String message) {

        super(
                message,
                HttpStatus.BAD_GATEWAY
        );

    }

}