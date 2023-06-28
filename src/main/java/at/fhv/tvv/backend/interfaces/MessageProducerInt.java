package at.fhv.tvv.backend.interfaces;

import javax.ejb.Local;
import javax.jms.JMSException;

@Local
public interface MessageProducerInt {
    void produce(String topic, String title, String message) throws JMSException;
}
