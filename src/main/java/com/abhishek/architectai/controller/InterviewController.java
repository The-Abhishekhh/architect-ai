package com.abhishek.architectai.controller;

import com.abhishek.architectai.entity.Interview;
import java.util.List;
import com.abhishek.architectai.dto.InterviewRequest;
import com.abhishek.architectai.dto.InterviewResponse;
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
    public InterviewResponse submitAnswer(@RequestBody InterviewRequest request) {
        return interviewService.processInterviewAnswer(request);
    }
    @GetMapping("/history")
    public List<Interview> getHistory() {
        return interviewService.getInterviewHistory();
    }
}