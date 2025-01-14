package com.gemini.function.ai.model.flights;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Data
public class PricingOption {
    private Price price;

    @Override
    public String toString() {
        return "PricingOption{" +
                "price=" + price +
                '}';
    }
}
