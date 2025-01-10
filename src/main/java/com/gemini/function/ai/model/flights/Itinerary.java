package com.gemini.function.ai.model.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Itinerary {
    private String id;
    @JsonProperty("leg_ids")
    private List<String> legIds;
    private double score;
}
