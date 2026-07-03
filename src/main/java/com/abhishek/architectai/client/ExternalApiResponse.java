package com.abhishek.architectai.client;

import lombok.Data;

@Data
public class ExternalApiResponse {

    private Integer userId;
    private Integer id;
    private String title;
    private String body;
}