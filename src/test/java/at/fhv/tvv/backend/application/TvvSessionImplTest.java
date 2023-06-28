package at.fhv.tvv.backend.application;

import at.fhv.tvv.backend.domain.model.angestellte.Rolle;
import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie;
import at.fhv.tvv.shared.dto.WarenkorbZeileDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TvvSessionImplTest {

    @Test
    void ticketsInWarenkorbLegenUndWarenkorbAusgeben() {

        TvvSessionImpl tvvSession = new TvvSessionImpl();

        //TEST-DATEN
        int datum = (int) (new Date("21/10/2023").getTime()/1000);
        WarenkorbZeileDTO ticket1 = new WarenkorbZeileDTO(805, "STEHPLATZ", 1013, "Theater Patati-Patata präsentiert: Alex und die gelbe Maus",11.99F, Integer.toString(datum));
        WarenkorbZeileDTO ticket2 = new WarenkorbZeileDTO(806, "STEHPLATZ", 1013, "Theater Patati-Patata präsentiert: Alex und die gelbe Maus",11.99F, Integer.toString(datum));
        WarenkorbZeileDTO ticket3 = new WarenkorbZeileDTO(807, "STEHPLATZ", 1013, "Theater Patati-Patata präsentiert: Alex und die gelbe Maus",11.99F, Integer.toString(datum));

        //TICKETS IN WARENKORB LEGEN
        tvvSession.hinzufuegen(ticket1);
        tvvSession.hinzufuegen(ticket2);
        tvvSession.hinzufuegen(ticket3);

        //WARENKORB HOLEN UND AUSGEBEN
        List<WarenkorbZeileDTO> actualTickets = tvvSession.getWarenkorb();
        for(WarenkorbZeileDTO ticket : actualTickets) {
            System.out.println("PlatzID: " + ticket.getPlatzId());
        }

        //Test gültig, wenn gilt...
        assertEquals(3, actualTickets.size());
        assertEquals(ticket1.getPlatzId(), actualTickets.get(0).getPlatzId());
        assertEquals(ticket2.getPlatzId(), actualTickets.get(1).getPlatzId());
        assertEquals(ticket3.getPlatzId(), actualTickets.get(2).getPlatzId());

    }

    @Test
    void DreiTicketsInWarenkorbLegenDanachEinsLoeschenUndDanachWarenkorbAusgeben() {

        TvvSessionImpl tvvSession = new TvvSessionImpl();

        //TEST-DATEN
        int datum = (int) (new Date("21/10/2023").getTime()/1000);
        WarenkorbZeileDTO ticket1 = new WarenkorbZeileDTO(805, "STEHPLATZ", 1013, "Theater Patati-Patata präsentiert: Alex und die gelbe Maus",11.99F, Integer.toString(datum));
        WarenkorbZeileDTO ticket2 = new WarenkorbZeileDTO(806, "STEHPLATZ", 1013, "Theater Patati-Patata präsentiert: Alex und die gelbe Maus",11.99F, Integer.toString(datum));
        WarenkorbZeileDTO ticket3 = new WarenkorbZeileDTO(807, "STEHPLATZ", 1013, "Theater Patati-Patata präsentiert: Alex und die gelbe Maus",11.99F, Integer.toString(datum));

        //TICKETS IN WARENKORB LEGEN
        tvvSession.hinzufuegen(ticket1);
        tvvSession.hinzufuegen(ticket2);
        tvvSession.hinzufuegen(ticket3);

        //TICKET LÖSCHEN AUS WARENKORB
        tvvSession.loeschen(ticket1);

        //WARENKORB HOLEN UND AUSGEBEN
        List<WarenkorbZeileDTO> actualTickets = tvvSession.getWarenkorb();
        for(WarenkorbZeileDTO ticket : actualTickets) {
            System.out.println("PlatzID: " + ticket.getPlatzId());
        }

        //Test gültig, wenn gilt...
        assertEquals(2, actualTickets.size());
        assertEquals(ticket2.getPlatzId(), actualTickets.get(0).getPlatzId());
        assertEquals(ticket3.getPlatzId(), actualTickets.get(1).getPlatzId());

    }

    @Test
    void TicketsInWarenkorbLegenDanachWarenkorbLeerenUndDanachWarenkorbAusgeben() {

        TvvSessionImpl tvvSession = new TvvSessionImpl();

        //TEST-DATEN
        int datum = (int) (new Date("21/10/2023").getTime()/1000);
        WarenkorbZeileDTO ticket1 = new WarenkorbZeileDTO(805, "STEHPLATZ", 1013, "Theater Patati-Patata präsentiert: Alex und die gelbe Maus",11.99F, Integer.toString(datum));
        WarenkorbZeileDTO ticket2 = new WarenkorbZeileDTO(806, "STEHPLATZ", 1013, "Theater Patati-Patata präsentiert: Alex und die gelbe Maus",11.99F, Integer.toString(datum));


        //TICKETS IN WARENKORB LEGEN
        tvvSession.hinzufuegen(ticket1);
        tvvSession.hinzufuegen(ticket2);

        //TICKET LÖSCHEN AUS WARENKORB
        tvvSession.leeren();

        //WARENKORB HOLEN UND AUSGEBEN
        List<WarenkorbZeileDTO> actualTickets = tvvSession.getWarenkorb();
        for(WarenkorbZeileDTO ticket : actualTickets) {
            System.out.println("PlatzID: " + ticket.getPlatzId());
        }

        //Test gültig, wenn gilt...
        assertEquals(0, actualTickets.size());

    }

    @Test
    void RollenDerTVVSessionZuweisenUndRollenAusgeben() {

        TvvSessionImpl tvvSession = new TvvSessionImpl();

        //TEST-DATEN
        List<String> rollen = new ArrayList<>();
        rollen.add(Rolle.MITARBEITER.getName());
        rollen.add(Rolle.OPERATOR.getName());

        //ROLLEN DER SESSION ZUWEISEN
        tvvSession.setRollen(rollen);

        //ROLLEN DER SESSION EINHOLEN UND AUSGEBEN
        List<String> actualRollen = tvvSession.getRollen();
        System.out.println("Rollen: ");
        for(String rolle : actualRollen) {
            System.out.println(rolle);
        }

        //Test gültig, wenn gilt...
        assertEquals(2, actualRollen.size());
        assertEquals(Rolle.MITARBEITER.getName(), actualRollen.get(0));
        assertEquals(Rolle.OPERATOR.getName(), actualRollen.get(1));

    }

    @Test
    void TopicsDerTVVSessionZuweisenUndTopicsAusgeben() {

        TvvSessionImpl tvvSession = new TvvSessionImpl();

        //TEST-DATEN
        List<String> topics = new ArrayList<>();
        topics.add(Kategorie.KINO.getName());
        topics.add(Kategorie.THEATER.getName());
        topics.add(Kategorie.KONZERT.getName());


        //TOPICS DER SESSION ZUWEISEN
        tvvSession.setTopics(topics);

        //Topics DER SESSION EINHOLEN UND AUSGEBEN
        List<String> actualTopics = tvvSession.getTopics();
        System.out.println("Topics: ");
        for(String topic : actualTopics) {
            System.out.println(topic);
        }

        //Test gültig, wenn gilt...
        assertEquals(3, actualTopics.size());
        assertEquals(Kategorie.KINO.getName(), actualTopics.get(0));
        assertEquals(Kategorie.THEATER.getName(), actualTopics.get(1));
        assertEquals(Kategorie.KONZERT.getName(), actualTopics.get(2));

    }

    @Test
    void benutzernameDerTVVSessionHinzufuegenUndAusgeben() {

        TvvSessionImpl tvvSession = new TvvSessionImpl();

        //TEST-DATEN
        String username = "tf-test";

        //BENUTZER DER SESSION ZUWEISEN
        tvvSession.setBenutzerName(username);

        //USERNAMEN EINHOLEN UND AUSGEBEN
        String actualUser = tvvSession.getBenutzerName();
        System.out.println("Username: " + actualUser);

        //Test gültig, wenn gilt...
        assertEquals(username, actualUser);

    }

    @Test
    void kundenIdEinerTVVSessionHinzufuegenUndAusgeben() {

        TvvSessionImpl tvvSession = new TvvSessionImpl();

        //TEST-DATEN
        UUID kundenId = UUID.fromString("0c28d094-4c31-4f05-ad74-2d97af7aaf04");

        //KUNDE DER SESSION ZUWEISEN
        tvvSession.hinzufuegenKunde(kundenId);

        //KUNDEN-ID EINHOLEN UND AUSGEBEN
        UUID actualKunde = tvvSession.getKunde();
        System.out.println("Kunde: " + actualKunde.toString());

        //Test gültig, wenn gilt...
        assertEquals(kundenId, actualKunde);

    }

    @Test
    void zahlungsmethodeEinerTVVSessionHinzufuegenUndAusgeben() {

        TvvSessionImpl tvvSession = new TvvSessionImpl();

        //TEST-DATEN
        String zahlungsmethode = "RECHNUNG";

        //ZAHLUNGSMETHODE DER SESSION ZUWEISEN
        tvvSession.hinzufuegenZahlungsMethode(zahlungsmethode);

        //ZAHLUNGSMETHODE EINHOLEN UND AUSGEBEN
        String actualZahlungsmethode = tvvSession.getZahlungsMethode();
        System.out.println("Zahlungsmethode: " + actualZahlungsmethode);

        //Test gültig, wenn gilt...
        assertEquals(zahlungsmethode, actualZahlungsmethode);

    }

}