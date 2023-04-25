package at.fhv.tvv.backend;

import at.fhv.tvv.backend.domain.model.angestellte.Angestellte;
import at.fhv.tvv.backend.domain.model.angestellte.Rolle;
import at.fhv.tvv.backend.domain.model.event.Event;
import at.fhv.tvv.backend.domain.model.platz.Platz;
import at.fhv.tvv.backend.domain.model.veranstaltungsort.Veranstaltungsort;
import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie;
import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Veranstaltungsserie;
import at.fhv.tvv.backend.domain.model.verkauf.Verkauf;
import at.fhv.tvv.backend.domain.model.verkauf.Zahlungsmethode;
import at.fhv.tvv.backend.infrastructure.JMSDurableSubscribers;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class TestData {
    private static final List<Veranstaltungsort> veranstaltungsorte = new ArrayList<>();
    private static final List<Veranstaltungsserie> veranstaltungsserien = new ArrayList<>();
    private static final List<Event> events = new ArrayList<>();

    private static final JMSDurableSubscribers jmsDurableSubscribers = new JMSDurableSubscribers();
    private static final List<Platz> plaetze = new ArrayList<>();
    private static final List<Angestellte> angestellte = new ArrayList<>();
    public static void main(String[] args) {
        generate();
    }

    public static void generate() {
        Veranstaltungsort dornbirn = new Veranstaltungsort(670011, "Remise Bludenz", "Raiffeisenplatz", "1", 6700, "Bludenz", "Österreich", "Hauptsaal");
        veranstaltungsorte.add(dornbirn);

        Veranstaltungsserie generell = new Veranstaltungsserie("Superstars of the world", "Hier könnte Ihre Werbung stehen!", Kategorie.KONZERT, "FH Vorarlberg GmbH");
        veranstaltungsserien.add(generell);



        Event event1 = new Event(1006, "Bruce Springsteen Konzert", generell, "Hier könnte Ihre Werbung stehen!", 1682002800, dornbirn);

        Verkauf verkauf1 = new Verkauf(UUID.randomUUID(), 10, 0, UUID.randomUUID(), "18:11", Zahlungsmethode.KREDITKARTE, "Maxine Musterfrau");

        Platz platz1 = new Platz(401, 401, 1, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 120);
        Platz platz2 = new Platz(402, 402, 2, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 120);
        Platz platz3 = new Platz(403, 403, 3, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 120);
        Platz platz4 = new Platz(404, 404, 4, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 120);
        Platz platz5 = new Platz(405, 405, 5, at.fhv.tvv.backend.domain.model.platz.Kategorie.STEHPLATZ, 120);
        Platz platz6 = new Platz(406, 406, 7, at.fhv.tvv.backend.domain.model.platz.Kategorie.VIP, 430);
        Platz platz7 = new Platz(407, 407, 7, at.fhv.tvv.backend.domain.model.platz.Kategorie.VIP, 430);
        event1.addPlatz(platz1);
        event1.addPlatz(platz2);
        event1.addPlatz(platz3);
        event1.addPlatz(platz4);
        event1.addPlatz(platz5);
        event1.addPlatz(platz6);
        event1.addPlatz(platz7);
        plaetze.add(platz1);
        plaetze.add(platz2);
        plaetze.add(platz3);
        plaetze.add(platz4);
        plaetze.add(platz5);
        plaetze.add(platz6);
        plaetze.add(platz7);
        events.add(event1);

        verkauf1.addPlatz(platz1);

        List<Rolle> rollen = new ArrayList<>();
        rollen.add(Rolle.MITARBEITER);
        rollen.add(Rolle.OPERATOR);
        List<Kategorie> topics = new ArrayList<>();
        topics.add(Kategorie.KINO);
        topics.add(Kategorie.KONZERT);
        topics.add(Kategorie.THEATER);

        Angestellte janik = new Angestellte("Janik", rollen, topics);
        Angestellte dominic = new Angestellte("Dominic", rollen, topics);
        Angestellte nina = new Angestellte("Nina", rollen, topics);
        Angestellte matthias = new Angestellte("Matthias", rollen, topics);
        Angestellte tftest = new Angestellte("tf-test", rollen, topics);
        Angestellte standard = new Angestellte("roles-standard", List.of(Rolle.MITARBEITER), topics);
        Angestellte operator = new Angestellte("roles-operator", List.of(Rolle.OPERATOR), topics);
        Angestellte stundop = new Angestellte("roles-standard-and-operator", rollen, topics);
        angestellte.add(janik);
        angestellte.add(dominic);
        angestellte.add(nina);
        angestellte.add(matthias);
        angestellte.add(tftest);
        angestellte.add(standard);
        angestellte.add(operator);
        angestellte.add(stundop);




        EntityManager manager = HibernateService.entityManager();
        manager.getTransaction().begin();

        veranstaltungsorte.forEach(manager::persist);
        veranstaltungsserien.forEach(manager::persist);
        events.forEach(manager::persist);
        plaetze.forEach(manager::persist);
        angestellte.forEach(manager::persist);
        manager.persist(verkauf1);

        manager.getTransaction().commit();
        jmsDurableSubscribers.createDurableSubscribers(angestellte);
    }
}
