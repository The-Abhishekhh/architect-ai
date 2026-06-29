package com.abhishek.architectai.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class InterviewRequest {

    @NotBlank
    private String question;

    @NotBlank
    private String answer;
}
