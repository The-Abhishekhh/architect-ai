package com.abhishek.architectai.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AiEvaluationClient {

    private static final Logger logger =
            LoggerFactory.getLogger(AiEvaluationClient.class);

    private final RestTemplate restTemplate;

    public AiEvaluationClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AiApiResponse evaluateAnswer(AiApiRequest request) {

        logger.info("Sending AI request for question: {}",
                request.getQuestion());

        String url = "https://jsonplaceholder.typicode.com/posts/1";


        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");


        HttpEntity<Void> entity = new HttpEntity<>(headers);


        ResponseEntity<ExternalApiResponse> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        entity,
                        ExternalApiResponse.class
                );

        logger.info("Received response from external API.");

        String feedback =
                "External API says: " +
                        response.getBody().getTitle();

        return new AiApiResponse(feedback);
    }
}