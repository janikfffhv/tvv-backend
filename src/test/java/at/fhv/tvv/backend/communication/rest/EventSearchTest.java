package at.fhv.tvv.backend.communication.rest;

import at.fhv.tvv.shared.dto.EventSearchDTO;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class EventSearchTest {

    @Test
    void sucheEventNachSuchbegriff() {

        EventSearch eventSearch = new EventSearch();

        String sucheBegriff = "Dragon";

        List<EventSearchDTO> expectedEvents = new ArrayList<>();
        EventSearchDTO event1 = new EventSearchDTO(1009, "Dungeons & Dragons: Ehre unter Dieben", "Dungeons & Dragons: Ehre unter Dieben", 1725487200, "Hohenems", 7, 6);

        expectedEvents.add(event1);

        Response eventSearchResponse = eventSearch.search(sucheBegriff);
        List<EventSearchDTO> actualEvents = eventSearchResponse.readEntity(List.class);

        //Test gültig, wenn gilt...
        assertEquals(expectedEvents.size(), actualEvents.size());
        assertEquals(expectedEvents.get(0).getEventId(), actualEvents.get(0).getEventId());

    }

    @Test
    void sucheEventNachKategorie() {

        EventSearch eventSearch = new EventSearch();

        String sucheKategorie = "KINO";

        List<EventSearchDTO> expectedEvents = new ArrayList<>();
        EventSearchDTO event1 = new EventSearchDTO(1009, "Dungeons & Dragons: Ehre unter Dieben", "Dungeons & Dragons: Ehre unter Dieben", 1725487200, "Hohenems", 7, 6);
        EventSearchDTO event2 = new EventSearchDTO(1010, "Enterprise Applikationen - The Movie", "FH Movies", 1685196840, "Dornbirn", 7, 6);
        expectedEvents.add(event1);
        expectedEvents.add(event2);

        Response eventSearchResponse = eventSearch.searchByCategory(sucheKategorie);
        List<EventSearchDTO> actualEvents = eventSearchResponse.readEntity(List.class);

        //Test gültig, wenn gilt...
        assertEquals(expectedEvents.size(), actualEvents.size());
        assertEquals(expectedEvents.get(0).getEventId(), actualEvents.get(0).getEventId());

    }

    @Test
    void sucheEventNachZeitraum() {

        EventSearch eventSearch = new EventSearch();

        String startDatum = String.valueOf((new Date("15/05/2023").getTime()/1000));
        String endDatum = String.valueOf((new Date("23/05/2023").getTime()/1000));

        List<EventSearchDTO> expectedEvents = new ArrayList<>();
        EventSearchDTO event1 = new EventSearchDTO(1009, "Dungeons & Dragons: Ehre unter Dieben", "Dungeons & Dragons: Ehre unter Dieben", 1725487200, "Hohenems", 7, 6); //Datum = 21.05.2023

        expectedEvents.add(event1);

        Response eventSearchResponse = eventSearch.searchByDate(startDatum, endDatum);
        List<EventSearchDTO> actualEvents = eventSearchResponse.readEntity(List.class);

        //Test gültig, wenn gilt...
        assertEquals(expectedEvents.size(), actualEvents.size());
        assertEquals(expectedEvents.get(0).getEventId(), actualEvents.get(0).getEventId());

    }

    @Test
    void sucheEventNachID() throws IOException {

        //EventSearch eventSearch = new EventSearch();

        String sucheID = "1009"; //Dungeons & Dragons

        EventSearchDTO event = new EventSearchDTO(1009, "Dungeons & Dragons: Ehre unter Dieben", "Dungeons & Dragons: Ehre unter Dieben", 1725487200, "Hohenems", 7, 6); //Datum = 21.05.2023

        //Response eventIDResponse = eventSearch.search("", "", startDatum, endDatum);
        //List<EventSearchDTO> actualEvents = eventSearchResponse.readEntity(List.class);
        String urlString = "http://localhost:8080/backend-1.0-SNAPSHOT/api/teamd/event/searchById?id=" + sucheID;
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        InputStream inputStream = conn.getInputStream();

        String foundEventName = null;
        String foundEventID = null;

        BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
            String[] output = line.split(",");

            //EventID
            String[] split1  = output[0].split(":");
            foundEventID = split1[1];

            //Eventname
            String[] split2 = output[1].split("\"");
            foundEventName = split2[3];
        }



        //Test gültig, wenn gilt...
        assertEquals(sucheID, foundEventID);
        assertEquals(event.getName(), foundEventName);

    }

}