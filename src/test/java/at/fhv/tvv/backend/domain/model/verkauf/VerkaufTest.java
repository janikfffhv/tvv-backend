package at.fhv.tvv.backend.domain.model.verkauf;

import at.fhv.tvv.backend.domain.model.platz.Kategorie;
import at.fhv.tvv.backend.domain.model.platz.Platz;
import org.junit.jupiter.api.Test;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class VerkaufTest {

    @Test
    void verkaufKorrektErstellen() {

        //Testdaten erstellen
        UUID verkaufsId = UUID.randomUUID();

        float gesamtpreis = 11.95F;

        float erstattungsbetrag = 0;

        List<Platz> plaetze = new ArrayList<>();

        UUID kundenId = UUID.randomUUID();

        String verkaufszeit = "17:38";

        Zahlungsmethode zahlungsmethode = Zahlungsmethode.RECHNUNG;

        String angestellter = "Kurt Heuchler";

        //Test-Verkauf erstellen
        Verkauf test = new Verkauf(verkaufsId, gesamtpreis, erstattungsbetrag, kundenId, verkaufszeit, zahlungsmethode, angestellter);

        //Test gültig, wenn gilt...
        assertEquals(verkaufsId, test.getVerkaufsId());
        assertEquals(gesamtpreis, test.getGesamtpreis());
        assertEquals(erstattungsbetrag, test.getErstattungsbetrag());
        assertNotNull(test.getPlaetze());
        assertThrows(IndexOutOfBoundsException.class, () -> test.getPlaetze().get(0));
        assertEquals(kundenId, test.getKundenId());
        assertEquals(verkaufszeit, test.getVerkaufszeit());
        assertEquals(zahlungsmethode, test.getZahlungsmethode());
        assertEquals(angestellter, test.getAngestellter());
        assertNull(test.getVerkaufsInternalId()); //Da erst durch DB zugewiesen, was hier ohne DB-Zugriff nicht passiert.

    }

    @Test
    void platzZuPlaetzeListeVonVerkaufHinzufuegen() {

        //Testdaten erstellen
        UUID verkaufsId = UUID.randomUUID();

        float gesamtpreis = 11.95F;

        float erstattungsbetrag = 0;


        List<Platz> plaetze = new ArrayList<>();
        Platz platz1 = new Platz(14, 4, 1, Kategorie.STEHPLATZ, 4);
        Platz platz2 = new Platz(55, 5, 5, Kategorie.VIP, 7.95F);

        UUID kundenId = UUID.randomUUID();

        String verkaufszeit = "17:38";

        Zahlungsmethode zahlungsmethode = Zahlungsmethode.RECHNUNG;

        String angestellter = "Kurt Heuchler";

        //Test-Verkauf erstellen
        Verkauf test = new Verkauf(verkaufsId, gesamtpreis, erstattungsbetrag, kundenId, verkaufszeit, zahlungsmethode, angestellter);
        test.addPlatz(platz1);
        test.addPlatz(platz2);

        //Test gültig, wenn gilt...
        assertEquals(platz1, test.getPlaetze().get(0));
        assertEquals(test, test.getPlaetze().get(0).getVerkauf());
        assertEquals(platz2, test.getPlaetze().get(1));
        assertEquals(test, test.getPlaetze().get(1).getVerkauf());

    }

}