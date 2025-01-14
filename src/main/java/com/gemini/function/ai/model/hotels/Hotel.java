package com.gemini.function.ai.model.hotels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Data
public class Hotel {
    private Integer hotelId;
    private String telephone;
    private String name;
    private String price1;
    private HotelGeocode geocode;
    private HotelReviews reviews;

    @Override
    public String toString() {
        return "HotelsResponse{" +
                "hotelId=" + hotelId +
                ", telephone='" + telephone + '\'' +
                ", name='" + name + '\'' +
                ", price1='" + price1 + '\'' +
                ", geocode=" + geocode +
                ", reviews=" + reviews +
                '}';
    }
}
