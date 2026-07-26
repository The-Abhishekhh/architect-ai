package com.abhishek.architectai.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiResponse<T> {

    private boolean success;

    private LocalDateTime timestamp;

    private T data;

}