package com.abhishek.architectai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InterviewResponse {

    private int score;
    private String feedback;
}