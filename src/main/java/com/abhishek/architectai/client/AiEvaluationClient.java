package com.abhishek.architectai.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import com.abhishek.architectai.config.GeminiConfig;
import com.abhishek.architectai.dto.gemini.*;
import java.util.List;

@Component
public class AiEvaluationClient {



    private static final Logger logger =
            LoggerFactory.getLogger(AiEvaluationClient.class);

    private final WebClient webClient;
    private final GeminiConfig geminiConfig;


    public AiEvaluationClient(
            WebClient webClient,
            GeminiConfig geminiConfig) {

        this.webClient = webClient;
        this.geminiConfig = geminiConfig;
    }

    public AiApiResponse evaluateAnswer(AiApiRequest request) {

        logger.info("Sending request to Gemini...");

        String prompt = """
            You are an interview evaluator.

            Question:
            %s

            Candidate Answer:
            %s

            Evaluate this answer.
            Give:
            1. Score out of 10
            2. Feedback
            """.formatted(
                request.getQuestion(),
                request.getAnswer()
        );

        InteractionRequest interactionRequest =
                new InteractionRequest(
                        "gemini-3.5-flash",
                        prompt
                );

        String response =
                webClient.post()
                        .uri("https://generativelanguage.googleapis.com/v1beta/interactions")
                        .header("x-goog-api-key", geminiConfig.getApiKey())
                        .header("Content-Type","application/json")
                        .bodyValue(interactionRequest)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();

        logger.info("Gemini Response : {}", response);

        return new AiApiResponse(response);
    }
}
