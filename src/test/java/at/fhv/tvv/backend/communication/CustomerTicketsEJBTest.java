package at.fhv.tvv.backend.communication;

import at.fhv.tvv.backend.HibernateService;
import at.fhv.tvv.shared.dto.CustomerInfoDTO;
import at.fhv.tvv.shared.rmi.CustomerTickets;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTicketsEJBTest {

    @Test
    void wennGesuchtNachBestimmerUUIDDannSollKundeAidenSorgGefundenWerden() {

        /* GESUCHTER KUNDE
        Vorname: Aiden
        Nachname: Sorg
        UUID: af3ce120-2059-4533-95d6-883f1567d376
        */

        CustomerTicketsEJB customerTicketsEJB = new CustomerTicketsEJB();

        UUID sucheID = UUID.fromString("af3ce120-2059-4533-95d6-883f1567d376");

        CustomerInfoDTO actualCustomer = customerTicketsEJB.searchById(sucheID);

        //Test gültig, wenn gilt...
        assertEquals("Aiden Sorg", actualCustomer.getName());
    }

    @Test
    void wennUngueltigeUUIDEingegebenWirdSollNullReturntWerden() {

        CustomerTicketsEJB customerTicketsEJB = new CustomerTicketsEJB();

        UUID sucheID = UUID.fromString("abc12345-1234-1234-12a3-123f1234d123");

        CustomerInfoDTO actualCustomer = customerTicketsEJB.searchById(sucheID);

        //Test gültig, wenn gilt...
        assertNull(actualCustomer);
    }

}