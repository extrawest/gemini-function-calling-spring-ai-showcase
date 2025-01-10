package com.gemini.function.ai.model.hotels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Data
public class CityMappingResponse {
    private String type;
    private String title;
    @JsonProperty("document_id")
    private String documentId;
    private String scope;
    private String name;
}
