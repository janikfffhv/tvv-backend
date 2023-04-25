package at.fhv.tvv.backend.domain.repository;

import at.fhv.tvv.backend.domain.model.angestellte.Angestellte;
import at.fhv.tvv.backend.domain.model.event.Event;
import at.fhv.tvv.backend.domain.model.platz.Platz;
import at.fhv.tvv.backend.domain.model.verkauf.Verkauf;
import at.fhv.tvv.shared.dto.CustomerEventDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventRepository {
    List<Event> searchByString (String searchString);
    List<Event> searchByDate (int searchDate1, int SearchDate2);
    List<Event> searchByCategory (String searchString);

    Platz searchByPlatzId (int PlatzId);

    List<CustomerEventDTO> getCustomerTickets (UUID kundenUUID);

    Event searchById (int eventId);

    Optional<Angestellte> getAngestellerById(String userid);

    void purchase (Verkauf verkauf);
}
