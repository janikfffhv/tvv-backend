package at.fhv.tvv.backend.communication;

import at.fhv.tvv.backend.interfaces.MessageProducerInt;
import at.fhv.tvv.shared.ejb.MessageProducer;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.JMSException;

@Stateless
public class MessageProducerEJB implements MessageProducer {
    @EJB
    private MessageProducerInt messageProducer;

    @Override
    public void produce(String topic, String title, String message) {
        try {
            messageProducer.produce(topic, title, message);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
