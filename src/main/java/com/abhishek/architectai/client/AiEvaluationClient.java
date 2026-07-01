package com.abhishek.architectai.client;

import org.springframework.stereotype.Component;

@Component
public class AiEvaluationClient {

    public String evaluateAnswer(String question,
                                 String answer) {

        if (answer.length() > 40) {
            return "Strong answer with detailed explanation";
        }

        return "Weak answer. Lacks technical depth";
    }
}