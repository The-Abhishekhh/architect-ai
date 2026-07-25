package com.abhishek.architectai.exception;

import org.springframework.http.HttpStatus;

public class AiServiceUnavailableException
        extends ApplicationException {

    public AiServiceUnavailableException(
            String message) {

        super(
                message,
                HttpStatus.SERVICE_UNAVAILABLE
        );

    }

}