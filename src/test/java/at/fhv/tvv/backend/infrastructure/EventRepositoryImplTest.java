package at.fhv.tvv.backend.infrastructure;

import at.fhv.tvv.backend.HibernateService;
import at.fhv.tvv.backend.domain.model.angestellte.Angestellte;
import at.fhv.tvv.backend.domain.model.angestellte.Rolle;
import at.fhv.tvv.backend.domain.model.event.Event;
import at.fhv.tvv.backend.domain.model.platz.Platz;
import at.fhv.tvv.backend.domain.model.veranstaltungsort.Veranstaltungsort;
import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie;
import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Veranstaltungsserie;
import at.fhv.tvv.backend.domain.model.verkauf.Verkauf;
import at.fhv.tvv.backend.domain.model.verkauf.Zahlungsmethode;
import at.fhv.tvv.backend.domain.repository.EventRepository;
import at.fhv.tvv.shared.dto.CustomerEventDTO;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class EventRepositoryImplTest {

    //Testdaten erstellen
    int eventId = 16751;
    String name = "Neues Testevent 2023";
    Veranstaltungsserie veranstaltungsserie = new Veranstaltungsserie("Testserie", "Hier könnte Ihre Werbung stehen!", Kategorie.KONZERT, "FH Vorarlberg GmbH");
    String beschreibung = "Hier ist ein Text.";
    int datum = 1682002800;
    Veranstaltungsort veranstaltungsort = new Veranstaltungsort(670011, "Remise Bludenz", "Raiffeisenplatz", "1", 6700, "Bludenz", "Österreich", "Hauptsaal");
    List<Platz> plaetze = new ArrayList<>();

    Event event1 = new Event(eventId, name, veranstaltungsserie, beschreibung, datum, veranstaltungsort);

    int eventId2 = 16752;
    String name2 = "404-Informatik-Basics";
    Veranstaltungsserie veranstaltungsserie2 = new Veranstaltungsserie("Testserie", "Hier könnte Ihre Werbung stehen!", Kategorie.KINO, "FH Vorarlberg GmbH");
    String beschreibung2 = "Hier ist ein Text.";
    int datum2 = 1345619256;
    Veranstaltungsort veranstaltungsort2 = new Veranstaltungsort(670011, "Remise Bludenz", "Raiffeisenplatz", "1", 6700, "Bludenz", "Österreich", "Hauptsaal");
    List<Platz> plaetze2 = new ArrayList<>();

    Event event2 = new Event(eventId2, name2, veranstaltungsserie2, beschreibung2, datum2, veranstaltungsort2);

    int eventId3 = 16753;
    String name3 = "Die Chroniken Der Informatik";
    Veranstaltungsserie veranstaltungsserie3 = new Veranstaltungsserie("Informatik Classics", "Hier könnte Ihre Werbung stehen!", Kategorie.THEATER, "FH Vorarlberg GmbH");
    String beschreibung3 = "Hier ist ein Text.";
    int datum3 = 1682002800;
    Veranstaltungsort veranstaltungsort3 = new Veranstaltungsort(670011, "Remise Bludenz", "Raiffeisenplatz", "1", 6700, "Bludenz", "Österreich", "Hauptsaal");
    List<Platz> plaetze3 = new ArrayList<>();

    Event event3 = new Event(eventId3, name3, veranstaltungsserie3, beschreibung3, datum3, veranstaltungsort3);

    List<Event> testEvents = new ArrayList<>();


    //------------------------TESTS------------------------------------------------------------------------------

    @Test
    void sucheEventNachNameInEventDB() {

        //Test-Events in Liste events speichern
        testEvents.add(event1); //Neues Testevent 2023
        testEvents.add(event2); //404-Informatik-Basics
        testEvents.add(event3); //Die Chroniken Der Informatik

        List<Event> itEvents = new ArrayList<>();
        itEvents.add(event2);
        itEvents.add(event3);

        String searchString = "Informatik";

        //Mocking
        EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
        TypedQuery<Event> queryMock = Mockito.mock(TypedQuery.class);
        TypedQuery<Event> paramQueryMock = Mockito.mock(TypedQuery.class);
        EventRepositoryImpl eventRepository = new EventRepositoryImpl(entityManagerMock);

        when(entityManagerMock.createQuery("SELECT e FROM Event e WHERE LOWER(e.name) LIKE LOWER(?1) OR LOWER(e.veranstaltungsort.gebaeude) LIKE LOWER(?1)", Event.class)).thenReturn(queryMock);
        when(queryMock.setParameter(1, "%" + searchString + "%")).thenReturn(paramQueryMock);
        when(paramQueryMock.getResultList()).thenReturn(itEvents);

        List<Event> actualEvents = eventRepository.searchByString(searchString);

        //Test gültig, wenn gilt...
        assertEquals(actualEvents, itEvents);
        assertNotEquals(actualEvents, testEvents);

    }

    @Test
    void sucheEventsNachDatumInEventDB() {

        //Test-Events in Liste events speichern
        testEvents.add(event1); //int datum = 1682002800
        testEvents.add(event2); //int datum = 1345619256
        testEvents.add(event3); //int datum = 1682002800

        List<Event> events2023 = new ArrayList<>();
        events2023.add(event1);
        events2023.add(event3);

        int date1 = 1345619256;
        int date2 = 1345619256;

        //Mocking
        EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
        TypedQuery<Event> queryMock = Mockito.mock(TypedQuery.class);
        TypedQuery<Event> paramQueryMock1 = Mockito.mock(TypedQuery.class);
        TypedQuery<Event> paramQueryMock2 = Mockito.mock(TypedQuery.class);
        EventRepositoryImpl eventRepository = new EventRepositoryImpl(entityManagerMock);

        when(entityManagerMock.createQuery("SELECT e FROM Event e WHERE e.datum BETWEEN (?1) AND (?2)", Event.class)).thenReturn(queryMock);
        when(queryMock.setParameter(1, date1)).thenReturn(paramQueryMock1);
        when(paramQueryMock1.setParameter(2, date2)).thenReturn(paramQueryMock2);
        when(paramQueryMock2.getResultList()).thenReturn(testEvents);

        List<Event> actualEvents = eventRepository.searchByDate(date1, date2);

        //Test gültig, wenn gilt...
        assertEquals(actualEvents, testEvents);
        assertNotEquals(actualEvents, events2023);

    }

    @Test
    void sucheEventsNachKategorieInEventDB() {

        //Test-Events in Liste events speichern
        testEvents.add(event1); //Konzert
        testEvents.add(event2); //Kino
        testEvents.add(event3); //Theater

        List<Event> kinoEvents = new ArrayList<>();
        kinoEvents.add(event2);

        String searchString = "KINO";
        Kategorie kategorie = Kategorie.KINO;

        //Mocking
        EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
        TypedQuery<Event> queryMock = Mockito.mock(TypedQuery.class);
        TypedQuery<Event> paramQueryMock = Mockito.mock(TypedQuery.class);
        EventRepositoryImpl eventRepository = new EventRepositoryImpl(entityManagerMock);

        when(entityManagerMock.createQuery("SELECT e FROM Event e WHERE e.veranstaltungsserie.kategorie=(?1)", Event.class)).thenReturn(queryMock);
        when(queryMock.setParameter(1, kategorie)).thenReturn(paramQueryMock);
        when(paramQueryMock.getResultList()).thenReturn(kinoEvents);

        List<Event> actualEvents = eventRepository.searchByCategory(searchString);

        //Test gültig, wenn gilt...
        assertEquals(Kategorie.valueOf(searchString), kategorie);
        assertEquals(actualEvents, kinoEvents);
        assertNotEquals(actualEvents, testEvents);

    }

    @Test
    void sucheEventNachEventIDInEventDB() {

        //Gesucht wird event1 mit ID 16751
        String searchString = "Informatik";

        int eventId = 16751;

        //Mocking
        EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
        TypedQuery<Event> queryMock = Mockito.mock(TypedQuery.class);
        TypedQuery<Event> paramQueryMock = Mockito.mock(TypedQuery.class);
        EventRepositoryImpl eventRepository = new EventRepositoryImpl(entityManagerMock);

        when(entityManagerMock.createQuery("SELECT e FROM Event e WHERE e.eventId=(?1)", Event.class)).thenReturn(queryMock);
        when(queryMock.setParameter(1, eventId)).thenReturn(paramQueryMock);
        when(paramQueryMock.getSingleResult()).thenReturn(event1);

        Event actualEvent = eventRepository.searchById(eventId);

        //Test gültig, wenn gilt...
        assertEquals(actualEvent, event1);
        assertNotEquals(actualEvent, event2);
        assertNotEquals(actualEvent, event3);

    }

    @Test
    void suchePlatzNachPlatzIDInPlatzDB() {

        int platzId = 14;

        int nummer = 4;

        int reihe = 1;

        at.fhv.tvv.backend.domain.model.platz.Kategorie kategorie = at.fhv.tvv.backend.domain.model.platz.Kategorie.SITZPLATZ;

        float preis = 9.99F;

        //Test-Platz erstellen
        Platz platz1 = new Platz(platzId, nummer, reihe, kategorie, preis);

        int searchPlatzID = 14;

        //Mocking
        EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
        TypedQuery<Platz> queryMock = Mockito.mock(TypedQuery.class);
        TypedQuery<Platz> paramQueryMock = Mockito.mock(TypedQuery.class);
        EventRepositoryImpl eventRepository = new EventRepositoryImpl(entityManagerMock);

        when(entityManagerMock.createQuery("SELECT p FROM Platz p WHERE p.platzId=(?1)", Platz.class)).thenReturn(queryMock);
        when(queryMock.setParameter(1, platzId)).thenReturn(paramQueryMock);
        when(paramQueryMock.getSingleResult()).thenReturn(platz1);

        Platz actualPlatz = eventRepository.searchByPlatzId(searchPlatzID);

        //Test gültig, wenn gilt...
        assertEquals(actualPlatz, platz1);

    }

    @Test
    void bekommeTicketsEinesKunden() {

        //Test-Verkäufe erstellen für einen Test-Kunden
        UUID testKunde = UUID.fromString("918523a4-71dc-49a3-8a65-484099bf48bf");
        Verkauf verkauf1 = new Verkauf(UUID.randomUUID(), 9.99F, 0, testKunde, "18:11", Zahlungsmethode.KREDITKARTE, "Maxine Musterfrau");
        Verkauf verkauf2 = new Verkauf(UUID.randomUUID(), 9.99F, 0, testKunde, "18:14", Zahlungsmethode.KREDITKARTE, "Maxine Musterfrau");
        Verkauf verkauf3 = new Verkauf(UUID.randomUUID(), 9.99F, 0, testKunde, "18:22", Zahlungsmethode.KREDITKARTE, "Maxine Musterfrau");
        Verkauf verkauf4 = new Verkauf(UUID.randomUUID(), 9.99F, 0, testKunde, "18:45", Zahlungsmethode.KREDITKARTE, "Maxine Musterfrau");

        List<Verkauf> testVerkaeufe = new ArrayList<>();
        testVerkaeufe.add(verkauf1);
        testVerkaeufe.add(verkauf2);
        testVerkaeufe.add(verkauf3);
        testVerkaeufe.add(verkauf4);

        //Test-CustomerEventDTOs erstellen
        CustomerEventDTO event1 = new CustomerEventDTO(verkauf1.getVerkaufsId(), verkauf1.getVerkaufszeit(), verkauf1.getZahlungsmethode().toString(), verkauf1.getGesamtpreis());
        CustomerEventDTO event2 = new CustomerEventDTO(verkauf2.getVerkaufsId(), verkauf2.getVerkaufszeit(), verkauf2.getZahlungsmethode().toString(), verkauf2.getGesamtpreis());
        CustomerEventDTO event3 = new CustomerEventDTO(verkauf3.getVerkaufsId(), verkauf3.getVerkaufszeit(), verkauf3.getZahlungsmethode().toString(), verkauf3.getGesamtpreis());
        CustomerEventDTO event4 = new CustomerEventDTO(verkauf4.getVerkaufsId(), verkauf4.getVerkaufszeit(), verkauf4.getZahlungsmethode().toString(), verkauf4.getGesamtpreis());

        List<CustomerEventDTO> customerListe = new ArrayList<>();
        customerListe.add(event1);
        customerListe.add(event2);
        customerListe.add(event3);
        customerListe.add(event4);

        //Suche nach Tickets von Kunde testKunde
        UUID customerUuid = testKunde;

        //Mocking
        EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
        TypedQuery<Verkauf> queryMock = Mockito.mock(TypedQuery.class);
        TypedQuery<Verkauf> paramQueryMock = Mockito.mock(TypedQuery.class);
        EventRepositoryImpl eventRepository = new EventRepositoryImpl(entityManagerMock);

        when(entityManagerMock.createQuery("SELECT v from Verkauf v  WHERE v.kundenId=(?1)", Verkauf.class)).thenReturn(queryMock);
        when(queryMock.setParameter(1, customerUuid)).thenReturn(paramQueryMock);
        when(paramQueryMock.getResultList()).thenReturn(testVerkaeufe);

        List<CustomerEventDTO> actualTickets = eventRepository.getCustomerTickets(customerUuid);

        //Test gültig, wenn gilt...
        assertEquals(actualTickets.get(0).getTicketID(), customerListe.get(0).getTicketID());
        assertEquals(actualTickets.get(1).getTicketID(), customerListe.get(1).getTicketID());
        assertEquals(actualTickets.get(2).getTicketID(), customerListe.get(2).getTicketID());
        assertEquals(actualTickets.get(3).getTicketID(), customerListe.get(3).getTicketID());

        assertEquals(actualTickets.get(0).getVerkaufsZeitpunkt(), customerListe.get(0).getVerkaufsZeitpunkt());
        assertEquals(actualTickets.get(1).getVerkaufsZeitpunkt(), customerListe.get(1).getVerkaufsZeitpunkt());
        assertEquals(actualTickets.get(2).getVerkaufsZeitpunkt(), customerListe.get(2).getVerkaufsZeitpunkt());
        assertEquals(actualTickets.get(3).getVerkaufsZeitpunkt(), customerListe.get(3).getVerkaufsZeitpunkt());

        assertEquals(actualTickets.get(0).getZahlungsmethode(), customerListe.get(0).getZahlungsmethode());
        assertEquals(actualTickets.get(1).getZahlungsmethode(), customerListe.get(1).getZahlungsmethode());
        assertEquals(actualTickets.get(2).getZahlungsmethode(), customerListe.get(2).getZahlungsmethode());
        assertEquals(actualTickets.get(3).getZahlungsmethode(), customerListe.get(3).getZahlungsmethode());

        assertEquals(actualTickets.get(0).getGesamtPreis(), customerListe.get(0).getGesamtPreis());
        assertEquals(actualTickets.get(1).getGesamtPreis(), customerListe.get(1).getGesamtPreis());
        assertEquals(actualTickets.get(2).getGesamtPreis(), customerListe.get(2).getGesamtPreis());
        assertEquals(actualTickets.get(3).getGesamtPreis(), customerListe.get(3).getGesamtPreis());

    }

    @Test
    void sucheAngestellteInAngestellteDB() {

        //Test-Angestellten erstellen
        List<Rolle> rollen = new ArrayList<>();
        rollen.add(Rolle.MITARBEITER);
        rollen.add(Rolle.OPERATOR);

        List<Kategorie> topics = new ArrayList<>();
        topics.add(Kategorie.KINO);
        topics.add(Kategorie.KONZERT);
        topics.add(Kategorie.THEATER);

        Angestellte testAngestellter = new Angestellte("tf-test", rollen, topics);

        List<Angestellte> angestellte = new ArrayList<>();
        angestellte.add(testAngestellter);

        Optional<Angestellte> test = angestellte.stream().findFirst();


        //Suche nach Angestellten
        String userid = "tf-test";

        //Mocking
        EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
        TypedQuery<Angestellte> queryMock = Mockito.mock(TypedQuery.class);
        TypedQuery<Angestellte> paramQueryMock = Mockito.mock(TypedQuery.class);
        EventRepositoryImpl eventRepository = new EventRepositoryImpl(entityManagerMock);

        when(entityManagerMock.createQuery("Select a FROM Angestellte a WHERE a.benutzername = :userId", Angestellte.class)).thenReturn(queryMock);
        when(queryMock.setParameter("userId", userid)).thenReturn(paramQueryMock);
        when(paramQueryMock.getResultList()).thenReturn(angestellte);

        Optional<Angestellte> actualAngestellte = eventRepository.getAngestellerById(userid);

        //Test gültig, wenn gilt...
        assertEquals(actualAngestellte.isPresent(), test.isPresent());
        assertEquals(actualAngestellte.get().getBenutzername(), test.get().getBenutzername());
        assertEquals(actualAngestellte.get().getTopics(), test.get().getTopics());
        assertEquals(actualAngestellte.get().getRollen(), test.get().getRollen());

    }

    @Test
    void aktualisiereAngestellteninAngestelltenDB() {

        //Test-Angestellten erstellen
        List<Rolle> rollen = new ArrayList<>();
        rollen.add(Rolle.MITARBEITER);
        rollen.add(Rolle.OPERATOR);

        List<Kategorie> topicsBefore = new ArrayList<>();
        topicsBefore.add(Kategorie.KINO);
        topicsBefore.add(Kategorie.KONZERT);
        topicsBefore.add(Kategorie.THEATER);

        List<Kategorie> topicsAfter = new ArrayList<>();
        topicsAfter.add(Kategorie.KINO);
        topicsAfter.add(Kategorie.THEATER);

        Angestellte angestellterBefore = new Angestellte("tf-test", rollen, topicsBefore);
        Angestellte angestellterAfter = new Angestellte("tf-test", rollen, topicsAfter);


        //Mocking
        EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
        EntityTransaction entityTransactionMock = Mockito.mock(EntityTransaction.class);

        EventRepositoryImpl eventRepository = new EventRepositoryImpl(entityManagerMock);

        when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);

        eventRepository.updateAngestellter(angestellterBefore);

        //Test gültig, wenn gilt...
        assertNotEquals(angestellterBefore.getTopics(), angestellterAfter.getTopics());

    }

    @Test
    void kaufeTicket() {

        //Test-Verkauf erstellen
        Verkauf verkauf = new Verkauf(UUID.randomUUID(), 9.99F, 0, UUID.randomUUID(), "18:11", Zahlungsmethode.KREDITKARTE, "Maxine Musterfrau");

        //Mocking
        EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
        EntityTransaction entityTransactionMock = Mockito.mock(EntityTransaction.class);

        EventRepositoryImpl eventRepository = new EventRepositoryImpl(entityManagerMock);

        when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);

        eventRepository.purchase(verkauf);


    }

}