package com.gemini.function.ai.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gemini.function.ai.model.attractions.AttractionsRequest;
import com.gemini.function.ai.model.attractions.AttractionsResponse;
import com.gemini.function.ai.model.attractions.Place;
import com.gemini.function.ai.model.attractions.PlaceIdResponse;
import com.gemini.function.ai.model.flights.FlightsRequest;
import com.gemini.function.ai.model.flights.FlightsResponse;
import com.gemini.function.ai.model.hotels.CityMappingResponse;
import com.gemini.function.ai.model.hotels.HotelsRequest;
import com.gemini.function.ai.model.hotels.Hotel;
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
    @Value("${external.api.flight.api-key}")
    private String flightApiKey;
    @Value("${external.api.hotel.api-key}")
    private String hotelApiKey;
    @Value("${external.api.attraction.api-key}")
    private String attractionApiKey;

    // REQUEST example: https://api.geoapify.com/v2/places?categories=tourism.attraction&apiKey={api_key}&filter=place:51b9afa5f60daf3e4059c604df6b2d3e4740f00101f90155a9c40000000000c0020892032ad09ed0b4d0b5d181d18cd0bad0b020d0bcd196d181d18cd0bad0b020d0b3d180d0bed0bcd0b0d0b4d0b0&limit=20
    public AttractionsResponse searchAttractions(AttractionsRequest request) {
        PlaceIdResponse placeId = getPlaceId(request.getCity());
        if (Objects.nonNull(placeId)) {
            log.info(placeId.toString());
        }

        if (Objects.nonNull(placeId) && Objects.nonNull(placeId.getResults()) && !placeId.getResults().isEmpty()) {
            Place place = placeId.getResults().getFirst();
            log.info(place.toString());
            return getRestClient("https://api.geoapify.com/v2/places")
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("categories", "tourism.sights,tourism.attraction")
                            .queryParam("apiKey", attractionApiKey)
                            .queryParam("filter", "place:" + place.getPlaceId())
                            .queryParam("format", "json")
                            .queryParam("limit", 10)
                            .queryParam("lang", "en")
                            .build()
                    )
                    .retrieve()
                    .body(AttractionsResponse.class);
        } else {
            return new AttractionsResponse();
        }
    }

    // REQUEST example: https://api.flightapi.io/roundtrip/{api_key}/HAN/SGN/2025-04-11/2025-04-11/1/0/1/Economy/USD
    public FlightsResponse searchFlights(FlightsRequest request) {
        return getRestClient("https://api.flightapi.io/onewaytrip")
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(getFlightsPath(request))
                        .build()
                )
                .retrieve()
                .body(FlightsResponse.class);
    }

    // REQUEST example: https://api.makcorps.com/city?api_key={api_key}&cityid=60763&pagination=0&cur=USD&rooms=1&adults=2&checkin=2023-12-25&checkout=2023-12-26
    public List<Hotel> searchHotels(HotelsRequest request) {
        CityMappingResponse[] cityIdArray = findCityId(request.getCity());
        log.info("CityMappingResponse[]: {}", Arrays.toString(cityIdArray));

        Optional<CityMappingResponse> geo = Arrays.stream(cityIdArray).filter(cityMapping -> cityMapping.getType().equals("GEO")).findFirst();
        if (geo.isPresent()) {
            log.info(geo.toString());
            String hotelResponseString = getRestClient("https://api.makcorps.com/city")
                    .get()
                    .uri(uriBuilder -> uriBuilder
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
                    return Arrays.asList(objectMapper.readValue("[" + substring + "]", Hotel[].class));
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

    // REQUEST example: https://api.geoapify.com/v1/geocode/search?apiKey={api_key}&text=11%20Av.%20de%20la%20Bourdonnais%2C%2075007%20Paris%2C%20France&format=json
    private PlaceIdResponse getPlaceId(String place) {
        return getRestClient("https://api.geoapify.com/v1/geocode/search")
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("apiKey", attractionApiKey)
                        .queryParam("text", place)
                        .queryParam("type", "city")
                        .queryParam("format", "json")
                        .queryParam("limit", 10)
                        .queryParam("lang", "en")
                        .build()
                )
                .retrieve()
                .body(PlaceIdResponse.class);
    }

    // REQUEST example: https://api.makcorps.com/mapping?api_key={api_key}&name=London"
    private CityMappingResponse[] findCityId(String city) {
        return getRestClient("https://api.makcorps.com/mapping")
                .get()
                .uri(uriBuilder -> uriBuilder
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
