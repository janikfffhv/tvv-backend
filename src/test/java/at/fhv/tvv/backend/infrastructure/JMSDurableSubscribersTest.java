package at.fhv.tvv.backend.infrastructure;

import at.fhv.tvv.backend.domain.model.angestellte.Angestellte;
import at.fhv.tvv.backend.domain.model.angestellte.Rolle;
import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JMSDurableSubscribersTest {

    @Test
    void createDurableSubscribers() {

        //Testdaten für Test-Angestellte erstellen
        String benutzername1 = "testuser01";
        String benutzername2 = "testuser02";

        List<Rolle> rollen = new ArrayList<>();
        rollen.add(Rolle.MITARBEITER);
        rollen.add(Rolle.OPERATOR);

        List<Kategorie> topics = new ArrayList<>();
        topics.add(Kategorie.KINO);
        topics.add(Kategorie.KONZERT);
        topics.add(Kategorie.THEATER);


        //Angestellte aus Testdaten erzeugen
        Angestellte test1 = new Angestellte(benutzername1, rollen, topics);
        Angestellte test2 = new Angestellte(benutzername2, rollen, topics);
        List<Angestellte> listAngestellte = new ArrayList<>();
        listAngestellte.add(test1);
        listAngestellte.add(test2);

        JMSDurableSubscribers test = new JMSDurableSubscribers();

        //Test gültig, wenn Methode erfolgreich durchgeführt wurde...
        assertDoesNotThrow(() -> test.createDurableSubscribers(listAngestellte));
    }

}