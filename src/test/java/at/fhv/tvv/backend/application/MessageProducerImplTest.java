package at.fhv.tvv.backend.application;

import at.fhv.tvv.backend.domain.model.angestellte.Angestellte;
import at.fhv.tvv.backend.domain.model.angestellte.Rolle;
import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie;
import at.fhv.tvv.backend.domain.repository.EventRepository;
import at.fhv.tvv.backend.infrastructure.EventRepositoryImpl;
import at.fhv.tvv.shared.dto.MessageDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.jms.JMSException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;


class MessageProducerImplTest {

    @Test
    void MessageVersendenAlsTfTest() throws JMSException {

        //MESSAGE PRODUCER ERSTELLEN
        MessageProducerImpl messageProducer = new MessageProducerImpl();

        //MESSAGE VERSENDEN
        String topic = Kategorie.KINO.getName();
        String title = "Hello From Remote";
        String message = "Dies ist eine Nachricht an ALLE KINOS!";

        messageProducer.produce(topic, title, message);

        //KONTROLLIEREN, OB NACHRICHT VERSCHICKT WURDE
        String username = "tf-test";

        boolean messageFound = messageSuchen(username, topic, title, message);

        //Test gültig, wenn gilt...
        assertTrue(messageFound);

    }

    boolean messageSuchen(String username, String topic, String title, String message) throws JMSException {

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
        List<MessageDTO> actualMessage = messageConsumerImpl.getMessages(username);
        boolean messageFound = false;

        for(MessageDTO m : actualMessage) {
            System.out.println("Title: " + m.getTitle()
                    + "\n Topic: " + m.getTopicName()
                    + "\n Content: " + m.getContent());

            if(m.getTopicName().equals(topic) && m.getTitle().equals(title) && m.getContent().equals(message)) {

                messageFound = true;
                break;

            }
        }

        return messageFound;

    }

}