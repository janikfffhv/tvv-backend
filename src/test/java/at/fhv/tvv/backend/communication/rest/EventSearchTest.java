package at.fhv.tvv.backend.communication.rest;

import at.fhv.tvv.shared.dto.EventSearchDTO;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class EventSearchTest {

    @Test
    void sucheEventNachKategorie() {

        EventSearch eventSearch = new EventSearch();

        String sucheKategorie = "KINO";

        List<EventSearchDTO> expectedEvents = new ArrayList<>();
        EventSearchDTO event1 = new EventSearchDTO(1009, "Dungeons & Dragons: Ehre unter Dieben", "Dungeons & Dragons: Ehre unter Dieben", 1725487200, "Hohenems", 7, 6);
        EventSearchDTO event2 = new EventSearchDTO(1010, "Enterprise Applikationen - The Movie", "FH Movies", 1685196840, "Dornbirn", 7, 6);
        expectedEvents.add(event1);
        expectedEvents.add(event2);

        Response eventSearchResponse = eventSearch.search("", sucheKategorie, "", "");
        List<EventSearchDTO> actualEvents = eventSearchResponse.readEntity(List.class);

        //Test gültig, wenn gilt...
        assertEquals(expectedEvents.size(), actualEvents.size());
        assertEquals(expectedEvents.get(0).getEventId(), actualEvents.get(0).getEventId());

    }
}