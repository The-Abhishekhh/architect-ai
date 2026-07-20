package com.abhishek.architectai.client;

import lombok.Data;

import java.util.List;

@Data
public class AiApiResponse {

    private Integer score;

    private String feedback;

    private List<String> strengths;

    private List<String> weaknesses;

    private List<String> improvements;
}