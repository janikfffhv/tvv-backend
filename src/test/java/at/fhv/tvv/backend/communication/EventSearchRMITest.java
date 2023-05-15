package at.fhv.tvv.backend.communication;

import at.fhv.tvv.backend.HibernateService;
import at.fhv.tvv.shared.dto.EventDescriptionDTO;
import at.fhv.tvv.shared.dto.EventSearchDTO;
import at.fhv.tvv.shared.rmi.EventSearch;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventSearchRMITest {

    @Test
    void wennNachBegriffGesuchtWirdSollBruceSpringsteenKonzertAlsEventGefundenWerden() throws RemoteException {

        EventSearch eventSearchRMI = HibernateService.eventSearchRMI();

        String suchbegriff = "Bruce Springsteen Konzert";

        List<EventSearchDTO> actualEvents = eventSearchRMI.searchByString(suchbegriff);

        for(EventSearchDTO event : actualEvents) {

            System.out.println("Event-Name: " + event.getName()
                    + "\nEvent-ID: " + event.getEventId()
                    + "\nDatum: " + event.getDatum());

        }

        boolean eventFound = false;
        for(EventSearchDTO event : actualEvents) {

            if(event.getName().equals("Bruce Springsteen Konzert")) {

                eventFound = true;
                break;

            }

        }

        //Test gültig, wenn gilt...
        assertTrue(eventFound);

    }

    @Test
    void wennNachTeilstringGesuchtWirdSollBruceSpringsteenKonzertAlsEventGefundenWerden() throws RemoteException {

        EventSearch eventSearchRMI = HibernateService.eventSearchRMI();

        String suchbegriff = "Springsteen";

        List<EventSearchDTO> actualEvents = eventSearchRMI.searchByString(suchbegriff);

        boolean eventFound = false;
        for(EventSearchDTO event : actualEvents) {

            if(event.getName().equals("Bruce Springsteen Konzert")) {

                eventFound = true;
                break;

            }

        }

        //Test gültig, wenn gilt...
        assertTrue(eventFound);

    }

    @Test
    void wennNachBegriffKleinGesuchtWirdSollBruceSpringsteenKonzertAlsEventGefundenWerden() throws RemoteException {

        EventSearch eventSearchRMI = HibernateService.eventSearchRMI();

        String suchbegriff = "bruce springsteen";

        List<EventSearchDTO> actualEvents = eventSearchRMI.searchByString(suchbegriff);

        boolean eventFound = false;
        for(EventSearchDTO event : actualEvents) {

            if(event.getName().equals("Bruce Springsteen Konzert")) {

                eventFound = true;
                break;

            }

        }

        //Test gültig, wenn gilt...
        assertTrue(eventFound);

    }

    @Test
    void wennNachDatum1682002800GesuchtWirdSollBruceSpringsteenKonzertAlsEventGefundenWerden() throws RemoteException {

        EventSearch eventSearchRMI = HibernateService.eventSearchRMI();

        int sucheDatum = 1682002800;

        List<EventSearchDTO> actualEvents = eventSearchRMI.searchByDate(1682002550, 1683001000);

        boolean eventFound = false;
        for(EventSearchDTO event : actualEvents) {

            if(event.getName().equals("Bruce Springsteen Konzert") && event.getDatum() == sucheDatum) {

                eventFound = true;
                break;

            }

        }

        //Test gültig, wenn gilt...
        assertTrue(eventFound);

    }

    @Test
    void wennNachKategorieKonzertGesuchtWirdSollBruceSpringsteenKonzertAlsEventGefundenWerden() throws RemoteException {

        EventSearch eventSearchRMI = HibernateService.eventSearchRMI();

        String sucheKategorie = "KONZERT";

        List<EventSearchDTO> actualEvents = eventSearchRMI.searchByCategory(sucheKategorie);

        boolean eventFound = false;
        for(EventSearchDTO event : actualEvents) {

            if(event.getName().equals("Bruce Springsteen Konzert")) {

                eventFound = true;
                break;

            }

        }

        //Test gültig, wenn gilt...
        assertTrue(eventFound);

    }

    @Test
    void wennNachBestimmterIDGesuchtWirdSollBruceSpringsteenKonzertAlsEventGefundenWerden() throws RemoteException {

        EventSearch eventSearchRMI = HibernateService.eventSearchRMI();

        int sucheID = 1006;

        EventDescriptionDTO actualEvent = eventSearchRMI.searchById(sucheID);

        //Test gültig, wenn gilt...
        assertEquals("Bruce Springsteen Konzert", actualEvent.getName());
        assertEquals(1006, actualEvent.getEventId());

    }

}