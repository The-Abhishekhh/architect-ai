package com.abhishek.architectai.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import com.abhishek.architectai.config.GeminiConfig;
import com.abhishek.architectai.dto.gemini.*;
import java.util.List;
import com.google.genai.Client;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

@Component
public class AiEvaluationClient {



    private static final Logger logger =
            LoggerFactory.getLogger(AiEvaluationClient.class);

    private final Client geminiClient;
    private final GeminiConfig geminiConfig;


    public AiEvaluationClient(
            Client geminiClient,
            GeminiConfig geminiConfig) {

        this.geminiClient = geminiClient;
        this.geminiConfig = geminiConfig;
    }

    public AiApiResponse evaluateAnswer(AiApiRequest request) {

        logger.info("Sending request to Gemini SDK...");

        String prompt = """
            You are an interview evaluator.

            Question:
            %s

            Candidate Answer:
            %s

            Evaluate the answer.

            Return:
            Score out of 10
            Feedback
            """.formatted(
                request.getQuestion(),
                request.getAnswer()
        );

        GenerateContentResponse response =
                geminiClient.models.generateContent(
                        "gemini-pro-latest",
                        prompt,
                        null
                );

        logger.info("Gemini response received.");

        return new AiApiResponse(
                response.text()
        );
    }
}
