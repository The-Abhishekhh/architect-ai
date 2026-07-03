package com.abhishek.architectai.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AiEvaluationClient {

    private final RestTemplate restTemplate;

    public AiEvaluationClient(
            RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    public AiApiResponse evaluateAnswer(
            AiApiRequest request) {

        String url =
                "https://jsonplaceholder.typicode.com/posts/1";

        ExternalApiResponse response =
                restTemplate.getForObject(
                        url,
                        ExternalApiResponse.class
                );

        String feedback =
                "External API says: " +
                        response.getTitle();

        return new AiApiResponse(feedback);
    }
}