package at.fhv.tvv.backend.application;

import at.fhv.tvv.backend.HibernateService;
import at.fhv.tvv.backend.interfaces.MessageProducerInt;

import javax.ejb.Stateless;
import javax.jms.*;
import java.rmi.RemoteException;

@Stateless
public class MessageProducerImpl implements MessageProducerInt {

    private Connection connection;
    private Session session;

    public MessageProducerImpl() {

    }

    @Override
    public void produce(String topic, String title, String message) throws JMSException {
        try {
            connection = HibernateService.activeMQConnectionFactory().createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic(topic);
            TextMessage textMessage = session.createTextMessage(message);
            textMessage.setStringProperty("topic", topic);
            textMessage.setStringProperty("title", title);
            MessageProducer messageProducer = session.createProducer(destination);
            messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
            messageProducer.send(textMessage);
            session.close();
            connection.close();
        } catch (Exception e) {
            connection.close();
            e.printStackTrace();
        }

    }
}
