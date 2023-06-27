package at.fhv.tvv.backend.application;

import at.fhv.tvv.backend.HibernateService;
import at.fhv.tvv.backend.domain.model.angestellte.Angestellte;
import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie;
import at.fhv.tvv.backend.domain.repository.EventRepository;
import at.fhv.tvv.backend.interfaces.MessageConsumerInt;
import at.fhv.tvv.shared.dto.MessageDTO;
import at.fhv.tvv.shared.rmi.MessageConsumer;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQSession;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Stateless
public class MessageConsumerImpl implements MessageConsumerInt {

    private ActiveMQConnection connection;
    private ActiveMQSession session;

    @EJB
    private EventRepository eventRepository;

    public MessageConsumerImpl() {

    }

    public MessageConsumerImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<MessageDTO> getMessages(String s) throws JMSException {
       List<MessageDTO> messages = new ArrayList<>();
        try {
            connection = (ActiveMQConnection) HibernateService.activeMQConnectionFactory().createConnection();
            connection.setClientID("client");
            connection.start();
            session = (ActiveMQSession) connection.createSession(false, ActiveMQSession.CLIENT_ACKNOWLEDGE);
            List<TextMessage> textMessageList = new ArrayList<>();
            System.out.println(s);
            Optional<Angestellte> angestellter = eventRepository.getAngestellerById(s);
            if(angestellter.isEmpty()) {
                connection.close();
                throw new IllegalArgumentException("Angestellter konnte nicht gefunden werden!");
            }
            System.out.println("LÄUFT!!!" + s);
            for(Kategorie kat : angestellter.get().getTopics()) {
                Topic topic = session.createTopic(kat.getName());
                TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, angestellter.get().getBenutzername()+topic.getTopicName());
                Message message;
                while((message = topicSubscriber.receiveNoWait()) != null) {
                    textMessageList.add((TextMessage) message);

                }
                topicSubscriber.close();
            }
            Collections.reverse(textMessageList);
            for(TextMessage messageToDTO : textMessageList) {
                messages.add(new MessageDTO(messageToDTO.getStringProperty("topic"), messageToDTO.getStringProperty("title"), messageToDTO.getText(), messageToDTO.getJMSMessageID()));
            }
            session.close();
            connection.close();
        } catch (Exception e) {
            connection.close();
            e.printStackTrace();
        }
        return messages;
    }
    @Override
    public boolean acknowledgeMessage(String userName, String id) throws JMSException {
        try {
            connection = (ActiveMQConnection) HibernateService.activeMQConnectionFactory().createConnection();
            connection.setClientID("client");
            connection.start();
            session = (ActiveMQSession) connection.createSession(false, ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE);
            Optional<Angestellte> angestellter = eventRepository.getAngestellerById(userName);
            if(angestellter.isEmpty()) {
                connection.close();
                throw new IllegalArgumentException("Angestellter konnte nicht gefunden werden!");
            }
            System.out.println("ACKNOWLEDGEMENT" + userName + ", ID: " + id);
            for(Kategorie kat : angestellter.get().getTopics()) {
                Topic topic = session.createTopic(kat.getName());
                Message message;
                TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, angestellter.get().getBenutzername()+topic.getTopicName());
                while((message = topicSubscriber.receiveNoWait()) != null) {
                    TextMessage message1 = (TextMessage) message;
                    System.out.println(message1.getJMSMessageID());
                    if(message1.getJMSMessageID().equals(id)) {
                        System.out.println("ACKNOWLEDGMENT MESSAGE: " + message1.getText());
                        message1.acknowledge();
                    }

                }
                topicSubscriber.close();
            }
            session.close();
            connection.close();
            return true;
        } catch (Exception e) {
            connection.close();
            e.printStackTrace();
            return false;
        }
    }
}
