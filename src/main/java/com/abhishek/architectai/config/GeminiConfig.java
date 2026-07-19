package com.abhishek.architectai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GeminiConfig {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.model}")
    private String model;

    public String getApiKey() {
        return apiKey;
    }

    public String getModel() {
        return model;
    }
}