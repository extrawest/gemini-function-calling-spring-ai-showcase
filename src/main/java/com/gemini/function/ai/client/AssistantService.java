package com.gemini.function.ai.client;

import com.gemini.function.ai.services.QuestionAgent;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@BrowserCallable
@AnonymousAllowed
@AllArgsConstructor
public class AssistantService {
    private final QuestionAgent questionAgent;

    public Flux<String> chat(String userMessage) {
//        return Flux.just(questionAgent.chat(userMessage));
        return Flux.just("Hello");
    }
}
