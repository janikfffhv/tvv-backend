package at.fhv.tvv.backend.domain.model.angestellte;

import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AngestellteTest {

    //Test für Angestellten-Objekt-Erstellung
    @Test
    void korrekteAngestelltenErstellungMitAllenRollenUndTopicsZugewiesen() {

        //Testdaten für Angestellten
        String benutzername = "testuser01";

        List<Rolle> rollen = new ArrayList<>();
        rollen.add(Rolle.MITARBEITER);
        rollen.add(Rolle.OPERATOR);

        List<Kategorie> topics = new ArrayList<>();
        topics.add(Kategorie.KINO);
        topics.add(Kategorie.KONZERT);
        topics.add(Kategorie.THEATER);


        //Angestellten aus Testdaten erzeugen
        Angestellte test = new Angestellte(benutzername, rollen, topics);


        //Test gültig, wenn...
        assertEquals(benutzername, test.getBenutzername());
        assertEquals(rollen, test.getRollen());
        assertEquals(topics, test.getTopics());


    }

    @Test
    void korrekteAngestelltenErstellungNurEinerRolleUndEinemTopic() {

        //Testdaten für Angestellten
        String benutzername = "testuser01";

        List<Rolle> rollen = new ArrayList<>();
        rollen.add(Rolle.OPERATOR);

        List<Kategorie> topics = new ArrayList<>();
        topics.add(Kategorie.KINO);


        //Angestellten aus Testdaten erzeugen
        Angestellte test = new Angestellte(benutzername, rollen, topics);


        //Test gültig, wenn...
        assertEquals(benutzername, test.getBenutzername());
        assertEquals(rollen, test.getRollen());
        assertEquals(topics, test.getTopics());


    }

    @Test
    void TopicsEinesAngestelltenAbaendern() {

        //Testdaten für Angestellten
        String benutzername = "testuser01";

        List<Rolle> rollen = new ArrayList<>();
        rollen.add(Rolle.MITARBEITER);

        List<Kategorie> topics = new ArrayList<>();
        topics.add(Kategorie.KINO);
        topics.add(Kategorie.KONZERT);
        topics.add(Kategorie.THEATER);


        //Angestellten aus Testdaten erzeugen
        Angestellte test = new Angestellte(benutzername, rollen, topics);


        //Topics verändern
        List<Kategorie> newTopics = new ArrayList<>();
        newTopics.add(Kategorie.THEATER);

        test.setTopics(newTopics);


        //Test gültig, wenn...
        assertNotEquals(topics, test.getTopics());
        assertEquals(newTopics, test.getTopics());

    }

}
