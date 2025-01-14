package com.gemini.function.ai.model.hotels;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class HotelReviews {
    private double rating;
    private int count;

    @Override
    public String toString() {
        return "HotelReviews{" +
                "rating=" + rating +
                ", count=" + count +
                '}';
    }
}
