package com.gemini.function.ai.services;

import com.gemini.function.ai.model.TicketResponse;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import com.gemini.function.ai.model.AttractionResponse;
import com.gemini.function.ai.model.HotelResponse;
import org.springframework.stereotype.Service;

@Service
public class MultiToolsService {

    @Tool("Get the attraction for a key")
    AttractionResponse getAttraction(@P("Key to get the attraction") String key) {
        if (key.equals("Attraction1")) {
            return new AttractionResponse("Attraction1");
        } else if (key.equals("Attraction2")) {
            return new AttractionResponse("Attraction2");
        } else {
            return new AttractionResponse("UnknownAttraction");
        }
    }

    @Tool("Get the hotel for a key")
    HotelResponse getHotel(@P("Key to get the hotel") String key) {
        if (key.equals("Hotel1")) {
            return new HotelResponse("Hotel1");
        } else if (key.equals("Hotel2")) {
            return new HotelResponse("Hotel2");
        } else {
            return new HotelResponse("UnknownHotel");
        }
    }

    @Tool("Get the ticket for a key")
    TicketResponse getTicket(@P("Key to get the ticket") String key) {
        if (key.equals("Ticket1")) {
            return new TicketResponse("Ticket1");
        } else if (key.equals("Ticket2")) {
            return new TicketResponse("Ticket2");
        } else {
            return new TicketResponse("UnknownTicket");
        }
    }
}
