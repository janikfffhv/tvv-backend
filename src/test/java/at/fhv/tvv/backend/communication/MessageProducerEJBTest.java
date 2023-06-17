package at.fhv.tvv.backend.communication;

import at.fhv.tvv.backend.HibernateService;
import at.fhv.tvv.shared.dto.MessageDTO;
import at.fhv.tvv.shared.rmi.MessageConsumer;
import at.fhv.tvv.shared.rmi.MessageProducer;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageProducerEJBTest {

    @Test
    void neueMessageAnTopicSystemSenden() throws RemoteException {

        //Test-MessageProducer erstellen
        MessageProducerEJB messageProducerEJB = new MessageProducerEJB();

        //Neue Nachricht an Topic System senden
        String topic = "THEATER";
        String title = "Wiederverwertbare Nachricht";
        String content = "Diese Nachricht taucht immer mal wieder auf...";

        messageProducerEJB.produce(topic, title, content);

        //Test-MessageConsumer erstellen
        MessageConsumerEJB messageConsumerEJB = new MessageConsumerEJB();

        //GetMessages von Mitarbeiter tf-test
        List<MessageDTO> actualMessages = messageConsumerEJB.getMessages("tf-test");

        boolean messageFound = false;

        for(MessageDTO message : actualMessages) {
            System.out.println("Title: " + message.getTitle()
                    + "\nTopic: " + message.getTopicName()
                    + "\nContent: " + message.getContent());
        }

        for(MessageDTO message : actualMessages) {

            if(message.getTopicName().equals(topic) && message.getTitle().equals(title) && message.getContent().equals(content)) {

                messageFound = true;
                break;

            }

        }

        //Test gültig, wenn gilt...
        assertTrue(messageFound);

    }

}