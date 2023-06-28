package at.fhv.tvv.backend.application;

import at.fhv.tvv.backend.domain.model.angestellte.Angestellte;
import at.fhv.tvv.backend.domain.model.angestellte.Rolle;
import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie;
import at.fhv.tvv.backend.domain.repository.EventRepository;
import at.fhv.tvv.backend.infrastructure.EventRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RolesTopicsImplTest {

    @Test
    void rolesTopicsImplKorrektInitialisieren() {

        RolesTopicsImpl test = new RolesTopicsImpl();

        //Test gültig, wenn gilt...
        assertEquals(RolesTopicsImpl.class, test.getClass());

    }

    @Test
    void alleRollenEinholen() {

        //TEST-DATEN
        String username = "tf-test";

        List<Rolle> rollen = new ArrayList<>();
        rollen.add(Rolle.MITARBEITER);
        rollen.add(Rolle.OPERATOR);
        List<Kategorie> topics = new ArrayList<>();
        topics.add(Kategorie.KINO);
        topics.add(Kategorie.KONZERT);
        topics.add(Kategorie.THEATER);

        Optional<Angestellte> expectedAngestellter = Optional.of(new Angestellte(username, rollen, topics));

        //MOCKING
        EventRepository eventRepositoryMock = Mockito.mock(EventRepositoryImpl.class);
        RolesTopicsImpl rolesTopicsImpl = new RolesTopicsImpl(eventRepositoryMock);

        Mockito.when(eventRepositoryMock.getAngestellerById(username)).thenReturn(expectedAngestellter);

        //ROLLEN EINHOLEN UND AUSGEBEN
        List<String> actualRoles = rolesTopicsImpl.getRoles(username);
        System.out.println("Rollen:");
        for(String role : actualRoles) {
            System.out.println(role);
        }

        //Test gültig, wenn gilt...
        assertEquals(2, actualRoles.size());
        assertEquals(Rolle.MITARBEITER.getName(), actualRoles.get(0));
        assertEquals(Rolle.OPERATOR.getName(), actualRoles.get(1));

    }

    @Test
    void alleTopicsEinholen() {

        //TEST-DATEN
        String username = "tf-test";

        List<Rolle> rollen = new ArrayList<>();
        rollen.add(Rolle.MITARBEITER);
        rollen.add(Rolle.OPERATOR);
        List<Kategorie> topics = new ArrayList<>();
        topics.add(Kategorie.KINO);
        topics.add(Kategorie.KONZERT);
        topics.add(Kategorie.THEATER);

        Optional<Angestellte> expectedAngestellter = Optional.of(new Angestellte(username, rollen, topics));

        //MOCKING
        EventRepository eventRepositoryMock = Mockito.mock(EventRepositoryImpl.class);
        RolesTopicsImpl rolesTopicsImpl = new RolesTopicsImpl(eventRepositoryMock);

        Mockito.when(eventRepositoryMock.getAngestellerById(username)).thenReturn(expectedAngestellter);

        //TOPICS EINHOLEN UND AUSGEBEN
        List<String> actualTopics = rolesTopicsImpl.getTopics(username);
        System.out.println("Topics:");
        for(String topic : actualTopics) {
            System.out.println(topic);
        }

        //Test gültig, wenn gilt...
        assertEquals(3, actualTopics.size());
        assertEquals(Kategorie.KINO.getName(), actualTopics.get(0));
        assertEquals(Kategorie.KONZERT.getName(), actualTopics.get(1));
        assertEquals(Kategorie.THEATER.getName(), actualTopics.get(2));

    }

    @Test
    void abonnierteTopicsVonTfTestAendern() {

        boolean changeSuccessful = false;

        //TEST-DATEN
        String username = "tf-test";

        List<Rolle> rollen = new ArrayList<>();
        rollen.add(Rolle.MITARBEITER);
        rollen.add(Rolle.OPERATOR);
        List<Kategorie> topics = new ArrayList<>();
        topics.add(Kategorie.KINO);
        topics.add(Kategorie.KONZERT);
        topics.add(Kategorie.THEATER);

        Optional<Angestellte> expectedAngestellter = Optional.of(new Angestellte(username, rollen, topics));

        //MOCKING
        EventRepository eventRepositoryMock = Mockito.mock(EventRepositoryImpl.class);
        RolesTopicsImpl rolesTopicsImpl = new RolesTopicsImpl(eventRepositoryMock);

        Mockito.when(eventRepositoryMock.getAngestellerById(username)).thenReturn(expectedAngestellter);
        Mockito.doNothing().when(eventRepositoryMock).updateAngestellter(expectedAngestellter.get());

        //TOPICS VON tf-test ABÄNDERN
        List<String> neueTopics = new ArrayList<>();
        neueTopics.add("Kino");

        rolesTopicsImpl.setTopics(neueTopics, username);
        changeSuccessful = true;


        //Test gültig, wenn gilt...
        assertTrue(changeSuccessful);

    }

}