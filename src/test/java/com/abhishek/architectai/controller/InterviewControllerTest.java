package com.abhishek.architectai.controller;

import com.abhishek.architectai.exception.InterviewNotFoundException;
import com.abhishek.architectai.service.InterviewService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import com.abhishek.architectai.dto.InterviewRequest;
import com.abhishek.architectai.dto.InterviewResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import com.abhishek.architectai.exception.InvalidAiResponseException;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.abhishek.architectai.entity.Interview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import java.util.List;


@WebMvcTest(InterviewController.class)
class InterviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InterviewService interviewService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldSubmitInterviewAnswer() throws Exception {

        InterviewResponse fakeResponse =
                new InterviewResponse(
                        4,
                        "Strong technical answer"
                );

        when(interviewService.processInterviewAnswer(any()))
                .thenReturn(fakeResponse);

        InterviewRequest request =
                new InterviewRequest();

        request.setQuestion("What is Polymorphism?");
        request.setAnswer(
                "Polymorphism allows one interface to have multiple implementations."
        );


        mockMvc.perform(
                        post("/api/interview/submit")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(request)
                                )
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.score").value(4))
                .andExpect(
                        jsonPath("$.data.feedback")
                                .value("Strong technical answer")
                );

        ArgumentCaptor<InterviewRequest> requestCaptor =
                ArgumentCaptor.forClass(InterviewRequest.class);

        verify(interviewService)
                .processInterviewAnswer(requestCaptor.capture());


        InterviewRequest capturedRequest =
                requestCaptor.getValue();

        assertEquals(
                "What is Polymorphism?",
                capturedRequest.getQuestion()
        );

        assertEquals(
                "Polymorphism allows one interface to have multiple implementations.",
                capturedRequest.getAnswer()
        );
    }

    @Test
    void shouldRejectBlankQuestion() throws Exception {

        InterviewRequest request =
                new InterviewRequest();

        request.setQuestion("");
        request.setAnswer(
                "Polymorphism allows one interface to have multiple implementations."
        );

        String json =
                objectMapper.writeValueAsString(request);

        mockMvc.perform(
                        post("/api/interview/submit")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isBadRequest());

        verify(interviewService, never())
                .processInterviewAnswer(any());
    }

    @Test
    void shouldRejectBlankAnswer() throws Exception {

        InterviewRequest request =
                new InterviewRequest();

        request.setQuestion("What is Polymorphism?");
        request.setAnswer("");

        String json =
                objectMapper.writeValueAsString(request);

        mockMvc.perform(
                        post("/api/interview/submit")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isBadRequest());

        verify(interviewService, never())
                .processInterviewAnswer(any());
    }

    @Test
    void shouldHandleInvalidAiResponseException() throws Exception {

        when(interviewService.processInterviewAnswer(any()))
                .thenThrow(
                        new InvalidAiResponseException(
                                "AI provider returned an invalid response: score is missing"
                        )
                );

        InterviewRequest request =
                new InterviewRequest();

        request.setQuestion("What is Polymorphism?");
        request.setAnswer(
                "Polymorphism allows one interface to have multiple implementations."
        );

        String json =
                objectMapper.writeValueAsString(request);

        mockMvc.perform(
                        post("/api/interview/submit")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isBadGateway())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(
                        jsonPath("$.data.error")
                                .value(
                                        "AI provider returned an invalid response: score is missing"
                                )
                );

        verify(interviewService)
                .processInterviewAnswer(any());


    }

    @Test
    void shouldDeleteInterview() throws Exception {

        doNothing()
                .when(interviewService)
                .deleteInterview(1L);

        mockMvc.perform(
                        delete("/api/interview/{id}", 1L)
                )
                .andExpect(status().isOk());

        verify(interviewService)
                .deleteInterview(1L);
    }

    @Test
    void shouldReturnNotFoundWhenInterviewDoesNotExist() throws Exception {

        Long interviewId = 999L;

        doThrow(
                new InterviewNotFoundException(
                        "Interview with id " + interviewId + " not found"
                )
        )
                .when(interviewService)
                .deleteInterview(interviewId);

        mockMvc.perform(
                        delete("/api/interview/{id}", interviewId)
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(
                        jsonPath("$.data.error")
                                .value(
                                        "Interview with id 999 not found"
                                )
                );


        verify(interviewService)
                .deleteInterview(interviewId);
    }

    @Test
    void shouldGetInterviewHistory() throws Exception {


        Interview interview =
                new Interview(
                        1L,
                        "What is Polymorphism?",
                        "Polymorphism allows one interface to have multiple implementations.",
                        4,
                        "Strong technical answer"
                );

        Page<Interview> fakePage =
                new PageImpl<>(
                        List.of(interview)
                );

        when(
                interviewService.getInterviewHistory(
                        0,
                        5,
                        "id",
                        "desc",
                        "Design",
                        7,
                        9
                )
        )
                .thenReturn(fakePage);


        mockMvc.perform(
                        get("/api/interview/history")
                                .param("keyword", "Design")
                                .param("minScore", "7")
                                .param("maxScore", "9")
                                .param("page", "0")
                                .param("size", "5")
                                .param("sortBy", "id")
                                .param("direction", "desc")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.items[0].id").value(1))
                .andExpect(jsonPath("$.data.items[0].score").value(4));

    }

}