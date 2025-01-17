package com.gemini.function.ai.model.attractions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@ToString
@Data
public class AttractionsResponse {
    private String type;
    private List<Attraction> features;
}
