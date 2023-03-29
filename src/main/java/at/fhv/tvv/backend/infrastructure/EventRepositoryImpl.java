package at.fhv.tvv.backend.infrastructure;

import at.fhv.tvv.backend.HibernateService;
import at.fhv.tvv.backend.domain.model.event.Event;
import at.fhv.tvv.backend.domain.repository.EventRepository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class EventRepositoryImpl implements EventRepository {

    private final EntityManager entityManager = HibernateService.entityManager();

    public List<Event> searchByString(String searchString) {
        return entityManager
                .createQuery("SELECT e FROM Event e WHERE LOWER(e.name) LIKE LOWER(?1) OR LOWER(e.veranstaltungsort.gebaeude) LIKE LOWER(?1)", Event.class)
                .setParameter(1, "%" + searchString + "%")
                .getResultList();
    }

    public List<Event> searchByDate(int searchDate1, int searchDate2) {
        return entityManager
                .createQuery("SELECT e FROM Event e WHERE LOWER(e.datum) BETWEEN (?1) AND (?2)", Event.class)
                .setParameter(1, searchDate1)
                .setParameter(2, searchDate2)
                .getResultList();
    }
}
