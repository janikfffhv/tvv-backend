package at.fhv.tvv.backend;

import at.fhv.tvv.backend.domain.model.event.Event;
import at.fhv.tvv.backend.domain.model.platz.Platz;
import at.fhv.tvv.backend.domain.model.veranstaltungsort.Veranstaltungsort;
import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie;
import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Veranstaltungsserie;
import at.fhv.tvv.backend.domain.model.verkauf.Verkauf;
import at.fhv.tvv.backend.domain.model.verkauf.Zahlungsmethode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class TestData {
    private static final List<Veranstaltungsort> veranstaltungsorte = new ArrayList<>();
    private static final List<Veranstaltungsserie> veranstaltungsserien = new ArrayList<>();
    private static final List<Event> events = new ArrayList<>();

    private static final List<Platz> plaetze = new ArrayList<>();
    public static void main(String[] args) {
        generate();
    }

    public static void generate() {
        Veranstaltungsort dornbirn = new Veranstaltungsort(685056, "Kulturhaus Dornbirn", "Rathausplatz", "1", 6850, "Dornbirn", "Österreich", "Hauptsaal");
        veranstaltungsorte.add(dornbirn);

        Veranstaltungsserie generell = new Veranstaltungsserie("Generelle Serie", "Dies ist eine normale Serie", Kategorie.KONZERT, "TestVeranstalter");
        veranstaltungsserien.add(generell);



        Event event1 = new Event(1001, "Testevent", generell, "Dies ist ein Testevent", 1679443200, dornbirn);

        Verkauf verkauf1 = new Verkauf(UUID.randomUUID(), 10, 0, UUID.randomUUID(), "18:11", Zahlungsmethode.KREDITKARTE, "Tester");

        Platz platz1 = new Platz(101, 101, 1, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 120);
        Platz platz2 = new Platz(102, 102, 2, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 120);
        Platz platz3 = new Platz(103, 103, 3, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 120);
        Platz platz4 = new Platz(104, 104, 4, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 120);
        Platz platz5 = new Platz(105, 105, 5, at.fhv.tvv.backend.domain.model.platz.Kategorie.STEHPLATZ, 120);
        event1.addPlatz(platz1);
        event1.addPlatz(platz2);
        event1.addPlatz(platz3);
        event1.addPlatz(platz4);
        event1.addPlatz(platz5);
        plaetze.add(platz1);
        plaetze.add(platz2);
        plaetze.add(platz3);
        plaetze.add(platz4);
        plaetze.add(platz5);
        events.add(event1);

        verkauf1.addPlatz(platz1);



        EntityManager manager = HibernateService.entityManager();
        manager.getTransaction().begin();

        veranstaltungsorte.forEach(manager::persist);
        veranstaltungsserien.forEach(manager::persist);
        events.forEach(manager::persist);
        plaetze.forEach(manager::persist);
        manager.persist(verkauf1);

        manager.getTransaction().commit();
    }
}
