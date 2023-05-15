package at.fhv.tvv.backend.communication;

import at.fhv.tvv.backend.HibernateService;
import at.fhv.tvv.backend.domain.model.angestellte.Rolle;
import at.fhv.tvv.backend.domain.model.platz.Kategorie;
import at.fhv.tvv.backend.domain.model.verkauf.Zahlungsmethode;
import at.fhv.tvv.shared.dto.WarenkorbZeileDTO;
import at.fhv.tvv.shared.rmi.TvvSession;
import at.fhv.tvv.shared.rmi.TvvSessionFactory;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TvvSessionImplRMITest {

    @Test
    void TvvSessionImplRMIKorrektInitialisieren() throws RemoteException {

        //TvvSessionFactory erstellen
        TvvSessionFactory tvvSessionFactory = HibernateService.tvvSessionFactoryRMI();

        //Create Session
        TvvSession test = tvvSessionFactory.createSession();

        //Test gültig, wennn gilt...
        assertEquals(TvvSessionImplRMI.class, test.getClass());

    }

    @Test
    void getWarenkorbVonBestehenderSession() throws RemoteException {

        //TvvSessionFactory erstellen
        TvvSessionFactory tvvSessionFactory = HibernateService.tvvSessionFactoryRMI();

        //Create Session
        TvvSession test = tvvSessionFactory.createSession();

        List<WarenkorbZeileDTO> actualWarenkorb = test.getWarenkorb();

        //Test gültig, wennn gilt...
        assertEquals(TvvSessionImplRMI.class, test.getClass());
        assertEquals(0, actualWarenkorb.size()); //DA TEST-SESSION LEER IST

    }

    @Test
    void WarenkorbEinenArtikelHinzufuegen() throws RemoteException {

        //TvvSessionFactory erstellen
        TvvSessionFactory tvvSessionFactory = HibernateService.tvvSessionFactoryRMI();

        //Create Session
        TvvSession test = tvvSessionFactory.createSession();

        WarenkorbZeileDTO testTicket = new WarenkorbZeileDTO(401, Kategorie.SITZPLATZ.getName(), 1006, "Bruce Springsteen Konzert", 120, "1682002800");
        test.hinzufuegen(testTicket);

        List<WarenkorbZeileDTO> actualWarenkorb = test.getWarenkorb();

        //Test gültig, wennn gilt...
        assertEquals(TvvSessionImplRMI.class, test.getClass());
        assertEquals(1, actualWarenkorb.size());
        assertEquals(testTicket, actualWarenkorb.get(0));

    }

    @Test
    void EinenArtikelAusDemWarenkorbLoeschen() throws RemoteException {

        //TvvSessionFactory erstellen
        TvvSessionFactory tvvSessionFactory = HibernateService.tvvSessionFactoryRMI();

        //Create Session
        TvvSession test = tvvSessionFactory.createSession();

        List<WarenkorbZeileDTO> actualWarenkorb = test.getWarenkorb();

        WarenkorbZeileDTO testTicket1 = new WarenkorbZeileDTO(401, Kategorie.SITZPLATZ.getName(), 1006, "Bruce Springsteen Konzert", 120, "1682002800");
        WarenkorbZeileDTO testTicket2 = new WarenkorbZeileDTO(402, Kategorie.SITZPLATZ.getName(), 1006, "Bruce Springsteen Konzert", 120, "1682002800");
        test.hinzufuegen(testTicket1);
        test.hinzufuegen(testTicket2);
        //Test-Warenkorb besitzt jetzt 2 Tickets
        assertEquals(2, actualWarenkorb.size());

        //testTicket1 löschen
        test.loeschen(testTicket1);

        //Test gültig, wennn gilt...
        assertEquals(1, actualWarenkorb.size());
        assertEquals(testTicket2, actualWarenkorb.get(0));

    }

    @Test
    void denWarenkorbDerSessionLeeren() throws RemoteException {

        //TvvSessionFactory erstellen
        TvvSessionFactory tvvSessionFactory = HibernateService.tvvSessionFactoryRMI();

        //Create Session
        TvvSession test = tvvSessionFactory.createSession();

        List<WarenkorbZeileDTO> actualWarenkorb = test.getWarenkorb();

        WarenkorbZeileDTO testTicket1 = new WarenkorbZeileDTO(401, Kategorie.SITZPLATZ.getName(), 1006, "Bruce Springsteen Konzert", 120, "1682002800");
        WarenkorbZeileDTO testTicket2 = new WarenkorbZeileDTO(402, Kategorie.SITZPLATZ.getName(), 1006, "Bruce Springsteen Konzert", 120, "1682002800");
        test.hinzufuegen(testTicket1);
        test.hinzufuegen(testTicket2);
        //Test-Warenkorb besitzt jetzt 2 Tickets
        assertEquals(2, actualWarenkorb.size());

        //Test-Warenkorb leeren
        test.leeren();

        //Test gültig, wennn gilt...
        assertEquals(0, actualWarenkorb.size());

    }

    @Test
    void ErstellteSessionMitInformationenBefuellen() throws RemoteException {

        //TvvSessionFactory erstellen
        TvvSessionFactory tvvSessionFactory = HibernateService.tvvSessionFactoryRMI();

        //Create Session
        TvvSession test = tvvSessionFactory.createSession();

        //Benutzername angeben
        String benutzername = "HelloWorld01";
        test.setBenutzerName(benutzername);

        //Rollen angeben
        List<String> rollen = new ArrayList<>();
        rollen.add(Rolle.MITARBEITER.getName());
        rollen.add(Rolle.OPERATOR.getName());

        test.setRollen(rollen);

        //Topics angeben
        List<String> topics = new ArrayList<>();
        topics.add(at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie.KINO.getName());
        topics.add(at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie.THEATER.getName());
        topics.add(at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie.KONZERT.getName());

        test.setTopics(topics);

        //Warenkorb befüllen
        WarenkorbZeileDTO testTicket1 = new WarenkorbZeileDTO(401, Kategorie.SITZPLATZ.getName(), 1006, "Bruce Springsteen Konzert", 120, "1682002800");
        WarenkorbZeileDTO testTicket2 = new WarenkorbZeileDTO(402, Kategorie.SITZPLATZ.getName(), 1006, "Bruce Springsteen Konzert", 120, "1682002800");
        test.hinzufuegen(testTicket1);
        test.hinzufuegen(testTicket2);

        //Zahlungsmethode hinzufügen
        String zahlungsmethode = Zahlungsmethode.KREDITKARTE.getName();
        test.hinzufuegenZahlungsMethode(zahlungsmethode);

        //Kunde hinzufügen
                    /* TEST-KUNDE
                    Vorname: Aiden
                    Nachname: Sorg
                    UUID: af3ce120-2059-4533-95d6-883f1567d376
                    */
        UUID kunde = UUID.fromString("af3ce120-2059-4533-95d6-883f1567d376");
        test.hinzufuegenKunde(kunde);


        //Test gültig, wennn gilt...
        assertEquals(benutzername, test.getBenutzerName());
        assertEquals(rollen, test.getRollen());
        assertEquals(topics, test.getTopics());
        assertEquals(2, test.getWarenkorb().size());
        assertEquals(testTicket1, test.getWarenkorb().get(0));
        assertEquals(testTicket2, test.getWarenkorb().get(1));
        assertEquals(zahlungsmethode, test.getZahlungsMethode());
        assertEquals(kunde, test.getKunde());

    }

}