package com.abhishek.architectai.service.validator;

import com.abhishek.architectai.client.AiApiResponse;
import com.abhishek.architectai.exception.InvalidAiResponseException;
import org.springframework.stereotype.Component;

@Component
public class AiResponseValidator {

    public void validate(AiApiResponse response) {

        if (response == null) {
            throw new InvalidAiResponseException("AI returned a null response.");
        }

        if (response.getScore() == null) {
            throw new InvalidAiResponseException("AI response is missing the score.");
        }

        if (response.getScore() < 0 || response.getScore() > 10) {
            throw new InvalidAiResponseException(
                    "AI score must be between 0 and 10."
            );
        }

        if (response.getFeedback() == null || response.getFeedback().isBlank()) {
            throw new InvalidAiResponseException(
                    "AI response is missing feedback."
            );
        }

    }

}