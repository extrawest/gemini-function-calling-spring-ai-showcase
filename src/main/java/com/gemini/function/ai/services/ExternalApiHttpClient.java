package com.gemini.function.ai.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gemini.function.ai.model.attractions.AttractionsRequest;
import com.gemini.function.ai.model.attractions.AttractionsResponse;
import com.gemini.function.ai.model.flights.FlightsRequest;
import com.gemini.function.ai.model.flights.FlightsResponse;
import com.gemini.function.ai.model.hotels.CityMappingResponse;
import com.gemini.function.ai.model.hotels.HotelsRequest;
import com.gemini.function.ai.model.hotels.HotelsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExternalApiHttpClient {
    private static final String FLIGHT_API_URL = "https://api.flightapi.io/roundtrip";
    private static final String HOTEL_API_URL = "https://api.makcorps.com";

    @Value("${external.api.flight.api-key}")
    private String flightApiKey;
    @Value("${external.api.hotel.api-key}")
    private String hotelApiKey;

    public AttractionsResponse searchAttractions(AttractionsRequest request) {
        return null;
    }

    // REQUEST example: https://api.flightapi.io/roundtrip/{api_key}/HAN/SGN/2025-04-11/2025-04-11/1/0/1/Economy/USD
    public FlightsResponse searchFlights(FlightsRequest request) {
        return getRestClient(FLIGHT_API_URL)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(getFlightsPath(request))
                        .build()
                )
                .retrieve()
                .body(FlightsResponse.class);
    }

    // REQUEST example: https://api.makcorps.com/city?api_key={api_key}&cityid=60763&pagination=0&cur=USD&rooms=1&adults=2&checkin=2023-12-25&checkout=2023-12-26
    public List<HotelsResponse> searchHotels(HotelsRequest request) {
        CityMappingResponse[] cityIdArray = findCityId(request.getCity());
        Optional<CityMappingResponse> geo = Arrays.stream(cityIdArray).filter(cityMapping -> cityMapping.getType().equals("GEO")).findFirst();
        if (geo.isPresent()) {
            log.info(geo.toString());
            String hotelResponseString = getRestClient(HOTEL_API_URL)
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/city")
                            .queryParam("api_key", hotelApiKey)
                            .queryParam("cityid", geo.get().getDocumentId())
                            .queryParam("pagination", 0)
                            .queryParam("cur", "USD")
                            .queryParam("rooms", 1)
                            .queryParam("adults", 2)
                            .queryParam("checkin", request.getCheckin())
                            .queryParam("checkout", request.getCheckout())
                            .build()
                    )
                    .retrieve()
                    .body(String.class);
            if (Objects.nonNull(hotelResponseString)) {
                String substring = hotelResponseString.substring(1, hotelResponseString.lastIndexOf("[") - 1);
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    log.info("[{}]", substring);
                    return Arrays.asList(objectMapper.readValue("[" + substring + "]", HotelsResponse[].class));
                } catch (JsonProcessingException e) {
                    log.warn(e.getMessage());
                    return Collections.emptyList();
                }
            } else {
                return Collections.emptyList();
            }
        } else {
            return Collections.emptyList();
        }
    }

    // REQUEST example: https://api.makcorps.com/mapping?api_key={api_key}&name=London"
    private CityMappingResponse[] findCityId(String city) {
        return getRestClient(HOTEL_API_URL)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/mapping")
                        .queryParam("api_key", hotelApiKey)
                        .queryParam("name", city)
                        .build()
                )
                .retrieve()
                .body(CityMappingResponse[].class);
    }

    private String getFlightsPath(FlightsRequest request) {
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

    private RestClient getRestClient(String url) {
        return RestClient.builder()
                .baseUrl(url)
                .defaultHeaders(httpHeaders -> httpHeaders.set("Content-Type", "application/json"))
                .build();
    }

}
