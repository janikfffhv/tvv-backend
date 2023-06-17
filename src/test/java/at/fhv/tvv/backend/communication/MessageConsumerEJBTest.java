package at.fhv.tvv.backend.communication;

import at.fhv.tvv.backend.HibernateService;
import at.fhv.tvv.shared.dto.MessageDTO;
import at.fhv.tvv.shared.rmi.MessageConsumer;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageConsumerEJBTest {

    @Test
    void bekommeMessagesVonMitarbeiterTFTEST() {

        //Test-MessageConsumer erstellen
        MessageConsumerEJB messageConsumerEJB = new MessageConsumerEJB();

        //GetMessages von Mitarbeiter tf-test
        List<MessageDTO> actualMessages = messageConsumerEJB.getMessages("tf-test");

        for(MessageDTO message : actualMessages) {
            System.out.println("Title: " + message.getTitle()
                    + "\nTopic: " + message.getTopicName()
                    + "\nContent: " + message.getContent());
        }

        boolean messageFound = false;
        for(MessageDTO message : actualMessages) {

            if(message.getTopicName().equals("KONZERT") && message.getTitle().equals("Test-Konzert1") && message.getContent().equals("Eine Testnachricht über ein Konzert!")) {

                messageFound = true;
                break;

            }

        }

        //Test gültig, wenn gilt...
        assertTrue(messageFound);

    }

    @Test
    void markiereMessageAlsGelesen() {

        //Test-MessageConsumer erstellen
        MessageConsumerEJB messageConsumerEJB = new MessageConsumerEJB();

        //GetMessages von Mitarbeiter tf-test
        List<MessageDTO> actualMessages = messageConsumerEJB.getMessages("tf-test");

        for(MessageDTO message : actualMessages) {
            System.out.println("Title: " + message.getTitle()
                    + "\nTopic: " + message.getTopicName()
                    + "\nContent: " + message.getContent());
        }

        String messageID = actualMessages.get(0).getId();

        //Message bestätigen
        Boolean bestaetigt = messageConsumerEJB.acknowledgeMessage("tf-test", messageID);

        //Test gültig, wenn gilt...
        assertTrue(bestaetigt);

    }


}