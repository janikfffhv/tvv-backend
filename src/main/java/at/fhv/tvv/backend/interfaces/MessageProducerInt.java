package at.fhv.tvv.backend.interfaces;

import javax.ejb.Local;
import javax.jms.JMSException;
import java.rmi.Remote;
import java.rmi.RemoteException;

@Local
public interface MessageProducerInt {
    void produce(String topic, String title, String message) throws JMSException;
}
