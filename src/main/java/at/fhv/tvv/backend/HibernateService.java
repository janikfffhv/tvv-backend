package at.fhv.tvv.backend;
import at.fhv.tvv.backend.application.EventSearchImpl;
import at.fhv.tvv.backend.communication.EventSearchRMI;
import at.fhv.tvv.backend.domain.repository.EventRepository;
import at.fhv.tvv.backend.infrastructure.EventRepositoryImpl;
import at.fhv.tvv.shared.rmi.EventSearch;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.rmi.RemoteException;

public class HibernateService {

    private static EntityManager entityManager;
    private static EventRepository eventRepository;

    private static EventSearch eventSearchImpl;
    private static EventSearch eventSearchRMI;
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
}
