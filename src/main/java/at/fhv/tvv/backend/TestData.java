package at.fhv.tvv.backend;

import at.fhv.tvv.backend.application.MessageProducerImpl;
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

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.JMSException;
import javax.persistence.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Startup
@Singleton
public class TestData {
    private static final List<Veranstaltungsort> veranstaltungsorte = new ArrayList<>();
    private static final List<Veranstaltungsserie> veranstaltungsserien = new ArrayList<>();
    private static final List<Event> events = new ArrayList<>();

    private static final JMSDurableSubscribers jmsDurableSubscribers = new JMSDurableSubscribers();
    private static final MessageProducerImpl messageProducerImpl = new MessageProducerImpl();

    private static final List<Platz> plaetze = new ArrayList<>();
    private static final List<Angestellte> angestellte = new ArrayList<>();
    public static void main(String[] args) throws RemoteException, JMSException {
        generate();
    }

    public static void generate() throws RemoteException, JMSException {

        //Veranstaltungsorte
        Veranstaltungsort bludenz1 = new Veranstaltungsort(670011, "Remise Bludenz", "Raiffeisenplatz", "1", 6700, "Bludenz", "Österreich", "Hauptsaal");
        veranstaltungsorte.add(bludenz1);

        Veranstaltungsort feldkirch1 = new Veranstaltungsort(670012, "Feldkirch Kulturhaus", "Marktplatz", "4", 6800, "Feldkirch", "Österreich", "Kultursaal");
        veranstaltungsorte.add(feldkirch1);
        Veranstaltungsort feldkirch2 = new Veranstaltungsort(670013, "Theater am Saumarkt", "Muehletorpl", "1", 6800, "Feldkirch", "Österreich", "Saal2");
        veranstaltungsorte.add(feldkirch2);

        Veranstaltungsort hohenems1 = new Veranstaltungsort(670014, "Cineplexx Hohenems", "Lustenauer Str.", "112", 6845, "Hohenems", "Österreich", "IMAX");
        veranstaltungsorte.add(hohenems1);

        Veranstaltungsort dornbirn1 = new Veranstaltungsort(670015, "FH Dornbirn", "Hochschulstraße", "1", 6850, "Dornbirn", "Österreich", "U314");
        veranstaltungsorte.add(dornbirn1);

        Veranstaltungsort bregenz1 = new Veranstaltungsort(670016, "Festspielhaus Bregenz", "Platz d. Wr. Symphoniker", "1", 6900, "Bregenz", "Österreich", "Festspielhaus - Großer Saal");
        veranstaltungsorte.add(bregenz1);


        //Veranstaltungsserien
        Veranstaltungsserie sotw = new Veranstaltungsserie("Superstars of the world", "Hier könnte Ihre Werbung stehen!", Kategorie.KONZERT, "FH Vorarlberg GmbH");
        veranstaltungsserien.add(sotw);
        Veranstaltungsserie mc = new Veranstaltungsserie("Mozart-Classics", "Die glorreichsten Mozart-Stuecke jetzt in Feldkirch!", Kategorie.KONZERT, "Musica AG");
        veranstaltungsserien.add(mc);
        Veranstaltungsserie vnv = new Veranstaltungsserie("Romantik-2023", "Die Serie der Romantischen Komoedien 2023.", Kategorie.THEATER, "Saumarkt GmbH");
        veranstaltungsserien.add(vnv);
        Veranstaltungsserie dad = new Veranstaltungsserie("Dungeons & Dragons: Ehre unter Dieben", "Basierend auf dem beliebten Fantasy-Rollenspiel begibt sich eine Gruppe von Abenteurern auf einen epischen Raubzug, um ein verlorenes Relikt zu stehlen.", Kategorie.KINO, "Constantin Film");
        veranstaltungsserien.add(dad);
        Veranstaltungsserie fhd = new Veranstaltungsserie("FH Movies", "Klassische Vorlesungen neu definiert.", Kategorie.KINO, "FH Dornbirn GmbH");
        veranstaltungsserien.add(fhd);
        Veranstaltungsserie zfk = new Veranstaltungsserie("Festspiele 2023", "Unsere Eventserie 2023", Kategorie.THEATER, "Bregenzer Festspiele");
        veranstaltungsserien.add(zfk);


        //Events
        int datum1 = (int) (new Date().getTime()/1000);
        int datum2 = (int) (new Date("31/12/2023").getTime()/1000);
        int datum3 = (int) (new Date("21/05/2023").getTime()/1000);
        int datum4 = (int) (new Date("24/05/2023").getTime()/1000);
        Event event1 = new Event(1006, "Bruce Springsteen Konzert", sotw, "Hier könnte Ihre Werbung stehen!", 1682002800, bludenz1);
        Event event2 = new Event(1007, "Requiem", mc, "Requiem - Das klassische Stueck aufgefuehrt in Feldkirch!", datum1, feldkirch1);
        Event event3 = new Event(1008, "404 - Die Fehler in unserer Realitaet", vnv, "Die kurioseste IT-verseuchteste romantische-Komoedie ueberhaupt!", datum2, feldkirch2);
        Event event4 = new Event(1009, "Dungeons & Dragons: Ehre unter Dieben", dad, "Kinovorstellung Nr. 1 in Hohenems", datum3, hohenems1);
        Event event5 = new Event(1010, "Enterprise Applikationen - The Movie", fhd, "Viele Vorlesungen - EIN GANZER FILM.", datum1, dornbirn1);
        Event event6 = new Event(1011, "Die Zauberfloete fuer Kinder", zfk, "Ein Klassiker neu aufgefuehrt!", datum4, bregenz1);


        //Verkauf
        Verkauf verkauf1 = new Verkauf(UUID.randomUUID(), 10, 0, UUID.randomUUID(), "18:11", Zahlungsmethode.KREDITKARTE, "Maxine Musterfrau");
        Verkauf verkauf2 = new Verkauf(UUID.randomUUID(), 8.99F, 0, UUID.fromString("af3ce120-2059-4533-95d6-883f1567d376"), "12:57", Zahlungsmethode.BAR, "Maxine Musterfrau"); //Kunde: Aiden Sorg
        Verkauf verkauf3 = new Verkauf(UUID.randomUUID(), 34.95F, 0, UUID.fromString("1e342519-9e5e-43f6-8636-60c4ceee4a91"), "09:23", Zahlungsmethode.RECHNUNG, "Herbert Walter"); //Kunde: Maria Röhl
        Verkauf verkauf4 = new Verkauf(UUID.randomUUID(), 15.99F, 0, UUID.fromString("1e342519-9e5e-43f6-8636-60c4ceee4a91"), "10:34", Zahlungsmethode.RECHNUNG, "Herbert Walter"); //Kunde: Maria Röhl
        Verkauf verkauf5 = new Verkauf(UUID.randomUUID(), 12.95F, 0, UUID.fromString("1e342519-9e5e-43f6-8636-60c4ceee4a91"), "16:16", Zahlungsmethode.KREDITKARTE, "Maxine Musterfrau"); //Kunde: Maria Röhl
        Verkauf verkauf6 = new Verkauf(UUID.randomUUID(), 20F, 0, UUID.fromString("af3ce120-2059-4533-95d6-883f1567d376"), "13:20", Zahlungsmethode.BAR, "Max Mustermann"); //Kunde: Aiden Sorg

        //Event-Plätze
        Platz ev1platz1 = new Platz(101, 101, 1, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 120);
        Platz ev1platz2 = new Platz(102, 102, 2, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 120);
        Platz ev1platz3 = new Platz(103, 103, 3, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 120);
        Platz ev1platz4 = new Platz(104, 104, 4, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 120);
        Platz ev1platz5 = new Platz(105, 105, 5, at.fhv.tvv.backend.domain.model.platz.Kategorie.STEHPLATZ, 100);
        Platz ev1platz6 = new Platz(106, 106, 6, at.fhv.tvv.backend.domain.model.platz.Kategorie.VIP, 430);
        Platz ev1platz7 = new Platz(107, 107, 7, at.fhv.tvv.backend.domain.model.platz.Kategorie.VIP, 430);
        event1.addPlatz(ev1platz1);
        event1.addPlatz(ev1platz2);
        event1.addPlatz(ev1platz3);
        event1.addPlatz(ev1platz4);
        event1.addPlatz(ev1platz5);
        event1.addPlatz(ev1platz6);
        event1.addPlatz(ev1platz7);
        plaetze.add(ev1platz1);
        plaetze.add(ev1platz2);
        plaetze.add(ev1platz3);
        plaetze.add(ev1platz4);
        plaetze.add(ev1platz5);
        plaetze.add(ev1platz6);
        plaetze.add(ev1platz7);
        events.add(event1);

        Platz ev2platz1 = new Platz(201, 201, 1, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 120);
        Platz ev2platz2 = new Platz(202, 202, 2, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 120);
        Platz ev2platz3 = new Platz(203, 203, 3, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 120);
        Platz ev2platz4 = new Platz(204, 204, 4, at.fhv.tvv.backend.domain.model.platz.Kategorie.STEHPLATZ, 100);
        Platz ev2platz5 = new Platz(205, 205, 5, at.fhv.tvv.backend.domain.model.platz.Kategorie.STEHPLATZ, 100);
        Platz ev2platz6 = new Platz(206, 206, 6, at.fhv.tvv.backend.domain.model.platz.Kategorie.VIP, 430);
        Platz ev2platz7 = new Platz(207, 207, 7, at.fhv.tvv.backend.domain.model.platz.Kategorie.VIP, 430);
        event2.addPlatz(ev2platz1);
        event2.addPlatz(ev2platz2);
        event2.addPlatz(ev2platz3);
        event2.addPlatz(ev2platz4);
        event2.addPlatz(ev2platz5);
        event2.addPlatz(ev2platz6);
        event2.addPlatz(ev2platz7);
        plaetze.add(ev2platz1);
        plaetze.add(ev2platz2);
        plaetze.add(ev2platz3);
        plaetze.add(ev2platz4);
        plaetze.add(ev2platz5);
        plaetze.add(ev2platz6);
        plaetze.add(ev2platz7);
        events.add(event2);

        Platz ev3platz1 = new Platz(301, 301, 1, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 120);
        Platz ev3platz2 = new Platz(302, 302, 2, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 120);
        Platz ev3platz3 = new Platz(303, 303, 3, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 120);
        Platz ev3platz4 = new Platz(304, 304, 4, at.fhv.tvv.backend.domain.model.platz.Kategorie.STEHPLATZ, 100);
        Platz ev3platz5 = new Platz(305, 305, 5, at.fhv.tvv.backend.domain.model.platz.Kategorie.STEHPLATZ, 100);
        Platz ev3platz6 = new Platz(306, 306, 6, at.fhv.tvv.backend.domain.model.platz.Kategorie.VIP, 430);
        Platz ev3platz7 = new Platz(307, 307, 7, at.fhv.tvv.backend.domain.model.platz.Kategorie.VIP, 430);
        event3.addPlatz(ev3platz1);
        event3.addPlatz(ev3platz2);
        event3.addPlatz(ev3platz3);
        event3.addPlatz(ev3platz4);
        event3.addPlatz(ev3platz5);
        event3.addPlatz(ev3platz6);
        event3.addPlatz(ev3platz7);
        plaetze.add(ev3platz1);
        plaetze.add(ev3platz2);
        plaetze.add(ev3platz3);
        plaetze.add(ev3platz4);
        plaetze.add(ev3platz5);
        plaetze.add(ev3platz6);
        plaetze.add(ev3platz7);
        events.add(event3);

        Platz ev4platz1 = new Platz(401, 401, 1, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 120);
        Platz ev4platz2 = new Platz(402, 402, 2, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 120);
        Platz ev4platz3 = new Platz(403, 403, 3, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 120);
        Platz ev4platz4 = new Platz(404, 404, 4, at.fhv.tvv.backend.domain.model.platz.Kategorie.STEHPLATZ, 100);
        Platz ev4platz5 = new Platz(405, 405, 5, at.fhv.tvv.backend.domain.model.platz.Kategorie.STEHPLATZ, 100);
        Platz ev4platz6 = new Platz(406, 406, 6, at.fhv.tvv.backend.domain.model.platz.Kategorie.VIP, 430);
        Platz ev4platz7 = new Platz(407, 407, 7, at.fhv.tvv.backend.domain.model.platz.Kategorie.VIP, 430);
        event4.addPlatz(ev4platz1);
        event4.addPlatz(ev4platz2);
        event4.addPlatz(ev4platz3);
        event4.addPlatz(ev4platz4);
        event4.addPlatz(ev4platz5);
        event4.addPlatz(ev4platz6);
        event4.addPlatz(ev4platz7);
        plaetze.add(ev4platz1);
        plaetze.add(ev4platz2);
        plaetze.add(ev4platz3);
        plaetze.add(ev4platz4);
        plaetze.add(ev4platz5);
        plaetze.add(ev4platz6);
        plaetze.add(ev4platz7);
        events.add(event4);

        Platz ev5platz1 = new Platz(501, 501, 1, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 120);
        Platz ev5platz2 = new Platz(502, 502, 2, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 120);
        Platz ev5platz3 = new Platz(503, 503, 3, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 120);
        Platz ev5platz4 = new Platz(504, 504, 4, at.fhv.tvv.backend.domain.model.platz.Kategorie.STEHPLATZ, 100);
        Platz ev5platz5 = new Platz(505, 505, 5, at.fhv.tvv.backend.domain.model.platz.Kategorie.STEHPLATZ, 100);
        Platz ev5platz6 = new Platz(506, 506, 6, at.fhv.tvv.backend.domain.model.platz.Kategorie.VIP, 430);
        Platz ev5platz7 = new Platz(507, 507, 7, at.fhv.tvv.backend.domain.model.platz.Kategorie.VIP, 430);
        event5.addPlatz(ev5platz1);
        event5.addPlatz(ev5platz2);
        event5.addPlatz(ev5platz3);
        event5.addPlatz(ev5platz4);
        event5.addPlatz(ev5platz5);
        event5.addPlatz(ev5platz6);
        event5.addPlatz(ev5platz7);
        plaetze.add(ev5platz1);
        plaetze.add(ev5platz2);
        plaetze.add(ev5platz3);
        plaetze.add(ev5platz4);
        plaetze.add(ev5platz5);
        plaetze.add(ev5platz6);
        plaetze.add(ev5platz7);
        events.add(event5);

        Platz ev6platz1 = new Platz(601, 601, 1, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 120);
        Platz ev6platz2 = new Platz(602, 602, 2, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 120);
        Platz ev6platz3 = new Platz(603, 603, 3, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 120);
        Platz ev6platz4 = new Platz(604, 604, 4, at.fhv.tvv.backend.domain.model.platz.Kategorie.STEHPLATZ, 100);
        Platz ev6platz5 = new Platz(605, 605, 5, at.fhv.tvv.backend.domain.model.platz.Kategorie.STEHPLATZ, 100);
        Platz ev6platz6 = new Platz(606, 606, 6, at.fhv.tvv.backend.domain.model.platz.Kategorie.VIP, 430);
        Platz ev6platz7 = new Platz(607, 607, 7, at.fhv.tvv.backend.domain.model.platz.Kategorie.VIP, 430);
        event6.addPlatz(ev6platz1);
        event6.addPlatz(ev6platz2);
        event6.addPlatz(ev6platz3);
        event6.addPlatz(ev6platz4);
        event6.addPlatz(ev6platz5);
        event6.addPlatz(ev6platz6);
        event6.addPlatz(ev6platz7);
        plaetze.add(ev6platz1);
        plaetze.add(ev6platz2);
        plaetze.add(ev6platz3);
        plaetze.add(ev6platz4);
        plaetze.add(ev6platz5);
        plaetze.add(ev6platz6);
        plaetze.add(ev6platz7);
        events.add(event6);

        verkauf1.addPlatz(ev1platz1);
        verkauf2.addPlatz(ev6platz7);
        verkauf3.addPlatz(ev2platz4);
        verkauf4.addPlatz(ev5platz3);
        verkauf5.addPlatz(ev3platz5);
        verkauf6.addPlatz(ev4platz2);

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
        manager.persist(verkauf2);
        manager.persist(verkauf3);
        manager.persist(verkauf4);
        manager.persist(verkauf5);
        manager.persist(verkauf6);

        manager.getTransaction().commit();
        jmsDurableSubscribers.createDurableSubscribers(angestellte);

        messageProducerImpl.produce("KONZERT", "Test-Konzert1", "Eine Testnachricht über ein Konzert!");
        messageProducerImpl.produce("THEATER", "Test-Theater1", "Eine Testnachricht über ein Theaterstueck!");
        messageProducerImpl.produce("KINO", "Test-Kino1", "Eine Testnachricht über ein Kinoevent!");

    }
}
