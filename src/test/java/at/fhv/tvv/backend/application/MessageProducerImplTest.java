package at.fhv.tvv.backend.application;

import at.fhv.tvv.backend.HibernateService;
import at.fhv.tvv.shared.dto.MessageDTO;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageProducerImplTest {

    @Test
    void neueMessageAnTopicSystemSenden() throws RemoteException {

        MessageProducerImpl messageProducer = (MessageProducerImpl) HibernateService.messageProducerImpl();

        //Nachricht
        String topic = "THEATER";
        String title = "Ein Test für Producer";
        String content = "Hat der Test geklappt?";

        messageProducer.produce(topic, title, content);

        MessageConsumerImpl messageConsumer = (MessageConsumerImpl) HibernateService.messageConsumerImpl();

        List<MessageDTO> actualMessage = messageConsumer.getMessages("tf-test");

        boolean messageFound = false;

        for(MessageDTO message : actualMessage) {
            System.out.println("Title: " + message.getTitle()
            + "\n Topic: " + message.getTopicName()
            + "\n Content: " + message.getContent());

            if(message.getTopicName().equals(topic) && message.getTitle().equals(title) && message.getContent().equals(content)) {

                messageFound = true;
                break;

            }
        }

        // Test gültig wenn
        assertTrue(messageFound);

    }


}