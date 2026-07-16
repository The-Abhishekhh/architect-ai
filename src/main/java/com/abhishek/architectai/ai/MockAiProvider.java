package com.abhishek.architectai.ai;

import com.abhishek.architectai.client.AiApiRequest;
import com.abhishek.architectai.client.AiApiResponse;
import org.springframework.stereotype.Component;

@Component
public class MockAiProvider implements AiProvider {

    @Override
    public AiApiResponse evaluateAnswer(AiApiRequest request) {

        String feedback = """
                Score: 8/10

                Strengths:
                - Good attempt at answering the question.
                - Answer is relevant to the topic.

                Improvements:
                - Add more technical depth.
                - Include a real-world example.
                """;

        return new AiApiResponse(feedback);
    }
}