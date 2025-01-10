package com.gemini.function.ai.services;

import com.gemini.function.ai.model.flights.FlightsRequest;
import com.gemini.function.ai.model.flights.FlightsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExternalApiHttpClient {
    private static final String API_URL = "https://api.flightapi.io/roundtrip";

    @Value("${external.api.flight.api-key}")
    private String flightApiKey;

    // URL example: https://api.flightapi.io/roundtrip/677d1ededfe82f1479cdfb41/HAN/SGN/2025-04-11/2025-04-11/1/0/1/Economy/USD
    public FlightsResponse searchFlights(FlightsRequest request) {
        return getRestClient()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(getPath(request))
                        .build()
                )
                .retrieve()
                .body(FlightsResponse.class);
    }

    private String getPath(FlightsRequest request) {
        return "/" + flightApiKey +
                "/" + request.getDepartureAirportCode() +
                "/" + request.getArrivalAirportCode() +
                "/" + request.getDepartureDate() +
                "/" + request.getArrivalDate() +
                "/" + request.getNumberOfAdults() +
                "/" + request.getNumberOfChildrens() +
                "/" + request.getNumberOfInfants() +
                "/" + request.getCabinClass() +
                "/" + request.getCurrency();
    }

    private RestClient getRestClient() {
        return RestClient.builder()
                .baseUrl(API_URL)
                .defaultHeaders(httpHeaders -> httpHeaders.set("Content-Type", "application/json"))
                .build();
    }

}
