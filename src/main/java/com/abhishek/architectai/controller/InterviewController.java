package com.abhishek.architectai.controller;

import com.abhishek.architectai.dto.InterviewRequest;
import com.abhishek.architectai.dto.InterviewResponse;
import com.abhishek.architectai.dto.response.ApiResponse;
import com.abhishek.architectai.dto.response.PagedResponse;
import com.abhishek.architectai.entity.Interview;
import com.abhishek.architectai.service.InterviewService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/interview")
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping("/submit")
    public ApiResponse<InterviewResponse> submitAnswer(
            @Valid @RequestBody InterviewRequest request) {

        InterviewResponse response =
                interviewService.processInterviewAnswer(request);

        return new ApiResponse<>(
                true,
                LocalDateTime.now(),
                response
        );
    }

    @GetMapping("/history")
    public ApiResponse<PagedResponse<Interview>> getHistory(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String direction,

            @RequestParam(defaultValue = "")
            String keyword,

            @RequestParam(required = false)
            Integer minScore
    ) {

        Page<Interview> history =
                interviewService.getInterviewHistory(
                        page,
                        size,
                        sortBy,
                        direction,
                        keyword,
                        minScore
                );

        PagedResponse<Interview> pagedResponse =
                new PagedResponse<>(
                        history.getContent(),
                        history.getNumber(),
                        history.getSize(),
                        history.getTotalElements(),
                        history.getTotalPages()
                );

        return new ApiResponse<>(
                true,
                LocalDateTime.now(),
                pagedResponse
        );
    }

    @DeleteMapping("/{id}")
    public void deleteInterview(@PathVariable Long id) {
        interviewService.deleteInterview(id);
    }
}