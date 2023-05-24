package at.fhv.tvv.backend.application;

import at.fhv.tvv.backend.HibernateService;
import at.fhv.tvv.shared.dto.MessageDTO;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageConsumerImplTest {

    @Test
    void bekommeMessageVonMitarbeiterTFTEST() throws RemoteException {

        MessageConsumerImpl messageConsumer = (MessageConsumerImpl) HibernateService.messageConsumerImpl();

        List<MessageDTO> actualMessage = messageConsumer.getMessages("tf-test");
        boolean messageFound = false;

        for (MessageDTO message : actualMessage) {
            System.out.println("Title: " + message.getTitle()
            + "\n Topic: " + message.getTopicName()
            + "\n Content: " + message.getContent());
            if(message.getTopicName().equals("KONZERT") && message.getTitle().equals("tf-test") && message.getContent().equals("Nur ein Test")) {

                messageFound = true;
                break;

            }
        }

        //Test gültig wenn...
        assertTrue(messageFound);



    }

}