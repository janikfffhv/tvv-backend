package at.fhv.tvv.backend;
import at.fhv.tvv.backend.application.*;
import at.fhv.tvv.backend.communication.*;
import at.fhv.tvv.backend.domain.repository.EventRepository;
import at.fhv.tvv.backend.infrastructure.EventRepositoryImpl;
import at.fhv.tvv.shared.rmi.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.rmi.RemoteException;

public class HibernateService {

    private static EntityManager entityManager;
    private static EventRepository eventRepository;

    private static EventSearch eventSearchImpl;
    private static EventSearch eventSearchRMI;

    private static Verkauf verkaufImpl;
    private static Verkauf verkaufImplRMI;

    private static CustomerSearch customerSearchRMI;

    private static CustomerTickets customerTickets;
    private static CustomerTickets customerTicketsRMI;

    private static TvvSessionFactory tvvSessionFactoryImpl;
    private static TvvSessionFactory tvvSessionFactoryRMI;

    private static CustomerSearchTicketsImpl customerSearchTickets;

    private static ActiveMQConnectionFactory activeMQConnectionFactory;

    private static MessageConsumer messageConsumerImpl;
    private static MessageConsumer messageConsumerRMI;

    private static MessageProducer messageProducerImpl;
    private static MessageProducer messageProducerRMI;

    private static RolesTopics rolesTopicsImpl;
    private static RolesTopics rolesTopicsRMI;

    public static EntityManager entityManager() {
        if (entityManager == null) {
            entityManager = Persistence.createEntityManagerFactory("Factory").createEntityManager();

        }
        return entityManager;
    }

    public static EventRepository eventRepository() {
        if(eventRepository == null) {
            eventRepository = new EventRepositoryImpl(entityManager());
        }
        return eventRepository;
    }


    public static EventSearch eventSearchImpl() {
        if(eventSearchImpl == null) {
            eventSearchImpl = new EventSearchImpl(eventRepository());
        }
        return eventSearchImpl;
    }

    public static EventSearch eventSearchRMI() throws RemoteException {
        if(eventSearchRMI == null) {
            eventSearchRMI = new EventSearchRMI(eventSearchImpl());
        }
        return eventSearchRMI;
    }

    public static Verkauf verkaufImpl() {
        if(verkaufImpl == null) {
            verkaufImpl = new VerkaufImpl(eventRepository());
        }
        return verkaufImpl;
    }

    public static CustomerSearchTicketsImpl customerSearchTickets() {
        if(customerSearchTickets == null) {
            customerSearchTickets = new CustomerSearchTicketsImpl(eventRepository());
        }
        return customerSearchTickets;
    }

    public static CustomerTickets customerTicketsRMI() throws RemoteException {
        if(customerTicketsRMI == null) {
            customerTicketsRMI = new CustomerTicketsRMI(customerSearchTickets());
        }
        return customerTicketsRMI;
    }

    public static Verkauf verkaufImplRMI() throws RemoteException {
        if(verkaufImplRMI == null) {
            verkaufImplRMI = new VerkaufImplRMI(verkaufImpl());
        }
        return verkaufImplRMI;
    }

    public static CustomerSearch customerSearchRMI() throws RemoteException {
        if(customerSearchRMI == null) {
            customerSearchRMI = new CustomerSearchRMI();
        }
        return customerSearchRMI;
    }

    public static TvvSessionFactory tvvSessionFactoryImpl() {
        if(tvvSessionFactoryImpl == null) {
            tvvSessionFactoryImpl = new TvvSessionFactoryImpl();
        }
        return tvvSessionFactoryImpl;
    }

    public static TvvSessionFactory tvvSessionFactoryRMI() throws RemoteException {
        if(tvvSessionFactoryRMI == null) {
            tvvSessionFactoryRMI = new TvvSessionFactoryRMI(tvvSessionFactoryImpl());
        }
        return tvvSessionFactoryRMI;
    }

    public static ActiveMQConnectionFactory activeMQConnectionFactory() {
        if(activeMQConnectionFactory == null) {
            activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616?jms.prefetchPolicy.all=0");
        }
        return activeMQConnectionFactory;
    }

    public static MessageConsumer messageConsumerImpl() {
        if(messageConsumerImpl == null) {
            messageConsumerImpl = new MessageConsumerImpl(eventRepository());
        }
        return messageConsumerImpl;
    }

    public static MessageConsumer messageConsumerRMI() throws RemoteException {
        if(messageConsumerRMI == null) {
            messageConsumerRMI = new MessageConsumerRMI(messageConsumerImpl());
        }
        return messageConsumerRMI;
    }

    public static MessageProducer messageProducerImpl() {
        if(messageProducerImpl == null) {
            messageProducerImpl = new MessageProducerImpl();
        }
        return messageProducerImpl;
    }

    public static MessageProducer messageProducerRMI() throws RemoteException {
        if(messageProducerRMI == null) {
            messageProducerRMI = new MessageProducerRMI(messageProducerImpl());
        }
        return messageProducerRMI;
    }

    public static RolesTopics rolesTopicsImpl() {
        if(rolesTopicsImpl == null) {
            rolesTopicsImpl = new RolesTopicsImpl(eventRepository());
        }
        return rolesTopicsImpl;
    }

    public static RolesTopics rolesTopicsRMI() throws RemoteException {
        if(rolesTopicsRMI == null) {
            rolesTopicsRMI = new RolesTopicsRMI(rolesTopicsImpl());
        }
        return rolesTopicsRMI;
    }




}
