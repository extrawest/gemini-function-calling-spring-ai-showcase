package com.gemini.function.ai.model.hotels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@NoArgsConstructor
@Data
public class HotelsResponse {
    private Integer hotelId;
    private String telephone;
    private String name;
}
