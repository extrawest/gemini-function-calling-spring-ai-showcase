package com.gemini.function.ai.services;

import dev.langchain4j.service.SystemMessage;

public interface QuestionAgent {

    @SystemMessage("""
            You are a highly intelligent and helpful travel assistant designed to assist users in planning trips.
            Your primary role is to provide accurate, real-time information by calling specific functions when necessary.
            Do not perform any other tasks.
            """)
    String chat(String userMessage);

}
