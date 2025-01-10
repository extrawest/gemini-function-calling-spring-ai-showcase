package com.gemini.function.ai.model.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Leg {
    private String id;
    @JsonProperty("origin_place_id")
    private Integer originPlaceId;
    @JsonProperty("destination_place_id")
    private Integer destinationPlaceId;
    @JsonProperty("segment_ids")
    private List<String> segmentIds;
    private String departure;
    private String arrival;
}
