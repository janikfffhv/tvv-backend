package at.fhv.tvv.backend.application;

import at.fhv.tvv.backend.HibernateService;

import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie;
import at.fhv.tvv.shared.dto.EventDescriptionDTO;
import at.fhv.tvv.shared.dto.EventSearchDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class EventSearchImplTest {

    @Test
    void BruceSpringKonzertFinden()  {
        System.out.println("-------------------------------------Test 1 BruceSpringKonzertFinden-------------------------");
        boolean eventFound = false;

        EventSearchImpl eventSearch = (EventSearchImpl) HibernateService.eventSearchImpl();
        String suchbegriff = "Bruce Springsteen Konzert";

        List<EventSearchDTO> actualEvents = eventSearch.searchByString(suchbegriff);

        for (EventSearchDTO event : actualEvents) {
            System.out.println("Event Name: " + event.getName() + "\n EventID: " + event.getEventId() + "\nDatum: " + event.getDatum());
            if (event.getName().equals(suchbegriff)) {

                eventFound = true;
                break;
            }
        }

        //Test gültig, wenn ...
        assertTrue(eventFound);

    }

    @Test
    void WennTeilStringGesuchtWirdMitBruce() {
        System.out.println("-------------------------------------Test 2 WennTeilStringGesuchtWirdMitBruce-------------------------");
        boolean eventFound = false;

        EventSearchImpl eventSearch = (EventSearchImpl) HibernateService.eventSearchImpl();
        String suchbegriff1 = "Bruce Springsteen Konzert";
        String suchbegriff = "Bruce";

        List<EventSearchDTO> actualEvents = eventSearch.searchByString(suchbegriff);

        for (EventSearchDTO event : actualEvents) {
            System.out.println("Event Name: " + event.getName() + "\n EventID: " + event.getEventId() + "\nDatum: " + event.getDatum());

            if (event.getName().equals(suchbegriff1)) {

                eventFound = true;
                break;
            }
        }

        //Test gültig, wenn ...
        assertTrue(eventFound);

    }

    @Test
    void FindeBruceMitDatum() {
        System.out.println("-------------------------------------Test 3 FindeBruceMitDatum-------------------------");
        boolean eventFound = false;

        EventSearchImpl eventSearch = (EventSearchImpl) HibernateService.eventSearchImpl();
        String suchbegriff1 = "Bruce Springsteen Konzert";
        int startDate = 1682002800;
        int endDate = 1683001000;

        List<EventSearchDTO> actualEvents = eventSearch.searchByDate(startDate, endDate);

        for (EventSearchDTO event : actualEvents) {
            System.out.println("Event Name: " + event.getName() + "\n EventID: " + event.getEventId() + "\nDatum: " + event.getDatum());

            if (event.getName().equals(suchbegriff1)) {
                eventFound = true;
                break;
            }
        }

        //Test gültig, wenn ...
        assertTrue(eventFound);

    }

    @Test
    void BruceSpringsteenNachKategorieSuchen() {
        System.out.println("-------------------------------------Test 4 BruceSpringsteenNachKategorieSuchen-------------------------");
        boolean eventFound = false;

        EventSearchImpl eventSearch = (EventSearchImpl) HibernateService.eventSearchImpl();
        String suchbegriff1 = "Bruce Springsteen Konzert";
        Kategorie suchbegriff = Kategorie.KONZERT;

        List<EventSearchDTO> actualEvents = eventSearch.searchByCategory(String.valueOf(suchbegriff));

        for (EventSearchDTO event : actualEvents) {
            System.out.println("Event Name: " + event.getName() + "\n EventID: " + event.getEventId() + "\nDatum: " + event.getDatum());

            if (event.getName().equals(suchbegriff1)) {

                eventFound = true;
                break;
            }
        }

        //Test gültig, wenn ...
        assertTrue(eventFound);

    }

    @Test
    void BruceSpringsteenNachKategorieTheaterSuchenGleichNichtsGefunden() {
        System.out.println("-------------------------------------Test 5 BruceSpringsteenNachKategorieTheaterSuchenGleichNichtsGefunden-------------------------");
        boolean eventFound = false;

        EventSearchImpl eventSearch = (EventSearchImpl) HibernateService.eventSearchImpl();
        String suchbegriff1 = "Bruce Springsteen Konzert";
        Kategorie suchbegriff = Kategorie.THEATER;

        List<EventSearchDTO> actualEvents = eventSearch.searchByCategory(String.valueOf(suchbegriff));

        for (EventSearchDTO event : actualEvents) {
            System.out.println("Event Name: " + event.getName() + "\n EventID: " + event.getEventId() + "\nDatum: " + event.getDatum());

            if (event.getName().equals(suchbegriff1)) {

                eventFound = true;
                break;
            }
        }

        //Test gültig, wenn ...
        assertFalse(eventFound);

    }

    @Test
    void BruceSpringsteenNachKategorieKinoSuchenGleichNichtsGefunden() {
        System.out.println("-------------------------------------Test 6 BruceSpringsteenNachKategorieKinoSuchenGleichNichtsGefunden-------------------------");
        boolean eventFound = false;

        EventSearchImpl eventSearch = (EventSearchImpl) HibernateService.eventSearchImpl();
        String suchbegriff1 = "Bruce Springsteen Konzert";
        Kategorie suchbegriff = Kategorie.KINO;

        List<EventSearchDTO> actualEvents = eventSearch.searchByCategory(String.valueOf(suchbegriff));

        for (EventSearchDTO event : actualEvents) {
            System.out.println("Event Name: " + event.getName() + "\n EventID: " + event.getEventId() + "\nDatum: " + event.getDatum());

            if (event.getName().equals(suchbegriff1)) {

                eventFound = true;
                break;
            }
        }

        //Test gültig, wenn ...
        assertFalse(eventFound);

    }

    @Test
    void BruceSpringsteenMitIdFinden() {
        System.out.println("-------------------------------------Test 7 BruceSpringsteenMitIdFinden-------------------------");
        boolean eventFound = false;

        EventSearchImpl eventSearch = (EventSearchImpl) HibernateService.eventSearchImpl();


        int sucheId = 1006;

        EventDescriptionDTO actualEvents = eventSearch.searchById(sucheId);



        //Test gültig, wenn ...
        assertEquals("Bruce Springsteen Konzert", actualEvents.getName());
        assertEquals(1006,actualEvents.getEventId());

    }

}