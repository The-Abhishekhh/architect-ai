package com.abhishek.architectai.service;

import java.util.List;

import com.abhishek.architectai.ai.AiProvider;
import com.abhishek.architectai.dto.InterviewRequest;
import com.abhishek.architectai.dto.InterviewResponse;
import com.abhishek.architectai.entity.Interview;
import com.abhishek.architectai.repository.InterviewRepository;
import org.springframework.stereotype.Service;
import com.abhishek.architectai.exception.InterviewNotFoundException;
import com.abhishek.architectai.client.AiApiRequest;
import com.abhishek.architectai.client.AiApiResponse;
import org.springframework.beans.factory.annotation.Qualifier;

@Service
public class InterviewService {

    private final InterviewRepository interviewRepository;
    private final AiProvider aiProvider;



    public InterviewService(
            InterviewRepository interviewRepository,

            @Qualifier("geminiProvider")
            AiProvider aiProvider) {

        this.interviewRepository = interviewRepository;
        this.aiProvider = aiProvider;
    }

    public InterviewResponse processInterviewAnswer(
            InterviewRequest request) {

        AiApiRequest aiRequest =
                new AiApiRequest(
                        request.getQuestion(),
                        request.getAnswer()
                );

        AiApiResponse aiResponse =
                aiProvider.evaluateAnswer(aiRequest);

        int score = 8;
        String feedback =
                aiResponse.getFeedback();

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

        if (!interviewRepository.existsById(id)) {
            throw new InterviewNotFoundException(
                    "Interview with id " + id + " not found"
            );
        }

        interviewRepository.deleteById(id);
    }
}