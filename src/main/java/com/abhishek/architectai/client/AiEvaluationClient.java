package com.abhishek.architectai.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AiEvaluationClient {

    private static final Logger logger =
            LoggerFactory.getLogger(AiEvaluationClient.class);

    private final WebClient webClient;

    public AiEvaluationClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public AiApiResponse evaluateAnswer(AiApiRequest request) {

        logger.info("Sending AI request...");

        ExternalApiResponse response =
                webClient
                        .get()
                        .uri("https://jsonplaceholder.typicode.com/posts/1")
                        .retrieve()
                        .bodyToMono(ExternalApiResponse.class)
                        .block();

        logger.info("Response received.");

        return new AiApiResponse(
                "External API says: " +
                        response.getTitle()
        );
    }
}