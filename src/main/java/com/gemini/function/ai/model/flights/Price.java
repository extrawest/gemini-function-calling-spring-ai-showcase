package com.gemini.function.ai.model.flights;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Data
public class Price {
    private double amount;
    @JsonProperty("update_status")
    private String updateStatus;
    @JsonProperty("last_updated")
    private String lastUpdated;
    @JsonProperty("quote_age")
    private int quoteAge;

    @Override
    public String toString() {
        return "Price{" +
                "amount=" + amount +
                ", updateStatus='" + updateStatus + '\'' +
                ", lastUpdated='" + lastUpdated + '\'' +
                ", quoteAge=" + quoteAge +
                '}';
    }
}
