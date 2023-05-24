package at.fhv.tvv.backend.application;

import at.fhv.tvv.backend.HibernateService;
import at.fhv.tvv.backend.domain.model.platz.Kategorie;
import at.fhv.tvv.backend.domain.model.verkauf.Zahlungsmethode;
import at.fhv.tvv.shared.dto.VerkaufDTO;
import at.fhv.tvv.shared.dto.WarenkorbZeileDTO;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class VerkaufImplTest {



    @Test
    void verkaufeTicket() throws RemoteException {
        System.out.println("-------------------------------------Test 1 VerkaufeTicket-------------------------");

        //TestVerkauf erstellen
        UUID kunde = UUID.fromString("af3ce120-2059-4533-95d6-883f1567d376");
        WarenkorbZeileDTO testT1 = new WarenkorbZeileDTO(401, Kategorie.SITZPLATZ.getName(), 1006, "Bruce Springsteen Konzert", 120, "1682002800");
        WarenkorbZeileDTO testT2 = new WarenkorbZeileDTO(402, Kategorie.SITZPLATZ.getName(), 1006, "Bruce Springsteen Konzert", 120, "1682002800" );
        List<WarenkorbZeileDTO> testTickets = new ArrayList<>();
        testTickets.add(testT1);
        testTickets.add(testT2);

        VerkaufImpl verkauf = (VerkaufImpl) HibernateService.verkaufImpl();

        VerkaufDTO verkaufDTO = new VerkaufDTO(100.50F, kunde, Zahlungsmethode.RECHNUNG.getName(), testTickets, "12:08");

        //kaufprozess ausführen
        Boolean testbol = verkauf.kaufe(verkaufDTO);

        //assertEquals(true, testbol);
    }

}