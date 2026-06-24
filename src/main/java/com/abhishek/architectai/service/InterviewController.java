package com.abhishek.architectai.service;

import com.abhishek.architectai.dto.InterviewRequest;
import com.abhishek.architectai.service.InterviewService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interview")
public class InterviewController {

    private final InterviewService interviewService;
    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping("/submit")
    public String submitInterview(@RequestBody InterviewRequest interviewRequest) {
        return interviewService.processInterviewAnswer();
    }
}
