package at.fhv.tvv.backend.communication.jms;

import javax.jms.JMSException;

public interface JMSClient {
    void createOrderSubscriber();

    void reconnect();

    void close();

    void publishMessage(String topic, String message) throws JMSException;
}
