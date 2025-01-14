package com.gemini.function.ai.model.hotels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Data
public class HotelGeocode {
    private double latitude;
    private double longitude;

    @Override
    public String toString() {
        return "HotelGeocode{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
