package at.fhv.tvv.backend.application;

import at.fhv.tvv.backend.HibernateService;
import at.fhv.tvv.backend.domain.model.angestellte.Angestellte;
import at.fhv.tvv.backend.domain.model.event.Event;
import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie;
import at.fhv.tvv.backend.domain.repository.EventRepository;
import at.fhv.tvv.shared.dto.MessageDTO;
import at.fhv.tvv.shared.rmi.MessageConsumer;
import org.hibernate.Hibernate;

import javax.jms.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MessageConsumerImpl implements MessageConsumer {

    private Connection connection;
    private Session session;

    private final EventRepository eventRepository;

    public MessageConsumerImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;

    }

    @Override
    public List<MessageDTO> getMessages(String s) throws RemoteException {
       List<MessageDTO> messages = new ArrayList<>();
        try {
            connection = HibernateService.activeMQConnectionFactory().createConnection();
            connection.setClientID("client");
            connection.start();
            session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            List<TextMessage> textMessageList = new ArrayList<>();
            Optional<Angestellte> angestellter = eventRepository.getAngestellerById(s);
            if(angestellter.isEmpty()) {
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
                messages.add(new MessageDTO(messageToDTO.getStringProperty("topic"), messageToDTO.getStringProperty("title"), messageToDTO.getText()));
            }
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messages;
    }
}
