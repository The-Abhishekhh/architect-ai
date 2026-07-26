package com.abhishek.architectai.controller;

import com.abhishek.architectai.entity.Interview;
import java.util.List;
import com.abhishek.architectai.dto.InterviewRequest;
import com.abhishek.architectai.dto.InterviewResponse;
import com.abhishek.architectai.service.InterviewService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.abhishek.architectai.dto.response.ApiResponse;
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
    public List<Interview> getHistory() {
        return interviewService.getInterviewHistory();
    }

    @DeleteMapping("/{id}")
    public void deleteInterview(@PathVariable Long id) { interviewService.deleteInterview(id);}


}