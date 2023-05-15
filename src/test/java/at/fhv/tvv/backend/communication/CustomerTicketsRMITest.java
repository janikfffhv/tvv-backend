package at.fhv.tvv.backend.communication;

import at.fhv.tvv.backend.HibernateService;
import at.fhv.tvv.shared.dto.CustomerInfoDTO;
import at.fhv.tvv.shared.rmi.CustomerTickets;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTicketsRMITest {

    @Test
    void CustomerTicketsRMIKorrektInitialisieren() throws RemoteException {

        CustomerTickets customerTicketsRMI = HibernateService.customerTicketsRMI();

        //Test gültig, wenn gilt...
        assertDoesNotThrow(HibernateService::customerTicketsRMI);

    }

    @Test
    void wennGesuchtNachBestimmerUUIDDannSollKundeAidenSorgGefundenWerden() throws RemoteException {

        /* GESUCHTER KUNDE
        Vorname: Aiden
        Nachname: Sorg
        UUID: af3ce120-2059-4533-95d6-883f1567d376
         */

        UUID sucheID = UUID.fromString("af3ce120-2059-4533-95d6-883f1567d376");

        CustomerTickets customerTicketsRMI = HibernateService.customerTicketsRMI();

        CustomerInfoDTO actualCustomer = customerTicketsRMI.searchById(sucheID);

        //Test gültig, wenn gilt...
        assertEquals(actualCustomer.getName(), "Aiden Sorg");
    }

}