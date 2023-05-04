package at.fhv.tvv.backend.infrastructure;

import at.fhv.tvv.backend.HibernateService;
import at.fhv.tvv.backend.domain.model.angestellte.Angestellte;
import at.fhv.tvv.backend.domain.model.event.Event;
import at.fhv.tvv.backend.domain.model.platz.Platz;
import at.fhv.tvv.backend.domain.model.veranstaltungsserie.Kategorie;
import at.fhv.tvv.backend.domain.model.verkauf.Verkauf;
import at.fhv.tvv.backend.domain.repository.EventRepository;
import at.fhv.tvv.shared.dto.CustomerEventDTO;
import at.fhv.tvv.shared.dto.CustomerInfoDTO;

import javax.persistence.EntityManager;
import java.util.*;

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
                .createQuery("SELECT e FROM Event e WHERE e.datum BETWEEN (?1) AND (?2)", Event.class)
                .setParameter(1, searchDate1)
                .setParameter(2, searchDate2)
                .getResultList();
    }

    public List<Event> searchByCategory(String searchString) {
        Kategorie kategorie = Kategorie.valueOf(searchString);
        return entityManager
                .createQuery("SELECT e FROM Event e WHERE e.veranstaltungsserie.kategorie=(?1)", Event.class)
                .setParameter(1, kategorie)
                .getResultList();
    }

    public Event searchById(int eventId) {
        return entityManager
                .createQuery("SELECT e FROM Event e WHERE e.eventId=(?1)", Event.class)
                .setParameter(1, eventId)
                .getSingleResult();
    }

    public Platz searchByPlatzId(int platzId) {
        return entityManager
                .createQuery("SELECT p FROM Platz p WHERE p.platzId=(?1)", Platz.class)
                .setParameter(1, platzId)
                .getSingleResult();
    }

    public List<CustomerEventDTO> getCustomerTickets(UUID customerUuid) {
        List<Verkauf> verkaufListe = entityManager
                .createQuery("SELECT v from Verkauf v  WHERE v.kundenId=(?1)", Verkauf.class)
                .setParameter(1, customerUuid)
                .getResultList();
        List<CustomerEventDTO> customerListe = new ArrayList<>();
        for(Verkauf verkauf:verkaufListe) {
            CustomerEventDTO event = new CustomerEventDTO(verkauf.getVerkaufsId(), verkauf.getVerkaufszeit(), verkauf.getZahlungsmethode().toString(), verkauf.getGesamtpreis());
            customerListe.add(event);
        }
        return customerListe;
    }

    public Optional<Angestellte> getAngestellerById(String userid) {
        return entityManager
                .createQuery("Select a FROM Angestellte a WHERE a.benutzername = :userId", Angestellte.class)
                .setParameter("userId", userid)
                .getResultList()
                .stream()
                .findFirst();
    }

    public void updateAngestellter(Angestellte angestellter) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(angestellter);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void purchase(Verkauf verkauf) {
        System.out.println("Verkauf gestartet!");
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(verkauf);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Platz> plaetze = verkauf.getPlaetze();
        /**for(Platz platz:plaetze) {
            try {

                entityManager
                        .createQuery("UPDATE Platz p SET p.verkauf=(?1) WHERE p.platzId=(?2)")
                        .setParameter(1, verkauf)
                        .setParameter(2, platz.getPlatzId())
                        .executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }**/
    }


}
