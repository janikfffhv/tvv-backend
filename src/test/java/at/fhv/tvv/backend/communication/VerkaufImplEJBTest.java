package at.fhv.tvv.backend.communication;

import at.fhv.tvv.backend.HibernateService;
import at.fhv.tvv.backend.application.VerkaufImpl;
import at.fhv.tvv.backend.domain.model.platz.Kategorie;
import at.fhv.tvv.backend.domain.model.verkauf.Zahlungsmethode;
import at.fhv.tvv.shared.dto.VerkaufDTO;
import at.fhv.tvv.shared.dto.WarenkorbZeileDTO;
import at.fhv.tvv.shared.rmi.Verkauf;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class VerkaufImplEJBTest {

    @Test
    void verkaufeTicket() {

        //Test-Verkauf erstellen
        UUID kunde = UUID.fromString("af3ce120-2059-4533-95d6-883f1567d376");
        WarenkorbZeileDTO testTicket1 = new WarenkorbZeileDTO(401, Kategorie.SITZPLATZ.getName(), 1006, "Bruce Springsteen Konzert", 120, "1682002800");
        WarenkorbZeileDTO testTicket2 = new WarenkorbZeileDTO(402, Kategorie.SITZPLATZ.getName(), 1006, "Bruce Springsteen Konzert", 120, "1682002800");
        List<WarenkorbZeileDTO> testTickets = new ArrayList<>();
        testTickets.add(testTicket1);
        testTickets.add(testTicket2);

        VerkaufImplEJB verkaufImplEJB = new VerkaufImplEJB();
        VerkaufImplEJB verkauf = Mockito.mock(VerkaufImplEJB.class);

        VerkaufDTO verkaufDTO = new VerkaufDTO(100.50F, kunde, Zahlungsmethode.RECHNUNG.getName(), testTickets, "19:08");

        when(verkauf.kaufe(verkaufDTO)).thenReturn(new VerkaufImpl().kaufe(verkaufDTO));

        boolean kaufe = verkauf.kaufe(verkaufDTO);

        //Kaufprozess ausführen
        verkaufImplEJB.kaufe(verkaufDTO);

        assertTrue(kaufe);

    }

}