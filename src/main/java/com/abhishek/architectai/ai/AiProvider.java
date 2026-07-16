package com.abhishek.architectai.ai;

import com.abhishek.architectai.client.AiApiRequest;
import com.abhishek.architectai.client.AiApiResponse;

public interface AiProvider {

    AiApiResponse evaluateAnswer(AiApiRequest request);

}