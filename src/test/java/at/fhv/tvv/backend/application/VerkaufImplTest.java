package at.fhv.tvv.backend.application;

import at.fhv.tvv.backend.domain.model.platz.Kategorie;
import at.fhv.tvv.backend.domain.model.platz.Platz;
import at.fhv.tvv.backend.domain.model.verkauf.Verkauf;
import at.fhv.tvv.backend.domain.repository.EventRepository;
import at.fhv.tvv.backend.infrastructure.EventRepositoryImpl;
import at.fhv.tvv.shared.dto.VerkaufDTO;
import at.fhv.tvv.shared.dto.WarenkorbZeileDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class VerkaufImplTest {

    @Test
    void verkaufImplKorrektInitialisieren() {

        VerkaufImpl test = new VerkaufImpl();

        //Test gültig, wenn gilt...
        assertEquals(VerkaufImpl.class, test.getClass());

    }

    @Test
    void ticketkaufAufFunktionalitaetTesten() {

        //MOCKING
        EventRepository eventRepositoryMock = Mockito.mock(EventRepositoryImpl.class);
        VerkaufImpl verkaufImpl = new VerkaufImpl(eventRepositoryMock);

        //TEST-VERKAUF ERSTELLEN mit Kunde Josie Stöber
        int datum = (int) (new Date("21/10/2023").getTime()/1000);

        List<WarenkorbZeileDTO> warenkorb = new ArrayList<>();
        WarenkorbZeileDTO platz = new WarenkorbZeileDTO(807, "STEHPLATZ", 1013, "Theater Patati-Patata präsentiert: Alex und die gelbe Maus", 11.99F, Integer.toString(datum));
        warenkorb.add(platz);

        VerkaufDTO verkaufDTO = new VerkaufDTO(200, UUID.fromString("de5cc747-d471-44a1-9b37-784c81e704e5"), "RECHNUNG", warenkorb, "11:14");

        Platz actualPlatz = new Platz(807, 807, 7, Kategorie.STEHPLATZ, 11.99F);

        Mockito.when(eventRepositoryMock.searchByPlatzId(platz.getPlatzId())).thenReturn(actualPlatz);
        Verkauf verkauf = null;
        Mockito.doNothing().when(eventRepositoryMock).purchase(verkauf);

        //VERKAUF AUSFÜHREN
        boolean test = verkaufImpl.kaufe(verkaufDTO);

        //TEST GÜLTIG, WENN GILT...
        assertTrue(test);

    }

}