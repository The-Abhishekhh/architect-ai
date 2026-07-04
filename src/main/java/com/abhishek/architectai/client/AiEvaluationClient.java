package com.abhishek.architectai.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AiEvaluationClient {

    // Logger object
    private static final Logger logger =
            LoggerFactory.getLogger(AiEvaluationClient.class);

    // Dependency Injection
    private final RestTemplate restTemplate;

    public AiEvaluationClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AiApiResponse evaluateAnswer(AiApiRequest request) {

        logger.info("Sending AI request for question: {}",
                request.getQuestion());

        String url = "https://jsonplaceholder.typicode.com/posts/1";

        ExternalApiResponse response =
                restTemplate.getForObject(
                        url,
                        ExternalApiResponse.class
                );

        logger.info("Received response from external API.");

        String feedback =
                "External API says: " + response.getTitle();

        return new AiApiResponse(feedback);
    }
}