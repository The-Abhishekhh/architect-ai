package com.abhishek.architectai.service;

import java.util.List;
import com.abhishek.architectai.dto.InterviewRequest;
import com.abhishek.architectai.dto.InterviewResponse;
import com.abhishek.architectai.entity.Interview;
import com.abhishek.architectai.repository.InterviewRepository;
import org.springframework.stereotype.Service;

@Service
public class InterviewService {

    private final InterviewRepository interviewRepository;

    public InterviewService(InterviewRepository interviewRepository) {
        this.interviewRepository = interviewRepository;
    }

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

        Interview interview = new Interview(
                null,
                request.getQuestion(),
                request.getAnswer(),
                score,
                feedback
        );

        interviewRepository.save(interview);

        return new InterviewResponse(score, feedback);
    }
    public List<Interview> getInterviewHistory() {
        return interviewRepository.findAll();
    }

    public void deleteInterview(Long id) {
        interviewRepository.deleteById(id);
    }
}