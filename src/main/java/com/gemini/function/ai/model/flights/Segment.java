package com.gemini.function.ai.model.flights;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Data
public class Segment {
    private String id;
    @JsonProperty("origin_place_id")
    private Integer originPlaceId;
    @JsonProperty("destination_place_id")
    private Integer destinationPlaceId;
    private String departure;
    private String arrival;
    private String mode;
}
