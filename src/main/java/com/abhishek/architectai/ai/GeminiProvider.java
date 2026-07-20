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
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class GeminiProvider implements AiProvider {


    private static final Logger logger =
            LoggerFactory.getLogger(GeminiProvider.class);

    private final Client geminiClient;
    private final GeminiConfig geminiConfig;
    private final ObjectMapper objectMapper;

    public GeminiProvider(
            Client geminiClient,
            GeminiConfig geminiConfig,
            ObjectMapper objectMapper) {

        this.geminiClient = geminiClient;
        this.geminiConfig = geminiConfig;
        this.objectMapper = objectMapper;
    }


    @Override
    public AiApiResponse evaluateAnswer(AiApiRequest request) {

        logger.info("Sending request to Gemini SDK...");

        String prompt = """
You are a senior Java interview evaluator.

Evaluate the candidate's answer.

Return ONLY valid JSON.

{
  "score": 0,
  "feedback": "",
  "strengths": [],
  "weaknesses": [],
  "improvements": []
}

Rules:
- score must be between 0 and 10
- Return ONLY JSON
- Do not use markdown
- Do not wrap JSON inside ``` blocks
- Do not add explanations before or after the JSON

Question:
%s

Candidate Answer:
%s
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

            return objectMapper.readValue(
                    response.text(),
                    AiApiResponse.class
            );

        } catch (Exception e) {

            logger.error("Gemini API error", e);

            throw new RuntimeException(
                    "AI service is temporarily unavailable."
            );
        }
    }
}