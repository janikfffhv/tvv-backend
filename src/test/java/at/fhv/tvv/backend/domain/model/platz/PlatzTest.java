package at.fhv.tvv.backend.domain.model.platz;

import at.fhv.tvv.backend.domain.model.event.Event;
import at.fhv.tvv.backend.domain.model.veranstaltungsort.Veranstaltungsort;
import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Veranstaltungsserie;
import at.fhv.tvv.backend.domain.model.verkauf.Verkauf;
import at.fhv.tvv.backend.domain.model.verkauf.Zahlungsmethode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.junit.jupiter.api.Test;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PlatzTest {

    @Test
    void korrektePlatzErstellung() {

        //Testdaten erstellen
        int platzId = 14;

        int nummer = 4;

        int reihe = 1;

        Kategorie kategorie = Kategorie.SITZPLATZ;

        float preis = 9.99F;

        Veranstaltungsserie generell = new Veranstaltungsserie("Superstars of the world", "Hier könnte Ihre Werbung stehen!", at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie.KONZERT, "FH Vorarlberg GmbH");
        Veranstaltungsort dornbirn = new Veranstaltungsort(670011, "Remise Bludenz", "Raiffeisenplatz", "1", 6700, "Bludenz", "Österreich", "Hauptsaal");
        Event event = new Event(1006, "Bruce Springsteen Konzert", generell, "Hier könnte Ihre Werbung stehen!", 1682002800, dornbirn);

        Verkauf verkauf = new Verkauf(UUID.randomUUID(), 9.99F, 0, UUID.randomUUID(), "18:11", Zahlungsmethode.KREDITKARTE, "Maxine Musterfrau");

        //Test-Platz erstellen
        Platz test = new Platz(platzId, nummer, reihe, kategorie, preis);
        test.setEvent(event);
        test.setVerkauf(verkauf);

        //Test gültig, wenn...
        assertEquals(platzId, test.getPlatzId());
        assertEquals(nummer, test.getNummer());
        assertEquals(reihe, test.getReihe());
        assertEquals(kategorie, test.getKategorie());
        assertEquals(preis, test.getPreis());

        assertNotNull(test.getVerkauf());
        assertNotNull(test.getEvent());
        assertEquals(generell, test.getEvent().getVeranstaltungsserie());
        assertEquals(dornbirn, test.getEvent().getVeranstaltungsort());
        assertEquals("Bruce Springsteen Konzert", test.getEvent().getName());
        assertEquals(9.99F, test.getVerkauf().getGesamtpreis());
        assertNull(test.getPlatzInternalId()); //Da erst durch DB zugewiesen, was hier ohne DB-Zugriff nicht passiert.

    }

}