package com.gemini.function.ai.model.flights;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Itinerary {
    private String id;
    @JsonProperty("leg_ids")
    private List<String> legIds;
    private double score;
    @JsonProperty("pricing_options")
    private List<PricingOption> pricingOptions;

    @Override
    public String toString() {
        return "Itinerary{" +
                "id='" + id + '\'' +
                ", legIds=" + legIds +
                ", score=" + score +
                ", pricingOptions=" + pricingOptions +
                '}';
    }
}
