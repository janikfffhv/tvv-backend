package at.fhv.tvv.backend.application;

import at.fhv.tvv.backend.HibernateService;
import at.fhv.tvv.shared.dto.CustomerInfoDTO;
import at.fhv.tvv.shared.dto.CustomerSearchDTO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerSearchTicketsImplTest {

    @Test
    void CustomerSearchTicketInitialisierung() {
        System.out.println("-------------------------------------Test 1 CustomerSearchTicketInitialisierung-------------------------");

        CustomerSearchTicketsImpl customerTicketSearch = HibernateService.customerSearchTickets();

        //Test gültig wenn...
        assertDoesNotThrow(HibernateService::eventSearchImpl);

    }

    @Test
    void FindeCustomerWennNachBestimmterUUIDGesuchtWird() {
        System.out.println("-------------------------------------Test 2 FindeCustomerWennNachBestimmterUUIDGesuchtWird-------------------------");

        boolean ticketFound = false;

        /* GESUCHTER KUNDE
         Vorname: Aiden
         Nachname: Sorg
         UUID: af3ce120-2059-4533-95d6-883f1567d376
         */

        UUID sucheID = UUID.fromString("af3ce120-2059-4533-95d6-883f1567d376");

        CustomerSearchTicketsImpl customerSearchTickets = HibernateService.customerSearchTickets();

        CustomerInfoDTO actualCustomer = customerSearchTickets.searchTickets(sucheID);

        //Test gültig wenn...
        assertEquals(actualCustomer.getName(), "Aiden Sorg");
    }

}