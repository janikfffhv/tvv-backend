package at.fhv.tvv.backend.application;

import at.fhv.tvv.backend.HibernateService;
import at.fhv.tvv.backend.communication.TvvSessionImplRMI;
import at.fhv.tvv.backend.domain.model.angestellte.Rolle;
import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie;
import at.fhv.tvv.backend.domain.model.verkauf.Zahlungsmethode;
import at.fhv.tvv.shared.dto.WarenkorbZeileDTO;
import at.fhv.tvv.shared.rmi.TvvSession;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TvvSessionImplTest {

    @Test
    void TvvSessionImplKorrektInitialisieren() throws RemoteException {
        System.out.println("-------------------------------------Test 1 TvvSessionImplKorrektInitilisieren-------------------------");
        TvvSessionFactoryImpl tvvSessionFactory = (TvvSessionFactoryImpl) HibernateService.tvvSessionFactoryImpl();

        TvvSession test = tvvSessionFactory.createSession();

        assertEquals(TvvSessionImplRMI.class, test.getClass());
    }

    @Test
    void getWarenkorbVonBestehenderSession() throws RemoteException {
        System.out.println("-------------------------------------Test 2 getWarenkorbVonBestehenderSession-------------------------");
        TvvSessionFactoryImpl tvvSessionFactory = (TvvSessionFactoryImpl) HibernateService.tvvSessionFactoryImpl();

        TvvSession test = tvvSessionFactory.createSession();

        List<WarenkorbZeileDTO> actualWarenkorb = test.getWarenkorb();

        assertEquals(TvvSessionImplRMI.class, test.getClass());
        assertEquals(0, actualWarenkorb.size());
    }

    @Test
    void EinenArtikelInWarenkorbHinzufügen() throws RemoteException {
        System.out.println("-------------------------------------Test 3 EinenArtikelInWarenkorbHinzufügen-------------------------");
        TvvSessionFactoryImpl tvvSessionFactory = (TvvSessionFactoryImpl) HibernateService.tvvSessionFactoryImpl();

        TvvSession test = tvvSessionFactory.createSession();

        List<WarenkorbZeileDTO> actualWarenkorb = test.getWarenkorb();
        WarenkorbZeileDTO testT1 = new WarenkorbZeileDTO(2,"sitzplatz", 10, "Bruce", (float) 10.51, "12052023" );

        test.hinzufuegen(testT1);
        assertEquals(TvvSessionImplRMI.class, test.getClass());
        assertEquals(1, actualWarenkorb.size());
    }

    @Test
    void EinenArtikelAusWarenkorbEntfernen() throws RemoteException {
        System.out.println("-------------------------------------Test 4 EinenArtikelAusWarenkorbEntfernen-------------------------");
        TvvSessionFactoryImpl tvvSessionFactory = (TvvSessionFactoryImpl) HibernateService.tvvSessionFactoryImpl();

        TvvSession test = tvvSessionFactory.createSession();

        List<WarenkorbZeileDTO> actualWarenkorb = test.getWarenkorb();
        WarenkorbZeileDTO testT1 = new WarenkorbZeileDTO(2,"sitzplatz", 10, "Bruce", (float) 10.51, "12052023" );

        assertEquals(0, actualWarenkorb.size());
        test.hinzufuegen(testT1);
        assertEquals(1, actualWarenkorb.size());

        test.loeschen(testT1);
        assertEquals(TvvSessionImplRMI.class, test.getClass());
        assertEquals(0, actualWarenkorb.size());


    }

    @Test
    void WarenkorbDerSessionLeerenSodassKeineTicketsMehrDrinnenSind() throws RemoteException {
        System.out.println("-------------------------------------Test 5 WarenkorbDerSessionLeerenSodassKeineTicketsMehrDrinnenSind-------------------------");
        TvvSessionFactoryImpl tvvSessionFactory = (TvvSessionFactoryImpl) HibernateService.tvvSessionFactoryImpl();

        TvvSession test = tvvSessionFactory.createSession();

        List<WarenkorbZeileDTO> actualWarenkorb = test.getWarenkorb();
        WarenkorbZeileDTO testT1 = new WarenkorbZeileDTO(2,"sitzplatz", 10, "Bruce", (float) 10.51, "12052023" );
        WarenkorbZeileDTO testT2 = new WarenkorbZeileDTO(2,"sitzplatz", 10, "Bruce", (float) 10.51, "12052023" );
        WarenkorbZeileDTO testT3 = new WarenkorbZeileDTO(2,"sitzplatz", 10, "Bruce", (float) 10.51, "12052023" );

        assertEquals(0, actualWarenkorb.size());
        test.hinzufuegen(testT1);
        test.hinzufuegen(testT2);
        test.hinzufuegen(testT3);
        assertEquals(3, actualWarenkorb.size());

        test.loeschen(testT1);
        assertEquals(TvvSessionImplRMI.class, test.getClass());
        assertEquals(2, actualWarenkorb.size());

        test.leeren();
        assertEquals(0, actualWarenkorb.size());

    }

    @Test
    void ErstellteSessionMitInformationenBefuellen() throws RemoteException {
        System.out.println("-------------------------------------Test 6 ErstellteSessionMitInformationenBefuellen -------------------------");
        TvvSessionFactoryImpl tvvSessionFactory = (TvvSessionFactoryImpl) HibernateService.tvvSessionFactoryImpl();

        TvvSession test = tvvSessionFactory.createSession();

        //Benutzername angeben
        String benutzername = "Testuser";
        test.setBenutzerName(benutzername);

        //Rollen angeben
        List<String> rollen = new ArrayList<>();
        rollen.add(Rolle.MITARBEITER.getName());
        rollen.add(Rolle.OPERATOR.getName());

        test.setRollen(rollen);

        //Topics
        List<String> topics = new ArrayList<>();
        topics.add(Kategorie.KINO.getName());
        topics.add(Kategorie.THEATER.getName());
        topics.add(Kategorie.KONZERT.getName());

        test.setTopics(topics);

        //Warenkorb befüllen
        WarenkorbZeileDTO testT1 = new WarenkorbZeileDTO(2,"sitzplatz", 10, "Bruce", (float) 10.51, "12052023" );
        WarenkorbZeileDTO testT2 = new WarenkorbZeileDTO(2,"sitzplatz", 10, "Bruce", (float) 10.51, "12052023" );
        WarenkorbZeileDTO testT3 = new WarenkorbZeileDTO(2,"sitzplatz", 10, "Bruce", (float) 10.51, "12052023" );

        test.hinzufuegen(testT1);
        test.hinzufuegen(testT2);
        test.hinzufuegen(testT3);

        //Zahlungsmethode
        String zahlmethode = Zahlungsmethode.KREDITKARTE.getName();
        test.hinzufuegenZahlungsMethode(zahlmethode);

        //Kunde hinzufügen
        /*Testkunde
        Vorname: Alden
        Nachname: Sorg
        UUID: af3ce120-2059-4533-95d6-883f1567d376
         */

        UUID kunde = UUID.fromString("af3ce120-2059-4533-95d6-883f1567d376");
        test.hinzufuegenKunde(kunde);

        //Test gültig wenn..
        assertEquals(benutzername, test.getBenutzerName());
        assertEquals(rollen, test.getRollen());
        assertEquals(3, test.getWarenkorb().size());
        assertEquals(testT1, test.getWarenkorb().get(0));
        assertEquals(testT2, test.getWarenkorb().get(1));
        assertEquals(zahlmethode, test.getZahlungsMethode());
        assertEquals(kunde, test.getKunde());
        assertEquals(topics, test.getTopics());

    }


}