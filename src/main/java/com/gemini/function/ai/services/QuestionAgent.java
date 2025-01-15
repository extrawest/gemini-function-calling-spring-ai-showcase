package com.gemini.function.ai.services;

import com.gemini.function.ai.model.AIModelResponse;
import dev.langchain4j.service.SystemMessage;

public interface QuestionAgent {

    @SystemMessage("""
            Generate and run code for the calculation and make sure you get correct result.
            Return generated script and execution result.
            """)
    AIModelResponse chat(String userMessage);

}
