package at.fhv.tvv.backend.domain.model.veranstaltungsserie;

import org.junit.jupiter.api.Test;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import static org.junit.jupiter.api.Assertions.*;

class VeranstaltungsserieTest {

    @Test
    void veranstaltungsserieKorrektErstellen() {

        //Testdaten erstellen
        String name = "All Stars";
        String beschreibung = "Meisterstücke aus allen Ecken der Erde - JETZT in ÖSTERREICH!";

        Kategorie kategorie = Kategorie.KONZERT;

        String veranstalter = "KarlAhn-GmbH";

        //Test-Veranstaltungsserie erstellen
        Veranstaltungsserie test = new Veranstaltungsserie(name, beschreibung,kategorie, veranstalter);

        //Test gültig, wenn gilt...
        assertEquals(name, test.getName());
        assertEquals(beschreibung, test.getBeschreibung());
        assertEquals(kategorie, test.getKategorie());
        assertEquals(veranstalter, test.getVeranstalter());
        assertNull(test.getVeranstaltungsserieInternalId()); //Da erst durch DB zugewiesen, was hier ohne DB-Zugriff nicht passiert.

    }

}