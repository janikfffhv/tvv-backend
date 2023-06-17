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

class TvvSessionImplEJBTest {

    @Test
    void TvvSessionImplRMIKorrektInitialisieren() throws RemoteException {

        //Create Session-EJB
        TvvSessionImplEJB tvvSessionImplEJB = new TvvSessionImplEJB();

        //Test gültig, wennn gilt...
        assertEquals(TvvSessionImplEJB.class, tvvSessionImplEJB.getClass());

    }

    @Test
    void getWarenkorbVonBestehenderSession() throws RemoteException {

        //Create Session-EJB
        TvvSessionImplEJB tvvSessionImplEJB = new TvvSessionImplEJB();

        List<WarenkorbZeileDTO> actualWarenkorb = tvvSessionImplEJB.getWarenkorb();

        //Test gültig, wennn gilt...
        assertEquals(TvvSessionImplEJB.class, tvvSessionImplEJB.getClass());
        assertEquals(0, actualWarenkorb.size()); //DA TEST-SESSION LEER IST

    }

    @Test
    void WarenkorbEinenArtikelHinzufuegen() throws RemoteException {

        //Create Session-EJB
        TvvSessionImplEJB tvvSessionImplEJB = new TvvSessionImplEJB();

        WarenkorbZeileDTO testTicket = new WarenkorbZeileDTO(401, Kategorie.SITZPLATZ.getName(), 1006, "Bruce Springsteen Konzert", 120, "1682002800");
        tvvSessionImplEJB.hinzufuegen(testTicket);

        List<WarenkorbZeileDTO> actualWarenkorb = tvvSessionImplEJB.getWarenkorb();

        //Test gültig, wennn gilt...
        assertEquals(TvvSessionImplEJB.class, tvvSessionImplEJB.getClass());
        assertEquals(1, actualWarenkorb.size());
        assertEquals(testTicket, actualWarenkorb.get(0));

    }

    @Test
    void EinenArtikelAusDemWarenkorbLoeschen() throws RemoteException {

        //Create Session-EJB
        TvvSessionImplEJB tvvSessionImplEJB = new TvvSessionImplEJB();

        List<WarenkorbZeileDTO> actualWarenkorb = tvvSessionImplEJB.getWarenkorb();

        WarenkorbZeileDTO testTicket1 = new WarenkorbZeileDTO(401, Kategorie.SITZPLATZ.getName(), 1006, "Bruce Springsteen Konzert", 120, "1682002800");
        WarenkorbZeileDTO testTicket2 = new WarenkorbZeileDTO(402, Kategorie.SITZPLATZ.getName(), 1006, "Bruce Springsteen Konzert", 120, "1682002800");
        tvvSessionImplEJB.hinzufuegen(testTicket1);
        tvvSessionImplEJB.hinzufuegen(testTicket2);
        //Test-Warenkorb besitzt jetzt 2 Tickets
        assertEquals(2, actualWarenkorb.size());

        //testTicket1 löschen
        tvvSessionImplEJB.loeschen(testTicket1);

        //Test gültig, wennn gilt...
        assertEquals(1, actualWarenkorb.size());
        assertEquals(testTicket2, actualWarenkorb.get(0));

    }

    @Test
    void denWarenkorbDerSessionLeeren() throws RemoteException {

        //Create Session-EJB
        TvvSessionImplEJB tvvSessionImplEJB = new TvvSessionImplEJB();

        List<WarenkorbZeileDTO> actualWarenkorb = tvvSessionImplEJB.getWarenkorb();

        WarenkorbZeileDTO testTicket1 = new WarenkorbZeileDTO(401, Kategorie.SITZPLATZ.getName(), 1006, "Bruce Springsteen Konzert", 120, "1682002800");
        WarenkorbZeileDTO testTicket2 = new WarenkorbZeileDTO(402, Kategorie.SITZPLATZ.getName(), 1006, "Bruce Springsteen Konzert", 120, "1682002800");
        tvvSessionImplEJB.hinzufuegen(testTicket1);
        tvvSessionImplEJB.hinzufuegen(testTicket2);
        //Test-Warenkorb besitzt jetzt 2 Tickets
        assertEquals(2, actualWarenkorb.size());

        //Test-Warenkorb leeren
        tvvSessionImplEJB.leeren();

        //Test gültig, wennn gilt...
        assertEquals(0, actualWarenkorb.size());

    }

    @Test
    void ErstellteSessionMitInformationenBefuellen() throws RemoteException {

        //Create Session-EJB
        TvvSessionImplEJB tvvSessionImplEJB = new TvvSessionImplEJB();

        //Benutzername angeben
        String benutzername = "HelloWorld01";
        tvvSessionImplEJB.setBenutzerName(benutzername);

        //Rollen angeben
        List<String> rollen = new ArrayList<>();
        rollen.add(Rolle.MITARBEITER.getName());
        rollen.add(Rolle.OPERATOR.getName());

        tvvSessionImplEJB.setRollen(rollen);

        //Topics angeben
        List<String> topics = new ArrayList<>();
        topics.add(at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie.KINO.getName());
        topics.add(at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie.THEATER.getName());
        topics.add(at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie.KONZERT.getName());

        tvvSessionImplEJB.setTopics(topics);

        //Warenkorb befüllen
        WarenkorbZeileDTO testTicket1 = new WarenkorbZeileDTO(401, Kategorie.SITZPLATZ.getName(), 1006, "Bruce Springsteen Konzert", 120, "1682002800");
        WarenkorbZeileDTO testTicket2 = new WarenkorbZeileDTO(402, Kategorie.SITZPLATZ.getName(), 1006, "Bruce Springsteen Konzert", 120, "1682002800");
        tvvSessionImplEJB.hinzufuegen(testTicket1);
        tvvSessionImplEJB.hinzufuegen(testTicket2);

        //Zahlungsmethode hinzufügen
        String zahlungsmethode = Zahlungsmethode.KREDITKARTE.getName();
        tvvSessionImplEJB.hinzufuegenZahlungsMethode(zahlungsmethode);

        //Kunde hinzufügen
                    /* TEST-KUNDE
                    Vorname: Aiden
                    Nachname: Sorg
                    UUID: af3ce120-2059-4533-95d6-883f1567d376

                     */

        UUID kunde = UUID.fromString("af3ce120-2059-4533-95d6-883f1567d376");
        tvvSessionImplEJB.hinzufuegenKunde(kunde);


        //Test gültig, wennn gilt...
        assertEquals(benutzername, tvvSessionImplEJB.getBenutzerName());
        assertEquals(rollen, tvvSessionImplEJB.getRollen());
        assertEquals(topics, tvvSessionImplEJB.getTopics());
        assertEquals(2, tvvSessionImplEJB.getWarenkorb().size());
        assertEquals(testTicket1, tvvSessionImplEJB.getWarenkorb().get(0));
        assertEquals(testTicket2, tvvSessionImplEJB.getWarenkorb().get(1));
        assertEquals(zahlungsmethode, tvvSessionImplEJB.getZahlungsMethode());
        assertEquals(kunde, tvvSessionImplEJB.getKunde());

    }

}