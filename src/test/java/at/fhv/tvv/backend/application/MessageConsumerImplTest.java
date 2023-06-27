package at.fhv.tvv.backend.application;

import at.fhv.tvv.backend.domain.model.angestellte.Angestellte;
import at.fhv.tvv.backend.domain.model.angestellte.Rolle;
import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie;
import at.fhv.tvv.backend.domain.repository.EventRepository;
import at.fhv.tvv.backend.infrastructure.EventRepositoryImpl;
import at.fhv.tvv.shared.dto.MessageDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.jms.JMSException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MessageConsumerImplTest {

    @Test
    void MessageConsumerImplKorrektInitialisieren() {
        MessageConsumerImpl messageConsumer = new MessageConsumerImpl();

        //Test gültig, wenn gilt...
        assertEquals(MessageConsumerImpl.class, messageConsumer.getClass());
    }

    @Test
    void bekommeMessageVonMitarbeiterTfTest() throws JMSException {

        String username = "tf-test";

        //MOCKING
        EventRepository eventRepositoryMock = Mockito.mock(EventRepositoryImpl.class);
        MessageConsumerImpl messageConsumerImpl = new MessageConsumerImpl(eventRepositoryMock);

        List<Rolle> rollen = new ArrayList<>();
        rollen.add(Rolle.MITARBEITER);
        rollen.add(Rolle.OPERATOR);
        List<Kategorie> topics = new ArrayList<>();
        topics.add(Kategorie.KINO);
        topics.add(Kategorie.KONZERT);
        topics.add(Kategorie.THEATER);
        Optional<Angestellte> expectedAngestellter = Optional.of(new Angestellte(username, rollen, topics));

        Mockito.when(eventRepositoryMock.getAngestellerById(username)).thenReturn(expectedAngestellter);

        //MESSAGE SUCHEN
        List<MessageDTO> actualMessage = messageConsumerImpl.getMessages("tf-test");
        boolean messageFound = false;

        for (MessageDTO message : actualMessage) {
            System.out.println("Title: " + message.getTitle()
                    + "\n Topic: " + message.getTopicName()
                    + "\n Content: " + message.getContent());
            if(message.getTopicName().equals("KONZERT") && message.getTitle().equals("Test-Konzert1") && message.getContent().equals("Eine Testnachricht über ein Konzert!")) {

                messageFound = true;
                break;

            }
        }

        //Test gültig wenn...
        assertTrue(messageFound);

    }

    @Test
    void wennNichtExistierenderUserAngegebenWirdSollNichtsAusgebenWerden() throws JMSException {

        String username = "tf-test";

        //MOCKING
        EventRepository eventRepositoryMock = Mockito.mock(EventRepositoryImpl.class);
        MessageConsumerImpl messageConsumerImpl = new MessageConsumerImpl(eventRepositoryMock);

        List<Rolle> rollen = new ArrayList<>();
        rollen.add(Rolle.MITARBEITER);
        rollen.add(Rolle.OPERATOR);
        List<Kategorie> topics = new ArrayList<>();
        topics.add(Kategorie.KINO);
        topics.add(Kategorie.KONZERT);
        topics.add(Kategorie.THEATER);
        Optional<Angestellte> expectedAngestellter = Optional.of(new Angestellte(username, rollen, topics));

        Mockito.when(eventRepositoryMock.getAngestellerById(username)).thenReturn(expectedAngestellter);

        //MESSAGE SUCHEN
        List<MessageDTO> actualMessage = messageConsumerImpl.getMessages("NichtExistent");
        boolean messageFound = false;

        for (MessageDTO message : actualMessage) {
            System.out.println("Title: " + message.getTitle()
                    + "\n Topic: " + message.getTopicName()
                    + "\n Content: " + message.getContent());
            if(message.getTopicName().equals("KONZERT") && message.getTitle().equals("Test-Konzert1") && message.getContent().equals("Eine Testnachricht über ein Konzert!")) {

                messageFound = true;
                break;

            }
        }

        //Test gültig wenn...
        assertFalse(messageFound);
        assertEquals(0, actualMessage.size());

    }

    @Test
    void nachrichtAcknowledgenVonTfTest() {

        //TODO

    }

}