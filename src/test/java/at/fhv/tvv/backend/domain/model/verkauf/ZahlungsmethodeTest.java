package at.fhv.tvv.backend.domain.model.verkauf;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZahlungsmethodeTest {

    @Test
    void barZahlungsmethodeErstellen() {

        //Test-Zahlungsmethode erstellen
        Zahlungsmethode test = Zahlungsmethode.BAR;

        //Test gültig, wenn gilt...
        assertEquals("BAR", test.getName());

    }

    @Test
    void kreditkarteZahlungsmethodeErstellen() {

        //Test-Zahlungsmethode erstellen
        Zahlungsmethode test = Zahlungsmethode.KREDITKARTE;

        //Test gültig, wenn gilt...
        assertEquals("KREDITKARTE", test.getName());

    }

    @Test
    void rechnungZahlungsmethodeErstellen() {

        //Test-Zahlungsmethode erstellen
        Zahlungsmethode test = Zahlungsmethode.RECHNUNG;

        //Test gültig, wenn gilt...
        assertEquals("RECHNUNG", test.getName());

    }

}