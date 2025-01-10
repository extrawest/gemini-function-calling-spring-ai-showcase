package com.gemini.function.ai.model.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

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
