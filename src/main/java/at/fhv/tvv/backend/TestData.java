package at.fhv.tvv.backend;

import at.fhv.tvv.backend.domain.model.event.Event;
import at.fhv.tvv.backend.domain.model.veranstaltungsort.Veranstaltungsort;
import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie;
import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Veranstaltungsserie;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


public class TestData {
    private static final List<Veranstaltungsort> veranstaltungsorte = new ArrayList<>();
    private static final List<Veranstaltungsserie> veranstaltungsserien = new ArrayList<>();
    private static final List<Event> events = new ArrayList<>();
    public static void main(String[] args) {
        generate();
    }

    public static void generate() {
        Veranstaltungsort dornbirn = new Veranstaltungsort(685056, "Kulturhaus Dornbirn", "Rathausplatz", "1", 6850, "Dornbirn", "Österreich", "Hauptsaal");
        veranstaltungsorte.add(dornbirn);

        Veranstaltungsserie generell = new Veranstaltungsserie("Generelle Serie", "Dies ist eine normale Serie", Kategorie.KONZERT, "TestVeranstalter");
        veranstaltungsserien.add(generell);

        Event event1 = new Event(1001, "Testevent", generell, "Dies ist ein Testevent", 1679443200, dornbirn, 100);
        events.add(event1);

        EntityManager manager = HibernateService.entityManager();
        manager.getTransaction().begin();

        veranstaltungsorte.forEach(manager::persist);
        veranstaltungsserien.forEach(manager::persist);
        events.forEach(manager::persist);

        manager.getTransaction().commit();
    }
}
