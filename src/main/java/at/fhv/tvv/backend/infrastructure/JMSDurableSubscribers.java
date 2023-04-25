package at.fhv.tvv.backend.infrastructure;

import at.fhv.tvv.backend.HibernateService;
import at.fhv.tvv.backend.domain.model.angestellte.Angestellte;
import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie;

import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;
import java.util.List;

public class JMSDurableSubscribers {

    private Connection connection;

    private Session session;

    public JMSDurableSubscribers() {

    }

    public void createDurableSubscribers(List<Angestellte> angestellte) {
        try {
            connection = HibernateService.activeMQConnectionFactory().createConnection();
            connection.setClientID("client");
            connection.start();
            session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            for (Angestellte angesteller : angestellte) {
                for(Kategorie kategorie : angesteller.getTopics()) {
                Topic topic = session.createTopic(kategorie.getName());
                TopicSubscriber subscriber = session.createDurableSubscriber(topic, angesteller.getBenutzername()+topic.getTopicName());
                subscriber.close();
                }
            }
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
