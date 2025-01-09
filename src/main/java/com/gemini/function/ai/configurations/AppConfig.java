package com.gemini.function.ai.configurations;

import com.gemini.function.ai.services.MultiToolsService;
import com.gemini.function.ai.services.QuestionAgent;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.vertexai.ToolCallingMode;
import dev.langchain4j.service.AiServices;
import lombok.extern.slf4j.Slf4j;
import dev.langchain4j.model.vertexai.VertexAiGeminiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class AppConfig {
    @Value("${google.project-id}")
    private String projectId;
    @Value("${google.location}")
    private String location;


    @Bean(name = "chatLanguageModel")
    public ChatLanguageModel chatLanguageModel() {
        return VertexAiGeminiChatModel.builder()
                .project(projectId)
                .location(location)
                .temperature(0f)
                .logRequests(true)
                .toolCallingMode(ToolCallingMode.ANY)
                .logResponses(true)
                .modelName("gemini-1.5-flash-001")
                .build();
    }

    @Bean(name = "messageWindowChatMemory")
    public MessageWindowChatMemory messageWindowChatMemory() {
        return MessageWindowChatMemory.builder()
                .maxMessages(20)
                .build();
    }

    @Bean(name = "questionAgent")
    public QuestionAgent questionAgent(
            ChatLanguageModel model,
            MessageWindowChatMemory chatMemory,
            MultiToolsService multiToolsService
    ) {

        return AiServices.builder(QuestionAgent.class)
                .chatLanguageModel(model)
                .chatMemory(chatMemory)
                .tools(multiToolsService)
                .build();

    }

}
