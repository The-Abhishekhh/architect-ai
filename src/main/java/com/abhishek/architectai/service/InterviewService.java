package com.abhishek.architectai.service;

import com.abhishek.architectai.dto.InterviewRequest;
import com.abhishek.architectai.dto.InterviewResponse;
import org.springframework.stereotype.Service;

@Service
public class InterviewService {

    public InterviewResponse processInterviewAnswer(InterviewRequest request) {

        String answer = request.getAnswer().toLowerCase();

        int score = 2;
        String feedback = "Missing system design fundamentals";

        if (answer.contains("distributed")) {
            score += 2;
        }

        if (answer.contains("database")) {
            score += 2;
        }

        if (answer.contains("cache")) {
            score += 2;
        }

        if (answer.contains("load balancer")) {
            score += 2;
        }

        if (score >= 8) {
            feedback = "Strong architecture understanding";
        }

        return new InterviewResponse(score, feedback);
    }
}