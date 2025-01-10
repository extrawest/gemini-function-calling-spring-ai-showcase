package com.gemini.function.ai.model.flights;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class FlightsResponse {
    private List<Itinerary> itineraries;
    private List<Leg> legs;
    private List<Place> places;
    private List<Segment> segments;
}
