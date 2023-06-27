package at.fhv.tvv.backend.application;

import at.fhv.tvv.backend.domain.model.platz.Platz;
import at.fhv.tvv.backend.domain.repository.EventRepository;
import at.fhv.tvv.backend.infrastructure.EventRepositoryImpl;
import at.fhv.tvv.shared.dto.CustomerEventDTO;
import at.fhv.tvv.shared.dto.CustomerInfoDTO;
import at.fhv.tvv.shared.dto.VerkaufDTO;
import at.fhv.tvv.shared.dto.WarenkorbZeileDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerSearchTicketsImplTest {

    @Test
    void ticketsVonKundeSuchen() {

        CustomerSearchTicketsImpl constructorTest = new CustomerSearchTicketsImpl();

        //TEST-KUNDE
        String uuid = "0c28d094-4c31-4f05-ad74-2d97af7aaf04"; //Erva Grewe

        List<CustomerEventDTO> expectedEvents = new ArrayList<>();
        CustomerEventDTO customerEventDTO = new CustomerEventDTO(UUID.fromString("be39dd8e-06b7-440d-b0b8-d18ffcd8be2f"), "10:55", "BAR", 11.95F);
        expectedEvents.add(customerEventDTO);

        //MOCKING
        EventRepository eventRepositoryMock = Mockito.mock(EventRepositoryImpl.class);
        CustomerSearchTicketsImpl customerSearchTicketsImpl = new CustomerSearchTicketsImpl(eventRepositoryMock);

        Mockito.when(eventRepositoryMock.getCustomerTickets(UUID.fromString(uuid))).thenReturn(expectedEvents);

        //TICKETSUCHE AUSFÜHREN
        CustomerInfoDTO customerInfo = customerSearchTicketsImpl.searchById(UUID.fromString(uuid));

        //TEST GÜLTIG, WENN GILT...
        assertEquals("Erva Grewe", customerInfo.getName());
        assertEquals(expectedEvents, customerInfo.getTickets());

    }

    @Test
    void wennNachNichtExistierendemKundeGesuchtWirdSollNichtsGefundenWerden() {


        //TEST-KUNDE
        String uuid = "1a23b456-4c31-4f05-ad74-2d97af7aaf04"; //Nicht-existierenden Kunde

        List<CustomerEventDTO> expectedEvents = new ArrayList<>();
        CustomerEventDTO customerEventDTO = new CustomerEventDTO(UUID.fromString("be39dd8e-06b7-440d-b0b8-d18ffcd8be2f"), "10:55", "BAR", 11.95F);
        expectedEvents.add(customerEventDTO);

        //MOCKING
        EventRepository eventRepositoryMock = Mockito.mock(EventRepositoryImpl.class);
        CustomerSearchTicketsImpl customerSearchTicketsImpl = new CustomerSearchTicketsImpl(eventRepositoryMock);

        Mockito.when(eventRepositoryMock.getCustomerTickets(UUID.fromString(uuid))).thenReturn(expectedEvents);

        //TICKETSUCHE AUSFÜHREN
        CustomerInfoDTO customerInfo = customerSearchTicketsImpl.searchById(UUID.fromString(uuid));

        //TEST GÜLTIG, WENN GILT...
        assertNull(customerInfo);

    }

}