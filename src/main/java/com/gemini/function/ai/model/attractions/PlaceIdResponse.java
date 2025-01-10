package com.gemini.function.ai.model.attractions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@NoArgsConstructor
@Data
public class PlaceIdResponse {
    private List<Place> results;
}
