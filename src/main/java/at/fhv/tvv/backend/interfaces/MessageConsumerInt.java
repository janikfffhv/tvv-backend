package at.fhv.tvv.backend.interfaces;

import at.fhv.tvv.shared.dto.MessageDTO;

import javax.ejb.Local;
import javax.jms.JMSException;
import java.util.List;

@Local
public interface MessageConsumerInt {
    List<MessageDTO> getMessages(String userName) throws JMSException;

    boolean acknowledgeMessage(String userName, String id) throws JMSException;
}
