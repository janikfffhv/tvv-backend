package at.fhv.tvv.backend.communication;

import at.fhv.tvv.backend.HibernateService;
import at.fhv.tvv.shared.dto.MessageDTO;
import at.fhv.tvv.shared.rmi.MessageConsumer;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageConsumerRMITest {

    @Test
    void bekommeMessagesVonMitarbeiterTFTEST() throws RemoteException {

        //Test-MessageConsumer erstellen
        MessageConsumer messageConsumerRMI = HibernateService.messageConsumerRMI();

        //GetMessages von Mitarbeiter tf-test
        List<MessageDTO> actualMessages = messageConsumerRMI.getMessages("tf-test");

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

}