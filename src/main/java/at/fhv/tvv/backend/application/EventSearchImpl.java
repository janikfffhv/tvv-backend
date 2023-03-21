package at.fhv.tvv.backend.application;

import at.fhv.tvv.backend.domain.repository.EventRepository;
import at.fhv.tvv.backend.infrastructure.EventRepositoryImpl;
import at.fhv.tvv.shared.dto.EventSearchDTO;
import at.fhv.tvv.shared.rmi.EventSearch;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EventSearchImpl implements EventSearch {
    private final at.fhv.tvv.backend.domain.repository.EventRepository eventRepository;

    public EventSearchImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    @Override
    public List<EventSearchDTO> searchByString(String searchString) {
        return eventRepository
                .searchByString(searchString)
                .stream()
                .map(event -> new EventSearchDTO(
                        event.getEventId(),
                        event.getName(),
                        event.getVeranstaltungsserie().getName(),
                        event.getDatum(),
                        event.getVeranstaltungsort().getOrt(),
                        event.getPlaetze()))
                .collect(Collectors.toList());
    }

    @Override
    public List<EventSearchDTO> searchByDate(String searchDate) {
        return eventRepository
                .searchByDate(searchDate)
                .stream()
                .map(event -> new EventSearchDTO(
                        event.getEventId(),
                        event.getName(),
                        event.getVeranstaltungsserie().getName(),
                        event.getDatum(),
                        event.getVeranstaltungsort().getOrt(),
                        event.getPlaetze()))
                .collect(Collectors.toList());
    }
}
