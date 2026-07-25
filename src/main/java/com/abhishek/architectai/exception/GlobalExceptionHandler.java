package com.abhishek.architectai.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Map<String, String>> handleApplicationException(
            ApplicationException ex) {

        return ResponseEntity
                .status(ex.getStatus())
                .body(
                        Map.of(
                                "error",
                                ex.getMessage()
                        )
                );
    }
}