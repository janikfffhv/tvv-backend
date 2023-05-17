package at.fhv.tvv.backend.communication;

import at.fhv.tvv.backend.interfaces.MessageConsumerInt;
import at.fhv.tvv.shared.dto.MessageDTO;
import at.fhv.tvv.shared.ejb.MessageConsumer;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import java.util.List;

@Stateless
public class MessageConsumerEJB implements MessageConsumer {
    @EJB
    private MessageConsumerInt messageConsumer;
    @Override
    public List<MessageDTO> getMessages(String userName) {
        try {
            return messageConsumer.getMessages(userName);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
