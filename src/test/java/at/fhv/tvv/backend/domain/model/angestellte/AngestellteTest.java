package at.fhv.tvv.backend.domain.model.angestellte;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AngestellteTest {

    //Test für Angestellten-Objekt-Erstellung
    @Test
    void korrekteAngestelltenErstellung() {

        //Testdaten für Angestellten
        String benutzername = "AlfrMust";

        UUID passwort = UUID.fromString("5fc03087-d265-11e7-b8c6-83e29cd24f4c");

        String vorname = "Alfredo";

        String nachname = "Mustermann";

        String rolle = "Operator";

        //Angestellten aus Testdaten erzeugen
        Angestellte test = new Angestellte(benutzername, passwort, vorname, nachname, rolle);

        //Test gültig, wenn...
        assertEquals(benutzername, test.getBenutzername());
        assertEquals(passwort, test.getPasswort());
        assertEquals(vorname, test.getVorname());
        assertEquals(nachname, test.getNachname());
        assertEquals(rolle, test.getRolle());
        assertNull(test.getAngestellteInternalId()); //Da erst durch DB zugewiesen, was hier ohne DB-Zugriff nicht passiert.

    }

}