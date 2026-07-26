package com.abhishek.architectai.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;

import com.abhishek.architectai.dto.response.ApiResponse;
import com.abhishek.architectai.dto.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>>
    handleApplicationException(
            ApplicationException ex) {

        return ResponseEntity
                .status(ex.getStatus())
                .body(
                        new ApiResponse<>(
                                false,
                                LocalDateTime.now(),
                                new ErrorResponse(
                                        ex.getMessage()
                                )
                        )
                );
    }
}