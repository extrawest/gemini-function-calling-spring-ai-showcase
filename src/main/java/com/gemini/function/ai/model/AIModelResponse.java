package com.gemini.function.ai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AIModelResponse {
    private String script;
    private String result;

    @Override
    public String toString() {
        return "AIModelResponse{" +
                "script='" + script + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
