package at.fhv.tvv.backend.application;

import at.fhv.tvv.backend.domain.model.event.Event;
import at.fhv.tvv.backend.domain.model.platz.Kategorie;
import at.fhv.tvv.backend.domain.model.platz.Platz;
import at.fhv.tvv.backend.domain.model.veranstaltungsort.Veranstaltungsort;
import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Veranstaltungsserie;
import at.fhv.tvv.backend.domain.repository.EventRepository;
import at.fhv.tvv.backend.infrastructure.EventRepositoryImpl;
import at.fhv.tvv.shared.dto.EventDescriptionDTO;
import at.fhv.tvv.shared.dto.EventSearchDTO;
import at.fhv.tvv.shared.dto.PlatzDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventSearchImplTest {

    @Test
    void eventSearchImplKorrektInitialisieren() {

        EventSearchImpl test = new EventSearchImpl();

        //Test gültig, wenn gilt...
        assertEquals(EventSearchImpl.class, test.getClass());

    }

    @Test
    void PlaetzeZaehlenWenn0Plaetze() {

        List<Platz> plaetze = new ArrayList<>(); // -> 0 Plätze vorhanden

        //MOCKING
        EventRepository eventRepositoryMock = Mockito.mock(EventRepositoryImpl.class);
        EventSearchImpl eventSearchImpl = new EventSearchImpl(eventRepositoryMock);

       int actualPlatzCount = eventSearchImpl.countVerfuegbar(plaetze);

       //Test gültig, wenn gilt...
        assertEquals(0, actualPlatzCount);

    }

    @Test
    void PlaetzeZaehlenWenn3Plaetze() {

        List<Platz> plaetze = new ArrayList<>(); // -> 3 Plätze vorhanden
        Platz platz1 = new Platz(804, 804, 4, Kategorie.SITZPLATZ, 14.99F);
        Platz platz2 = new Platz(807, 806, 6, Kategorie.STEHPLATZ, 11.99F);
        Platz platz3 = new Platz(807, 807, 7, Kategorie.STEHPLATZ, 11.99F);
        plaetze.add(platz1);
        plaetze.add(platz2);
        plaetze.add(platz3);

        //MOCKING
        EventRepository eventRepositoryMock = Mockito.mock(EventRepositoryImpl.class);
        EventSearchImpl eventSearchImpl = new EventSearchImpl(eventRepositoryMock);

        int actualPlatzCount = eventSearchImpl.countVerfuegbar(plaetze);

        //Test gültig, wenn gilt...
        assertEquals(3, actualPlatzCount);

    }

    @Test
    void plaetzeOhneZugewiesenenVerkaufKorrektSuchen() {

        List<Platz> plaetze = new ArrayList<>(); // -> 3 Plätze vorhanden
        Platz platz1 = new Platz(804, 804, 4, Kategorie.SITZPLATZ, 14.99F);
        Platz platz2 = new Platz(807, 806, 6, Kategorie.STEHPLATZ, 11.99F);
        Platz platz3 = new Platz(807, 807, 7, Kategorie.STEHPLATZ, 11.99F);
        plaetze.add(platz1);
        plaetze.add(platz2);
        plaetze.add(platz3);

        //MOCKING
        EventRepository eventRepositoryMock = Mockito.mock(EventRepositoryImpl.class);
        EventSearchImpl eventSearchImpl = new EventSearchImpl(eventRepositoryMock);

        List<PlatzDTO> test = eventSearchImpl.getPlaetze(plaetze);

        //Test gültig, wenn gilt...
        assertEquals(3, test.size());
        assertEquals(platz1.getPlatzId(), test.get(0).getPlatzId());
        assertEquals("", test.get(0).getVerkaufsId());

    }

    @Test
    void WennAlsSuchbegriffRequiemEingegebenWirdSollEventDazuGefundenWerden()  {

        boolean eventFound = false;

        //TESTDATEN DEFINIEREN
        int datum1 = (int) (new Date("01/08/2023").getTime()/1000);
        Veranstaltungsort feldkirch1 = new Veranstaltungsort(670012, "Feldkirch Kulturhaus", "Marktplatz", "4", 6800, "Feldkirch", "Österreich", "Kultursaal");
        Veranstaltungsserie mc = new Veranstaltungsserie("Mozart-Classics", "Die glorreichsten Mozart-Stuecke jetzt in Feldkirch!", at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie.KONZERT, "Musica AG");

        List<Event> expectedEvents = new ArrayList<>();
        Event event = new Event(1007, "Requiem", mc, "Requiem - Das klassische Stueck aufgefuehrt in Feldkirch!", datum1, feldkirch1);
        expectedEvents.add(event);

        //MOCKING
        EventRepository eventRepositoryMock = Mockito.mock(EventRepositoryImpl.class);
        EventSearchImpl eventSearchImpl = new EventSearchImpl(eventRepositoryMock);

        String suchbegriff = "Requiem";
        Mockito.when(eventRepositoryMock.searchByString(suchbegriff)).thenReturn(expectedEvents);

        //EVENTSUCHE STARTEN
        List<EventSearchDTO> actualEvents = eventSearchImpl.searchByString(suchbegriff);

        for (EventSearchDTO eventDto : actualEvents) {
            System.out.println("Event Name: " + eventDto.getName() + "\n EventID: " + eventDto.getEventId() + "\nDatum: " + eventDto.getDatum());
            if (eventDto.getName().equals(suchbegriff)) {

                eventFound = true;
                break;
            }
        }

        //Test gültig, wenn ...
        assertTrue(eventFound);
        assertEquals(suchbegriff, actualEvents.get(0).getName());

    }

    @Test
    void WennAlsKategorieKINOEingegebenWirdSollen2EventsDazuGefundenWerden()  {

        boolean eventFound = false;

        //TESTDATEN DEFINIEREN
        int datum3 = (int) (new Date("21/08/2023").getTime()/1000);
        Veranstaltungsort hohenems1 = new Veranstaltungsort(670014, "Cineplexx Hohenems", "Lustenauer Str.", "112", 6845, "Hohenems", "Österreich", "IMAX");
        Veranstaltungsserie dad = new Veranstaltungsserie("Dungeons & Dragons: Ehre unter Dieben", "Basierend auf dem beliebten Fantasy-Rollenspiel begibt sich eine Gruppe von Abenteurern auf einen epischen Raubzug, um ein verlorenes Relikt zu stehlen.", at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie.KINO, "Constantin Film");

        int datum1 = (int) (new Date("01/08/2023").getTime()/1000);
        Veranstaltungsort dornbirn1 = new Veranstaltungsort(670015, "FH Dornbirn", "Hochschulstraße", "1", 6850, "Dornbirn", "Österreich", "U314");
        Veranstaltungsserie fhd = new Veranstaltungsserie("FH Movies", "Klassische Vorlesungen neu definiert.", at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie.KINO, "FH Dornbirn GmbH");


        List<Event> expectedEvents = new ArrayList<>();
        Event event1 = new Event(1009, "Dungeons & Dragons: Ehre unter Dieben", dad, "Kinovorstellung Nr. 1 in Hohenems", datum3, hohenems1);
        Event event2 = new Event(1010, "Enterprise Applikationen - The Movie", fhd, "Viele Vorlesungen - EIN GANZER FILM.", datum1, dornbirn1);
        expectedEvents.add(event1);
        expectedEvents.add(event2);

        //MOCKING
        EventRepository eventRepositoryMock = Mockito.mock(EventRepositoryImpl.class);
        EventSearchImpl eventSearchImpl = new EventSearchImpl(eventRepositoryMock);

        String expectedEvent1 = "Dungeons & Dragons: Ehre unter Dieben"; //Kategorie KINO
        String expectedEvent2 = "Enterprise Applikationen - The Movie"; //Kategorie KINO

        String gesuchteKategorie = "KINO";

        Mockito.when(eventRepositoryMock.searchByCategory(gesuchteKategorie)).thenReturn(expectedEvents);

        //EVENTSUCHE STARTEN
        List<EventSearchDTO> actualEvents = eventSearchImpl.searchByCategory(gesuchteKategorie);

        for (EventSearchDTO eventDto : actualEvents) {
            System.out.println("Event Name: " + eventDto.getName() + "\n EventID: " + eventDto.getEventId() + "\nDatum: " + eventDto.getDatum());
            if (eventDto.getDatum() == datum1) {

                eventFound = true;

            } else {
                eventFound = false;
            }
        }

        //Test gültig, wenn ...
        assertTrue(eventFound);
        assertEquals(2, actualEvents.size());
        assertEquals(dad.getName(), actualEvents.get(0).getVeranstaltungsserie()); //Veranstaltungsserie der Kino Kategorie
        assertEquals(fhd.getName(), actualEvents.get(1).getVeranstaltungsserie()); //Veranstaltungsserie der Kino Kategorie
        assertEquals(expectedEvent1, actualEvents.get(0).getName());
        assertEquals(expectedEvent2, actualEvents.get(1).getName());

    }

    @Test
    void WennAlsZeitraumErsterAugust2023EingegebenWirdSollen2EventsGefundenWerdenDieDortStattfinden()  {

        boolean eventFound = false;

        //TESTDATEN DEFINIEREN
        int datum1 = (int) (new Date("01/08/2023").getTime()/1000);
        Veranstaltungsort feldkirch1 = new Veranstaltungsort(670012, "Feldkirch Kulturhaus", "Marktplatz", "4", 6800, "Feldkirch", "Österreich", "Kultursaal");
        Veranstaltungsserie mc = new Veranstaltungsserie("Mozart-Classics", "Die glorreichsten Mozart-Stuecke jetzt in Feldkirch!", at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie.KONZERT, "Musica AG");
        Veranstaltungsort dornbirn1 = new Veranstaltungsort(670015, "FH Dornbirn", "Hochschulstraße", "1", 6850, "Dornbirn", "Österreich", "U314");
        Veranstaltungsserie fhd = new Veranstaltungsserie("FH Movies", "Klassische Vorlesungen neu definiert.", at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie.KINO, "FH Dornbirn GmbH");

        List<Event> expectedEvents = new ArrayList<>();
        Event event1 = new Event(1007, "Requiem", mc, "Requiem - Das klassische Stueck aufgefuehrt in Feldkirch!", datum1, feldkirch1);
        Event event2 = new Event(1010, "Enterprise Applikationen - The Movie", fhd, "Viele Vorlesungen - EIN GANZER FILM.", datum1, dornbirn1);
        expectedEvents.add(event1);
        expectedEvents.add(event2);

        //MOCKING
        EventRepository eventRepositoryMock = Mockito.mock(EventRepositoryImpl.class);
        EventSearchImpl eventSearchImpl = new EventSearchImpl(eventRepositoryMock);

        String expectedEvent1 = "Requiem"; //Datum 01.08.2023
        String expectedEvent2 = "Enterprise Applikationen - The Movie"; //Datum 01.08.2023

        int datumvon = (int) (new Date("01/08/2023").getTime()/1000);
        int datumbis = (int) (new Date("02/08/2023").getTime()/1000);

        Mockito.when(eventRepositoryMock.searchByDate(datumvon, datumbis)).thenReturn(expectedEvents);

        //EVENTSUCHE STARTEN
        List<EventSearchDTO> actualEvents = eventSearchImpl.searchByDate(datumvon, datumbis);

        for (EventSearchDTO eventDto : actualEvents) {
            System.out.println("Event Name: " + eventDto.getName() + "\n EventID: " + eventDto.getEventId() + "\nDatum: " + eventDto.getDatum());
            if (eventDto.getDatum() == datum1) {

                eventFound = true;

            } else {
                eventFound = false;
            }
        }

        //Test gültig, wenn ...
        assertTrue(eventFound);
        assertEquals(2, actualEvents.size());
        assertEquals(datum1, actualEvents.get(0).getDatum());
        assertEquals(datum1, actualEvents.get(1).getDatum());
        assertEquals(expectedEvent1, actualEvents.get(0).getName());
        assertEquals(expectedEvent2, actualEvents.get(1).getName());

    }

    @Test
    void WennNachID1010GesuchtWirdSollEntApplMovieEventGefundenWerden()  {

        //TESTDATEN DEFINIEREN
        int datum1 = (int) (new Date("01/08/2023").getTime()/1000);
        Veranstaltungsort dornbirn1 = new Veranstaltungsort(670015, "FH Dornbirn", "Hochschulstraße", "1", 6850, "Dornbirn", "Österreich", "U314");
        Veranstaltungsserie fhd = new Veranstaltungsserie("FH Movies", "Klassische Vorlesungen neu definiert.", at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie.KINO, "FH Dornbirn GmbH");

        Event expectedEvent = new Event(1010, "Enterprise Applikationen - The Movie", fhd, "Viele Vorlesungen - EIN GANZER FILM.", datum1, dornbirn1);


        //MOCKING
        EventRepository eventRepositoryMock = Mockito.mock(EventRepositoryImpl.class);
        EventSearchImpl eventSearchImpl = new EventSearchImpl(eventRepositoryMock);

        String expectedEventName = "Enterprise Applikationen - The Movie"; //ID 1010

        int gesuchteID = 1010;

        Mockito.when(eventRepositoryMock.searchById(gesuchteID)).thenReturn(expectedEvent);


        //EVENTSUCHE STARTEN
        EventDescriptionDTO actualEvent = eventSearchImpl.searchById(gesuchteID);


        //Test gültig, wenn ...
        assertEquals(expectedEventName, actualEvent.getName());
        assertEquals(gesuchteID, actualEvent.getEventId());

    }

}