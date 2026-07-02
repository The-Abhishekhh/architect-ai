package com.abhishek.architectai.client;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AiApiRequest {

    private String question;
    private String answer;
}