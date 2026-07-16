package com.abhishek.architectai.config;

import com.google.genai.Client;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeminiClientConfig {

    private final GeminiConfig geminiConfig;

    public GeminiClientConfig(GeminiConfig geminiConfig) {
        this.geminiConfig = geminiConfig;
    }

    @PostConstruct
    public void printKey() {

        System.out.println("Loaded API Key:");
        System.out.println(geminiConfig.getApiKey());

    }

    @Bean
    public Client geminiClient() {

        return Client.builder()
                .apiKey(geminiConfig.getApiKey())
                .build();
    }
}