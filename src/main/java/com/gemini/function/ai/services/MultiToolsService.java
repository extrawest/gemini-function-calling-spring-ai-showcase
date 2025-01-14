package com.gemini.function.ai.services;

import com.gemini.function.ai.model.attractions.Attraction;
import com.gemini.function.ai.model.attractions.AttractionProperty;
import com.gemini.function.ai.model.attractions.AttractionsRequest;
import com.gemini.function.ai.model.attractions.AttractionsResponse;
import com.gemini.function.ai.model.flights.FlightsRequest;
import com.gemini.function.ai.model.flights.FlightsResponse;
import com.gemini.function.ai.model.hotels.HotelsRequest;
import com.gemini.function.ai.model.hotels.HotelsResponse;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class MultiToolsService {
    private final ExternalApiHttpClient httpClient;

    @Tool("Search attractions by the user query")
    List<AttractionProperty> searchAttractions(@P("Name of the city and country where attractions is searching. Format: Tokyo, Japan") String city) {
        AttractionsRequest attractionsRequest = new AttractionsRequest(city);
        log.info("Calling function 'searchAttractions': {}", attractionsRequest);

        AttractionsResponse attractionsResponse = httpClient.searchAttractions(attractionsRequest);
        List<AttractionProperty> attractions = attractionsResponse.getFeatures().stream().map(Attraction::getProperties).toList();
        log.info(attractions.toString());

        return attractions;
    }

    @Tool("Search hotels by the user query")
    String searchHotels(
            @P("Name of the city") String city,
            @P("It is the check-in date Format - YYYY-MM-DD") String checkin,
            @P("It is the check-out date Format - YYYY-MM-DD") String checkout
    ) {
        HotelsRequest hotelsRequest = new HotelsRequest(city, checkin, checkout);
        log.info("Calling function 'searchHotels': {}", hotelsRequest);

        List<HotelsResponse> hotelsResponses = httpClient.searchHotels(hotelsRequest);
        log.info(hotelsResponses.toString());

        return hotelsResponses.toString();
    }

    @Tool("Search flights by the user query")
    String searchFlights(
            @P("This is the IATA code of departure airport") String departureAirportCode,
            @P("This is the IATA code of arrival airport") String arrivalAirportCode,
            @P("Date of departure Format - YYYY-MM-DD") String departureDate,
            @P("Date of Arrival Format - YYYY-MM-DD") String arrivalDate
    ) {
        FlightsRequest flightRequest = new FlightsRequest(departureAirportCode, arrivalAirportCode, departureDate, arrivalDate);
        log.info("Calling function 'searchFlights': {}", flightRequest);

        FlightsResponse flightsResponse = httpClient.searchFlights(flightRequest);
        log.info(flightsResponse.toString());

        return flightsResponse.toString();
    }
}
