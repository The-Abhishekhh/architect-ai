package com.abhishek.architectai.ai;

import com.abhishek.architectai.client.AiApiRequest;
import com.abhishek.architectai.client.AiApiResponse;
import com.abhishek.architectai.config.GeminiConfig;
import com.google.genai.Client;
import com.google.genai.errors.ClientException;
import com.google.genai.types.GenerateContentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GeminiProvider implements AiProvider {

    private static final Logger logger =
            LoggerFactory.getLogger(GeminiProvider.class);

    private final Client geminiClient;
    private final GeminiConfig geminiConfig;

    public GeminiProvider(
            Client geminiClient,
            GeminiConfig geminiConfig) {

        this.geminiClient = geminiClient;
        this.geminiConfig = geminiConfig;
    }

    @Override
    public AiApiResponse evaluateAnswer(AiApiRequest request) {

        logger.info("Sending request to Gemini SDK...");

        String prompt = """
                You are an interview evaluator.

                Question:
                %s

                Candidate Answer:
                %s

                Evaluate the answer.
                Give:
                1. Score out of 10
                2. Feedback
                """.formatted(
                request.getQuestion(),
                request.getAnswer()
        );

        try {

            GenerateContentResponse response =
                    geminiClient.models.generateContent(
                            geminiConfig.getModel(),
                            prompt,
                            null
                    );

            return new AiApiResponse(response.text());

        } catch (ClientException e) {

            logger.error("Gemini API error", e);

            throw new RuntimeException(
                    "AI service is temporarily unavailable."
            );
        }
    }
}