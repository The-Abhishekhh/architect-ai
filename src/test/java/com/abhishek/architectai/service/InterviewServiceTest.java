package com.abhishek.architectai.service;

import com.abhishek.architectai.ai.AiProvider;
import com.abhishek.architectai.client.AiApiResponse;
import com.abhishek.architectai.dto.InterviewRequest;
import com.abhishek.architectai.dto.InterviewResponse;
import com.abhishek.architectai.repository.InterviewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class InterviewServiceTest {

    @Mock
    private InterviewRepository interviewRepository;

    @Mock
    private AiProvider aiProvider;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private InterviewService interviewService;

    @Test
    void shouldCallAiProvider() {

        // Arrange

        AiApiResponse fakeResponse = new AiApiResponse();

        fakeResponse.setScore(8);
        fakeResponse.setFeedback("Strong technical answer");

        when(
                aiProvider.evaluateAnswer(any())
        ).thenReturn(fakeResponse);

        InterviewRequest request = new InterviewRequest();

        request.setQuestion("What is Polymorphism?");
        request.setAnswer(
                "Polymorphism allows one interface to have multiple implementations."
        );
        InterviewResponse response =
                interviewService.processInterviewAnswer(request);

    }


}