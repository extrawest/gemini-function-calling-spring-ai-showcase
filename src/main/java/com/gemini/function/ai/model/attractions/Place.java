package com.gemini.function.ai.model.attractions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@NoArgsConstructor
@Data
public class Place {
    private String city;
    private String country;
    @JsonProperty("place_id")
    private String placeId;
}
