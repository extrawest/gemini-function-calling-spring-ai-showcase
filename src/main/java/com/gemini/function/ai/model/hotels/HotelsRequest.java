package com.gemini.function.ai.model.hotels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HotelsRequest {
    private String city;
    private String checkin;
    private String checkout;
}
