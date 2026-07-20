package com.abhishek.architectai.ai;

import com.abhishek.architectai.client.AiApiRequest;
import com.abhishek.architectai.client.AiApiResponse;
import org.springframework.stereotype.Component;

@Component
public class MockAiProvider implements AiProvider {

    @Override
    public AiApiResponse evaluateAnswer(AiApiRequest request) {

        AiApiResponse response = new AiApiResponse();

        response.setScore(8);

        response.setFeedback(
                "Good attempt at answering the question. Add more technical depth and include a real-world example."
        );

        response.setStrengths(
                java.util.List.of(
                        "Relevant answer",
                        "Good attempt"
                )
        );

        response.setWeaknesses(
                java.util.List.of(
                        "Lacks technical depth"
                )
        );

        response.setImprovements(
                java.util.List.of(
                        "Add real-world examples",
                        "Explain concepts in more detail"
                )
        );

        return response;
    }
}