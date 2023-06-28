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

import javax.annotation.PostConstruct;
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

    @PostConstruct
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
        Veranstaltungsort hohenems2 = new Veranstaltungsort(670017, "Cineplexx Hohenems", "Lustenauer Str.", "112", 6845, "Hohenems", "Österreich", "SAAL 2");
        veranstaltungsorte.add(hohenems2);
        Veranstaltungsort hohenems3 = new Veranstaltungsort(670018, "Cineplexx Hohenems", "Lustenauer Str.", "112", 6845, "Hohenems", "Österreich", "SAAL 3");
        veranstaltungsorte.add(hohenems3);

        Veranstaltungsort dornbirn1 = new Veranstaltungsort(670015, "FH Dornbirn", "Hochschulstraße", "1", 6850, "Dornbirn", "Österreich", "U314");
        veranstaltungsorte.add(dornbirn1);

        Veranstaltungsort bregenz1 = new Veranstaltungsort(670016, "Festspielhaus Bregenz", "Platz d. Wr. Symphoniker", "1", 6900, "Bregenz", "Österreich", "Festspielhaus - Großer Saal");
        veranstaltungsorte.add(bregenz1);


        //Veranstaltungsserien
        Veranstaltungsserie sotw = new Veranstaltungsserie("Superstars of the world", "Hier k&ouml;nnte Ihre Werbung stehen!", Kategorie.KONZERT, "FH Vorarlberg GmbH");
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
        Veranstaltungsserie tas = new Veranstaltungsserie("Saumarkt-Stuecke Herbst/Winter 2023", "Unser Theater-Programm für den Herbst/Winter 2023.", Kategorie.THEATER, "Saumarkt GmbH");
        veranstaltungsserien.add(tas);
        Veranstaltungsserie kas = new Veranstaltungsserie("Saumarkt-Konzerte Herbst/Winter 2023", "Unser Konzert-Programm für den Herbst/Winter 2023.", Kategorie.KONZERT, "Saumarkt GmbH");
        veranstaltungsserien.add(kas);
        Veranstaltungsserie wish = new Veranstaltungsserie("Wish", "'Wish' von Chris Buck und Fawn Veerasunthorn in der Regie.", Kategorie.KINO, "The Walt Disney Comp GmbH");
        veranstaltungsserien.add(wish);
        Veranstaltungsserie tvp = new Veranstaltungsserie("Die Tribute von Panem – The Ballad of Songbirds & Snakes", "'Die Tribute von Panem – The Ballad of Songbirds & Snakes' von Francis Lawrence in der Regie.", Kategorie.KINO, "Constantin Film");
        veranstaltungsserien.add(tvp);
        Veranstaltungsserie dfk = new Veranstaltungsserie("Das fliegende Klassenzimmer", "Endlich wieder auf der großen Leinwand: 'Das fliegende Klassenzimmer' kommt in einer Neuauflage des beliebten Kinder- und Jugendbuchklassikers.", Kategorie.KINO, "Constantin Film");
        veranstaltungsserien.add(dfk);
        Veranstaltungsserie girl = new Veranstaltungsserie("Girl you know it's true", "Die Geschichte des Aufstiegs der beiden Tänzer Robert Pilatus und Fabrice Morvan alias Milli Vanilli zum schillerndsten Pop-Duo", Kategorie.KINO, "Constantin Film");
        veranstaltungsserien.add(girl);


        //Events
        int datum1 = (int) (new Date("01/08/2023").getTime()/1000);
        int datum2 = (int) (new Date("31/12/2023").getTime()/1000);
        int datum3 = (int) (new Date("21/08/2023").getTime()/1000);
        int datum4 = (int) (new Date("24/09/2023").getTime()/1000);
        int datum5 = (int) (new Date("17/12/2023").getTime()/1000);
        int datum6 = (int) (new Date("21/10/2023").getTime()/1000);
        int datum7 = (int) (new Date("20/10/2023").getTime()/1000);
        int datum8 = (int) (new Date("17/09/2023").getTime()/1000);
        int datum9 = (int) (new Date("27/10/2023").getTime()/1000);
        int datum10 = (int) (new Date("30/11/2023").getTime()/1000);
        int datum11 = (int) (new Date("12/10/2023").getTime()/1000);
        int datum12 = (int) (new Date("16/11/2023").getTime()/1000);
        int datum13 = (int) (new Date("13/12/2023").getTime()/1000);

        Event event1 = new Event(1006, "Bruce Springsteen Konzert", sotw, "Hier k&oumlnnte Ihre Werbung stehen!", 1682002800, bludenz1);
        Event event2 = new Event(1007, "Requiem", mc, "Requiem - Das klassische Stueck aufgefuehrt in Feldkirch!", datum1, feldkirch1);
        Event event3 = new Event(1008, "404 - Die Fehler in unserer Realitaet", vnv, "Die kurioseste IT-verseuchteste romantische-Komoedie ueberhaupt!", datum2, feldkirch2);
        Event event4 = new Event(1009, "Dungeons & Dragons: Ehre unter Dieben", dad, "Kinovorstellung Nr. 1 in Hohenems", datum3, hohenems1);
        Event event5 = new Event(1010, "Enterprise Applikationen - The Movie", fhd, "Viele Vorlesungen - EIN GANZER FILM.", 1702166400, dornbirn1);
        Event event6 = new Event(1011, "Die Zauberfloete fuer Kinder", zfk, "Ein Klassiker neu aufgefuehrt!", datum4, bregenz1);
        Event event7 = new Event(1012, "KRIEG", tas, "Die Geschichte der Kriegsverläufe umgekehrt: Was waere, wenn er bei uns waere?", datum5, feldkirch2);
        Event event8 = new Event(1013, "Theater Patati-Patata präsentiert: Alex und die gelbe Maus", tas, "Eine Mäusegeschichte über echte Freundschaft und den großen Wunsch, geliebt zu werden.", datum6, feldkirch2);
        Event event9 = new Event(1014, "Theaterproduktion Villa Falkenhorst: Gemeinsam ist Alzheimer schöner", tas, "'Gemeinsam ist Alzheimer schöner' ist kein echtes Alzheimer – Stück, sondern ein Erinnerungspaartanz bei dem man einander mal absichtlich, mal aus Versehen auf die Zehen steigt.", datum7, feldkirch2);
        Event event10 = new Event(1015, "Polo Noyalet - Toutes mes chansons", kas, "Humorvoll und unterhaltsam singt, spielt und erzählt Polo Noyalet eine musikalische Geschichte voller Poesie und Charme.", datum8, feldkirch2);
        Event event11 = new Event(1016, "Radio Diwanistan - Orientalischer Partystimmung und osteuropäischer Blues", kas, "Radio Diwanistan verbindet musikalische Einflüsse aus Osteuropa, dem Nahen Osten und vom Balkan zu einem frischen neuen Sound.", datum9, feldkirch2);
        Event event12 = new Event(1017, "WISH", wish, "'Wish' von Chris Buck und Fawn Veerasunthorn zeigt, dass mutige Menschen Erstaunliches erreichen können, sobald sie sich mit der Magie der Sterne verbinden.", datum10, hohenems2);
        Event event13 = new Event(1018, "DAS FLIEGENDE KLASSENZIMMER", dfk, "Endlich wieder auf der großen Leinwand: 'Das fliegende Klassenzimmer' kommt in einer Neuauflage des beliebten Kinder- und Jugendbuchklassikers von Erich Kästner unter der Regie von Carolina Hellgard in die Kinos.", datum11, hohenems2);
        Event event14 = new Event(1019, "DIE TRIBUTE VON PANEM – THE BALLAD OF SONGBIRDS & SNAKES", tvp, "'Die Tribute von Panem – The Ballad of Songbirds & Snakes' erzählt die Vorgeschichte der Welt von Panem und den Anfang der gefürchteten Hungerspiele.", datum12, hohenems2);
        Event event15 = new Event(1020, "GIRL YOU KNOW IT'S TRUE", girl, "Die wahre Geschichte des märchenhaften Aufstiegs der beiden Tänzer Robert Pilatus und Fabrice Morvan alias Milli Vanilli zum schillerndsten Pop-Duo der späten 80er Jahre.", datum13, hohenems3);


        //Verkauf
        Verkauf verkauf1 = new Verkauf(UUID.randomUUID(), 10, 0, UUID.randomUUID(), "18:11", Zahlungsmethode.KREDITKARTE, "Maxine Musterfrau");
        Verkauf verkauf2 = new Verkauf(UUID.randomUUID(), 8.99F, 0, UUID.fromString("af3ce120-2059-4533-95d6-883f1567d376"), "12:57", Zahlungsmethode.BAR, "Maxine Musterfrau"); //Kunde: Aiden Sorg
        Verkauf verkauf3 = new Verkauf(UUID.randomUUID(), 34.95F, 0, UUID.fromString("1e342519-9e5e-43f6-8636-60c4ceee4a91"), "09:23", Zahlungsmethode.RECHNUNG, "Herbert Walter"); //Kunde: Maria Röhl
        Verkauf verkauf4 = new Verkauf(UUID.randomUUID(), 15.99F, 0, UUID.fromString("1e342519-9e5e-43f6-8636-60c4ceee4a91"), "10:34", Zahlungsmethode.RECHNUNG, "Herbert Walter"); //Kunde: Maria Röhl
        Verkauf verkauf5 = new Verkauf(UUID.randomUUID(), 12.95F, 0, UUID.fromString("1e342519-9e5e-43f6-8636-60c4ceee4a91"), "16:16", Zahlungsmethode.KREDITKARTE, "Maxine Musterfrau"); //Kunde: Maria Röhl
        Verkauf verkauf6 = new Verkauf(UUID.randomUUID(), 20F, 0, UUID.fromString("af3ce120-2059-4533-95d6-883f1567d376"), "13:20", Zahlungsmethode.BAR, "Max Mustermann"); //Kunde: Aiden Sorg
        Verkauf verkauf7 = new Verkauf(UUID.randomUUID(), 10F, 0, UUID.fromString("6622b362-b1f4-4efc-8801-699880a6e8db"), "14:45", Zahlungsmethode.RECHNUNG, "Max Mustermann"); //Kunde: Adelina Landmann
        Verkauf verkauf8 = new Verkauf(UUID.randomUUID(), 14.99F, 0, UUID.fromString("de5cc747-d471-44a1-9b37-784c81e704e5"), "15:15", Zahlungsmethode.BAR, "Max Mustermann"); //Kunde: Josie Stöber
        Verkauf verkauf9 = new Verkauf(UUID.randomUUID(), 11.99F, 0, UUID.fromString("6622b362-b1f4-4efc-8801-699880a6e8db"), "20:26", Zahlungsmethode.RECHNUNG, "Max Mustermann"); //Kunde: Adelina Landmann
        Verkauf verkauf10 = new Verkauf(UUID.randomUUID(), 11.99F, 0, UUID.fromString("6622b362-b1f4-4efc-8801-699880a6e8db"), "12:49", Zahlungsmethode.BAR, "Max Mustermann"); //Kunde: Adelina Landmann
        Verkauf verkauf11 = new Verkauf(UUID.randomUUID(), 9.99F, 0, UUID.fromString("de5cc747-d471-44a1-9b37-784c81e704e5"), "21:34", Zahlungsmethode.BAR, "Max Mustermann"); //Kunde: Josie Stöber
        Verkauf verkauf12 = new Verkauf(UUID.randomUUID(), 13F, 0, UUID.fromString("af3ce120-2059-4533-95d6-883f1567d376"), "09:22", Zahlungsmethode.BAR, "Max Mustermann"); //Kunde: Aiden Sorg
        Verkauf verkauf13 = new Verkauf(UUID.randomUUID(), 11.95F, 0, UUID.fromString("0c28d094-4c31-4f05-ad74-2d97af7aaf04"), "10:55", Zahlungsmethode.BAR, "Max Mustermann"); //Kunde: Erva Grewe
        Verkauf verkauf14 = new Verkauf(UUID.randomUUID(), 16.95F, 0, UUID.fromString("6622b362-b1f4-4efc-8801-699880a6e8db"), "11:50", Zahlungsmethode.KREDITKARTE, "Max Mustermann"); //Kunde: Adelina Landmann
        Verkauf verkauf15 = new Verkauf(UUID.randomUUID(), 18.75F, 0, UUID.fromString("de5cc747-d471-44a1-9b37-784c81e704e5"), "08:40", Zahlungsmethode.KREDITKARTE, "Max Mustermann"); //Kunde: Josie Stöber


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

        Platz ev7platz1 = new Platz(701, 701, 1, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 10);
        Platz ev7platz2 = new Platz(702, 702, 2, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 10);
        Platz ev7platz3 = new Platz(703, 703, 3, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 10);
        Platz ev7platz4 = new Platz(704, 704, 4, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 10);
        Platz ev7platz5 = new Platz(705, 705, 5, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 10);
        Platz ev7platz6 = new Platz(706, 706, 6, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 10);
        Platz ev7platz7 = new Platz(707, 707, 7, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 10);
        event7.addPlatz(ev7platz1);
        event7.addPlatz(ev7platz2);
        event7.addPlatz(ev7platz3);
        event7.addPlatz(ev7platz4);
        event7.addPlatz(ev7platz5);
        event7.addPlatz(ev7platz6);
        event7.addPlatz(ev7platz7);
        plaetze.add(ev7platz1);
        plaetze.add(ev7platz2);
        plaetze.add(ev7platz3);
        plaetze.add(ev7platz4);
        plaetze.add(ev7platz5);
        plaetze.add(ev7platz6);
        plaetze.add(ev7platz7);
        events.add(event7);

        Platz ev8platz1 = new Platz(801, 801, 1, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 14.99F);
        Platz ev8platz2 = new Platz(802, 802, 2, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 14.99F);
        Platz ev8platz3 = new Platz(803, 803, 3, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 14.99F);
        Platz ev8platz4 = new Platz(804, 804, 4, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 14.99F);
        Platz ev8platz5 = new Platz(805, 805, 5, at.fhv.tvv.backend.domain.model.platz.Kategorie.STEHPLATZ, 11.99F);
        Platz ev8platz6 = new Platz(806, 806, 6, at.fhv.tvv.backend.domain.model.platz.Kategorie.STEHPLATZ, 11.99F);
        Platz ev8platz7 = new Platz(807, 807, 7, at.fhv.tvv.backend.domain.model.platz.Kategorie.STEHPLATZ, 11.99F);
        event8.addPlatz(ev8platz1);
        event8.addPlatz(ev8platz2);
        event8.addPlatz(ev8platz3);
        event8.addPlatz(ev8platz4);
        event8.addPlatz(ev8platz5);
        event8.addPlatz(ev8platz6);
        event8.addPlatz(ev8platz7);
        plaetze.add(ev8platz1);
        plaetze.add(ev8platz2);
        plaetze.add(ev8platz3);
        plaetze.add(ev8platz4);
        plaetze.add(ev8platz5);
        plaetze.add(ev8platz6);
        plaetze.add(ev8platz7);
        events.add(event8);

        Platz ev9platz1 = new Platz(901, 901, 1, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 14.99F);
        Platz ev9platz2 = new Platz(902, 902, 2, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 14.99F);
        Platz ev9platz3 = new Platz(903, 903, 3, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 14.99F);
        Platz ev9platz4 = new Platz(904, 904, 4, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 14.99F);
        Platz ev9platz5 = new Platz(905, 905, 5, at.fhv.tvv.backend.domain.model.platz.Kategorie.STEHPLATZ, 11.99F);
        Platz ev9platz6 = new Platz(906, 906, 6, at.fhv.tvv.backend.domain.model.platz.Kategorie.STEHPLATZ, 11.99F);
        Platz ev9platz7 = new Platz(907, 907, 7, at.fhv.tvv.backend.domain.model.platz.Kategorie.STEHPLATZ, 11.99F);
        event9.addPlatz(ev9platz1);
        event9.addPlatz(ev9platz2);
        event9.addPlatz(ev9platz3);
        event9.addPlatz(ev9platz4);
        event9.addPlatz(ev9platz5);
        event9.addPlatz(ev9platz6);
        event9.addPlatz(ev9platz7);
        plaetze.add(ev9platz1);
        plaetze.add(ev9platz2);
        plaetze.add(ev9platz3);
        plaetze.add(ev9platz4);
        plaetze.add(ev9platz5);
        plaetze.add(ev9platz6);
        plaetze.add(ev9platz7);
        events.add(event9);

        Platz ev10platz1 = new Platz(1001, 1001, 1, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 11.99F);
        Platz ev10platz2 = new Platz(1002, 1002, 2, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 11.99F);
        Platz ev10platz3 = new Platz(1003, 1003, 3, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 11.99F);
        Platz ev10platz4 = new Platz(1004, 1004, 4, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 11.99F);
        Platz ev10platz5 = new Platz(1005, 1005, 5, at.fhv.tvv.backend.domain.model.platz.Kategorie.STEHPLATZ, 9.99F);
        Platz ev10platz6 = new Platz(1006, 1006, 6, at.fhv.tvv.backend.domain.model.platz.Kategorie.STEHPLATZ, 9.99F);
        Platz ev10platz7 = new Platz(1007, 1007, 7, at.fhv.tvv.backend.domain.model.platz.Kategorie.STEHPLATZ, 9.99F);
        event10.addPlatz(ev10platz1);
        event10.addPlatz(ev10platz2);
        event10.addPlatz(ev10platz3);
        event10.addPlatz(ev10platz4);
        event10.addPlatz(ev10platz5);
        event10.addPlatz(ev10platz6);
        event10.addPlatz(ev10platz7);
        plaetze.add(ev10platz1);
        plaetze.add(ev10platz2);
        plaetze.add(ev10platz3);
        plaetze.add(ev10platz4);
        plaetze.add(ev10platz5);
        plaetze.add(ev10platz6);
        plaetze.add(ev10platz7);
        events.add(event10);

        Platz ev11platz1 = new Platz(1101, 1101, 1, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 11.99F);
        Platz ev11platz2 = new Platz(1102, 1102, 2, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 11.99F);
        Platz ev11platz3 = new Platz(1103, 1103, 3, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 11.99F);
        Platz ev11platz4 = new Platz(1104, 1104, 4, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 11.99F);
        Platz ev11platz5 = new Platz(1105, 1105, 5, at.fhv.tvv.backend.domain.model.platz.Kategorie.STEHPLATZ, 9.99F);
        Platz ev11platz6 = new Platz(1106, 1106, 6, at.fhv.tvv.backend.domain.model.platz.Kategorie.STEHPLATZ, 9.99F);
        Platz ev11platz7 = new Platz(1107, 1107, 7, at.fhv.tvv.backend.domain.model.platz.Kategorie.STEHPLATZ, 9.99F);
        event11.addPlatz(ev11platz1);
        event11.addPlatz(ev11platz2);
        event11.addPlatz(ev11platz3);
        event11.addPlatz(ev11platz4);
        event11.addPlatz(ev11platz5);
        event11.addPlatz(ev11platz6);
        event11.addPlatz(ev11platz7);
        plaetze.add(ev11platz1);
        plaetze.add(ev11platz2);
        plaetze.add(ev11platz3);
        plaetze.add(ev11platz4);
        plaetze.add(ev11platz5);
        plaetze.add(ev11platz6);
        plaetze.add(ev11platz7);
        events.add(event11);

        Platz ev12platz1 = new Platz(1201, 1201, 1, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 13F);
        Platz ev12platz2 = new Platz(1202, 1202, 2, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 13F);
        Platz ev12platz3 = new Platz(1203, 1203, 3, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 13F);
        Platz ev12platz4 = new Platz(1204, 1204, 4, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 13F);
        Platz ev12platz5 = new Platz(1205, 1205, 5, at.fhv.tvv.backend.domain.model.platz.Kategorie.VIP, 17F);
        Platz ev12platz6 = new Platz(1206, 1206, 6, at.fhv.tvv.backend.domain.model.platz.Kategorie.VIP, 17F);
        Platz ev12platz7 = new Platz(1207, 1207, 7, at.fhv.tvv.backend.domain.model.platz.Kategorie.VIP, 17F);
        event12.addPlatz(ev12platz1);
        event12.addPlatz(ev12platz2);
        event12.addPlatz(ev12platz3);
        event12.addPlatz(ev12platz4);
        event12.addPlatz(ev12platz5);
        event12.addPlatz(ev12platz6);
        event12.addPlatz(ev12platz7);
        plaetze.add(ev12platz1);
        plaetze.add(ev12platz2);
        plaetze.add(ev12platz3);
        plaetze.add(ev12platz4);
        plaetze.add(ev12platz5);
        plaetze.add(ev12platz6);
        plaetze.add(ev12platz7);
        events.add(event12);

        Platz ev13platz1 = new Platz(1301, 1301, 1, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 11.95F);
        Platz ev13platz2 = new Platz(1302, 1302, 2, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 11.95F);
        Platz ev13platz3 = new Platz(1303, 1303, 3, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 11.95F);
        Platz ev13platz4 = new Platz(1304, 1304, 4, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 11.95F);
        Platz ev13platz5 = new Platz(1305, 1305, 5, at.fhv.tvv.backend.domain.model.platz.Kategorie.VIP, 15.95F);
        Platz ev13platz6 = new Platz(1306, 1306, 6, at.fhv.tvv.backend.domain.model.platz.Kategorie.VIP, 15.95F);
        Platz ev13platz7 = new Platz(1307, 1307, 7, at.fhv.tvv.backend.domain.model.platz.Kategorie.VIP, 15.95F);
        event13.addPlatz(ev13platz1);
        event13.addPlatz(ev13platz2);
        event13.addPlatz(ev13platz3);
        event13.addPlatz(ev13platz4);
        event13.addPlatz(ev13platz5);
        event13.addPlatz(ev13platz6);
        event13.addPlatz(ev13platz7);
        plaetze.add(ev13platz1);
        plaetze.add(ev13platz2);
        plaetze.add(ev13platz3);
        plaetze.add(ev13platz4);
        plaetze.add(ev13platz5);
        plaetze.add(ev13platz6);
        plaetze.add(ev13platz7);
        events.add(event13);

        Platz ev14platz1 = new Platz(1401, 1401, 1, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 12.95F);
        Platz ev14platz2 = new Platz(1402, 1402, 2, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 12.95F);
        Platz ev14platz3 = new Platz(1403, 1403, 3, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 12.95F);
        Platz ev14platz4 = new Platz(1404, 1404, 4, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 12.95F);
        Platz ev14platz5 = new Platz(1405, 1405, 5, at.fhv.tvv.backend.domain.model.platz.Kategorie.VIP, 16.95F);
        Platz ev14platz6 = new Platz(1406, 1406, 6, at.fhv.tvv.backend.domain.model.platz.Kategorie.VIP, 16.95F);
        Platz ev14platz7 = new Platz(1407, 1407, 7, at.fhv.tvv.backend.domain.model.platz.Kategorie.VIP, 16.95F);
        event14.addPlatz(ev14platz1);
        event14.addPlatz(ev14platz2);
        event14.addPlatz(ev14platz3);
        event14.addPlatz(ev14platz4);
        event14.addPlatz(ev14platz5);
        event14.addPlatz(ev14platz6);
        event14.addPlatz(ev14platz7);
        plaetze.add(ev14platz1);
        plaetze.add(ev14platz2);
        plaetze.add(ev14platz3);
        plaetze.add(ev14platz4);
        plaetze.add(ev14platz5);
        plaetze.add(ev14platz6);
        plaetze.add(ev14platz7);
        events.add(event14);

        Platz ev15platz1 = new Platz(1501, 1501, 1, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 14.75F);
        Platz ev15platz2 = new Platz(1502, 1502, 2, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 14.75F);
        Platz ev15platz3 = new Platz(1503, 1503, 3, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 14.75F);
        Platz ev15platz4 = new Platz(1504, 1504, 4, at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ, 14.75F);
        Platz ev15platz5 = new Platz(1505, 1505, 5, at.fhv.tvv.backend.domain.model.platz.Kategorie.VIP, 18.75F);
        Platz ev15platz6 = new Platz(1506, 1506, 6, at.fhv.tvv.backend.domain.model.platz.Kategorie.VIP, 18.75F);
        Platz ev15platz7 = new Platz(1507, 1507, 7, at.fhv.tvv.backend.domain.model.platz.Kategorie.VIP, 18.75F);
        event15.addPlatz(ev15platz1);
        event15.addPlatz(ev15platz2);
        event15.addPlatz(ev15platz3);
        event15.addPlatz(ev15platz4);
        event15.addPlatz(ev15platz5);
        event15.addPlatz(ev15platz6);
        event15.addPlatz(ev15platz7);
        plaetze.add(ev15platz1);
        plaetze.add(ev15platz2);
        plaetze.add(ev15platz3);
        plaetze.add(ev15platz4);
        plaetze.add(ev15platz5);
        plaetze.add(ev15platz6);
        plaetze.add(ev15platz7);
        events.add(event15);


        verkauf1.addPlatz(ev1platz1);
        verkauf2.addPlatz(ev6platz7);
        verkauf3.addPlatz(ev2platz4);
        verkauf4.addPlatz(ev5platz3);
        verkauf5.addPlatz(ev3platz5);
        verkauf6.addPlatz(ev4platz2);
        verkauf7.addPlatz(ev7platz1);
        verkauf8.addPlatz(ev8platz2);
        verkauf9.addPlatz(ev9platz6);
        verkauf10.addPlatz(ev10platz4);
        verkauf11.addPlatz(ev11platz7);
        verkauf12.addPlatz(ev12platz3);
        verkauf13.addPlatz(ev13platz1);
        verkauf14.addPlatz(ev14platz5);
        verkauf15.addPlatz(ev15platz6);

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
        manager.persist(verkauf7);
        manager.persist(verkauf8);
        manager.persist(verkauf9);
        manager.persist(verkauf10);
        manager.persist(verkauf11);
        manager.persist(verkauf12);
        manager.persist(verkauf13);
        manager.persist(verkauf14);
        manager.persist(verkauf15);

        manager.getTransaction().commit();
        jmsDurableSubscribers.createDurableSubscribers(angestellte);

        messageProducerImpl.produce("KONZERT", "Test-Konzert1", "Eine Testnachricht über ein Konzert!");
        messageProducerImpl.produce("THEATER", "Test-Theater1", "Eine Testnachricht über ein Theaterstueck!");
        messageProducerImpl.produce("KINO", "Test-Kino1", "Eine Testnachricht über ein Kinoevent!");

    }
}
