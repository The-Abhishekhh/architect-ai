package com.abhishek.architectai.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            InterviewNotFoundException.class
    )
    public Map<String, String> handleInterviewNotFound(
            InterviewNotFoundException ex) {

        return Map.of(
                "error",
                ex.getMessage()
        );
    }
    @ExceptionHandler(
            InvalidAiResponseException.class
    )
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public Map<String, String> handleInvalidAiResponse(
            InvalidAiResponseException ex) {

        return Map.of(
                "error",
                ex.getMessage()
        );
    }
}