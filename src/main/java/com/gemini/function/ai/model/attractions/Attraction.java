package com.gemini.function.ai.model.attractions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@NoArgsConstructor
@Data
public class Attraction {
    private String type;
    private AttractionProperty properties;
}
