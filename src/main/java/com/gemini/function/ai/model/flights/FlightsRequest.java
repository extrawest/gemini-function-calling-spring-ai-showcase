package com.gemini.function.ai.model.flights;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Data
public class FlightsRequest {
    private String departureAirportCode;
    private String arrivalAirportCode;
    private String departureDate;
    private String numberOfAdults = "1";
    private String numberOfChildrens = "0";
    private String numberOfInfants = "1";
    private String cabinClass = "Economy";
    private String currency= "USD";

    public FlightsRequest(String departureAirportCode, String arrivalAirportCode, String departureDate) {
        this.departureAirportCode = departureAirportCode;
        this.arrivalAirportCode = arrivalAirportCode;
        this.departureDate = departureDate;
    }
}