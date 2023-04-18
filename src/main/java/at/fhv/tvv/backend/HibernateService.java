package at.fhv.tvv.backend;
import at.fhv.tvv.backend.application.CustomerSearchTicketsImpl;
import at.fhv.tvv.backend.application.EventSearchImpl;
import at.fhv.tvv.backend.application.TvvSessionFactoryImpl;
import at.fhv.tvv.backend.application.VerkaufImpl;
import at.fhv.tvv.backend.communication.*;
import at.fhv.tvv.backend.domain.repository.EventRepository;
import at.fhv.tvv.backend.infrastructure.EventRepositoryImpl;
import at.fhv.tvv.shared.rmi.*;

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

    public static EntityManager entityManager() {
        if (entityManager == null) {
            entityManager = Persistence.createEntityManagerFactory("Factory").createEntityManager();

        }
        return entityManager;
    }

    public static EventRepository eventRepository() {
        if(eventRepository == null) {
            eventRepository = new EventRepositoryImpl();
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
}
