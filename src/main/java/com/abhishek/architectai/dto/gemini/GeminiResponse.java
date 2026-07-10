package com.abhishek.architectai.dto.gemini;

import lombok.Data;
import java.util.List;

@Data
public class GeminiResponse {

    private List<GeminiCandidate> candidates;

}