package at.fhv.tvv.backend.domain.model.veranstaltungsort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VeranstaltungsortTest {

    @Test
    void veranstaltungsortKorrektErstellen() {

        //Testdaten erstellen
        int ortId = 12345;

        String gebaeude = "Musterhaus Bremen";

        String strasse = "Lauserstraße";

        String hausnummer = "34";

        int plz = 6850;

        String ort = "Dornbirn";

        String land = "Österreich";

        String raum = "Raum 204";

        //Test-Veranstaltungsort erstellen
        Veranstaltungsort test = new Veranstaltungsort(ortId, gebaeude, strasse, hausnummer, plz, ort, land, raum);


        //Test gültig, wenn gilt...
        assertEquals(ortId, test.getOrtId());
        assertEquals(gebaeude, test.getGebaeude());
        assertEquals(strasse, test.getStrasse());
        assertEquals(hausnummer, test.getHausnummer());
        assertEquals(plz, test.getPlz());
        assertEquals(ort, test.getOrt());
        assertEquals(land, test.getLand());
        assertEquals(raum, test.getRaum());
        assertNull(test.getVeranstaltungsortIdInternal()); //Da erst durch DB zugewiesen, was hier ohne DB-Zugriff nicht passiert.


    }

}