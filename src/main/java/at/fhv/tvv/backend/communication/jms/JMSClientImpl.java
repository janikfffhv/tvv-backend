package at.fhv.tvv.backend.communication.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.jms.*;

@Stateless
@Local(JMSClient.class)
public class JMSClientImpl implements JMSClient {
    private static final Logger logger = LogManager.getLogger(JMSClientImpl.class);

    private static final String ORDER_TOPIC_NAME = "Orders";
    private static final String ORDER_CLIENT_CONNECTION_ID = "system_ordering_client";
    private static final String ORDER_CLIENT_NAME = "ordering_client";
    private static final String PROTOCOL = "tcp://";
    private static final String HOST = "jms-provider";
    private static final String PORT = "61616";
    private TopicSession session;
    private TopicConnection connection;

    public JMSClientImpl() {
        this.reconnect();
    }

    @Override
    public void reconnect() {
        this.close();
        try {
            this.connectionFactory = new ActiveMQConnectionFactory(PROTOCOL + HOST + ":" + PORT);
            connection = connectionFactory.createTopicConnection();
            connection.start();
            session = connection.createTopicSession(false, Session.CLIENT_ACKNOWLEDGE);
            logger.info("Successfully connected to JMS-Provider at {}:{}", HOST, PORT);
        } catch (JMSException e) {
            e.printStackTrace();
            logger.warn("Failed to connect to JMS-Provider at {}:{}", HOST, PORT);
        }
    }
    @Override
    public void close() {
        try {
            if (session != null) {
                session.close();
            }
            if (connection != null) {
                connection.stop();
                connection.close();
            }
        } catch (JMSException e) {
            e.getLinkedException();
        }
    }

    @Override
    public void publishMessage(String topic, String message) throws JMSException {
        Destination destination = session.createTopic(topic);
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        TextMessage textMessage = session.createTextMessage(message);

        producer.send(textMessage);
        logger.info("Sent message: {} to topic {}", message, topic);
    }
}