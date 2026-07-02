package com.abhishek.architectai.client;

import org.springframework.stereotype.Component;

@Component
public class AiEvaluationClient {

    public AiApiResponse evaluateAnswer(
            AiApiRequest request) {

        System.out.println(
                "Sending request to external AI..."
        );

        String feedback;

        if (request.getAnswer().length() > 40) {
            feedback =
                    "AI says: Strong technical answer";
        } else {
            feedback =
                    "AI says: Weak technical explanation";
        }

        return new AiApiResponse(feedback);
    }
}