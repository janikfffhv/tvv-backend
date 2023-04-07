package at.fhv.tvv.backend;
import at.fhv.tvv.backend.application.EventSearchImpl;
import at.fhv.tvv.backend.application.TvvSessionFactoryImpl;
import at.fhv.tvv.backend.communication.CustomerSearchRMI;
import at.fhv.tvv.backend.communication.EventSearchRMI;
import at.fhv.tvv.backend.communication.TvvSessionFactoryRMI;
import at.fhv.tvv.backend.domain.repository.EventRepository;
import at.fhv.tvv.backend.infrastructure.EventRepositoryImpl;
import at.fhv.tvv.shared.rmi.CustomerSearch;
import at.fhv.tvv.shared.rmi.EventSearch;
import at.fhv.tvv.shared.rmi.TvvSessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.rmi.RemoteException;

public class HibernateService {

    private static EntityManager entityManager;
    private static EventRepository eventRepository;

    private static EventSearch eventSearchImpl;
    private static EventSearch eventSearchRMI;

    private static CustomerSearch customerSearchRMI;

    private static TvvSessionFactory tvvSessionFactoryImpl;
    private static TvvSessionFactory tvvSessionFactoryRMI;
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
