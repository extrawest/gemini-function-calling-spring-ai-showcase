package com.gemini.function.ai.services;

import com.gemini.function.ai.model.flights.FlightsRequest;
import com.gemini.function.ai.model.flights.FlightsResponse;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class MultiToolsService {
    private final ExternalApiHttpClient httpClient;

    @Tool("Get the attraction for a key")
    String getAttraction(@P("Key to get the attraction") String key) {
        return "";
    }

    @Tool("Get the hotel for a key")
    String getHotel(@P("Key to get the hotel") String key) {
       return "";
    }

    @Tool("Search flights by the user query")
    String searchFlights(
            @P("This is the IATA code of departure airport.") String departureAirportCode,
            @P("This is the IATA code of arrival airport.") String arrivalAirportCode,
            @P("Date of departure Format - YYYY-MM-DD") String departureDate,
            @P("Date of Arrival Format - YYYY-MM-DD") String arrivalDate
    ) {
        FlightsRequest flightRequest = new FlightsRequest(
                departureAirportCode,
                arrivalAirportCode,
                departureDate,
                arrivalDate
        );
        log.info(flightRequest.toString());

        FlightsResponse flightsResponse = httpClient.searchFlights(flightRequest);
        log.info(flightsResponse.toString());

        return flightsResponse.toString();
    }
}
