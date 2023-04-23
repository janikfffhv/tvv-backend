package at.fhv.tvv.backend.domain.model.event;

import at.fhv.tvv.backend.domain.model.platz.Platz;
import at.fhv.tvv.backend.domain.model.veranstaltungsort.Veranstaltungsort;
import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie;
import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Veranstaltungsserie;
import org.junit.jupiter.api.Test;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void korrekteEventErstellung() {

        //Testdaten erstellen
        int eventId = 16750;

        String name = "Neues Testevent 2023";

        Veranstaltungsserie veranstaltungsserie = new Veranstaltungsserie("Superstars of the world", "Hier könnte Ihre Werbung stehen!", Kategorie.KONZERT, "FH Vorarlberg GmbH");;

        String beschreibung = "Hier ist ein Text.";

        int datum = 1682002800;

        Veranstaltungsort veranstaltungsort = new Veranstaltungsort(670011, "Remise Bludenz", "Raiffeisenplatz", "1", 6700, "Bludenz", "Österreich", "Hauptsaal");;

        List<Platz> plaetze = new ArrayList<>();

        //Test-Event erstellen
        Event test = new Event(eventId, name, veranstaltungsserie, beschreibung, datum, veranstaltungsort);

        //Test gültig, wenn...
        assertEquals(eventId, test.getEventId());
        assertEquals(name, test.getName());
        assertEquals(veranstaltungsserie, test.getVeranstaltungsserie());
        assertEquals(beschreibung, test.getBeschreibung());
        assertEquals(datum, test.getDatum());
        assertEquals(veranstaltungsort, test.getVeranstaltungsort());
        assertNotNull(test.getPlaetze());
        assertThrows(IndexOutOfBoundsException.class, () -> test.getPlaetze().get(0));
        assertNull(test.getEventInternalId()); //Da erst durch DB zugewiesen, was hier ohne DB-Zugriff nicht passiert.

    }

    @Test
    void platzZuPlaetzeListeVonEventHinzufuegen() {

        //Testdaten erstellen
        int eventId = 16750;

        String name = "Neues Testevent 2023";

        Veranstaltungsserie veranstaltungsserie = new Veranstaltungsserie("Superstars of the world", "Hier könnte Ihre Werbung stehen!", Kategorie.KONZERT, "FH Vorarlberg GmbH");;

        String beschreibung = "Hier ist ein Text.";

        int datum = 1682002800;

        Veranstaltungsort veranstaltungsort = new Veranstaltungsort(670011, "Remise Bludenz", "Raiffeisenplatz", "1", 6700, "Bludenz", "Österreich", "Hauptsaal");;

        List<Platz> plaetze = new ArrayList<>();
        Platz platz1 = new Platz(54, 4, 5, at.fhv.tvv.backend.domain.model.platz.Kategorie.STEHPLATZ, 10);
        Platz platz2 = new Platz(13, 3, 1, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 15);

        //Test-Event erstellen
        Event test = new Event(eventId, name, veranstaltungsserie, beschreibung, datum, veranstaltungsort);

        test.addPlatz(platz1);
        test.addPlatz(platz2);

        //Test gültig, wenn...
        assertEquals(platz1, test.getPlaetze().get(0));
        assertEquals(test, test.getPlaetze().get(0).getEvent());
        assertEquals(platz2, test.getPlaetze().get(1));
        assertEquals(test, test.getPlaetze().get(1).getEvent());


    }

}