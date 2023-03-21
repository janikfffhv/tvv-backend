package at.fhv.tvv.backend.domain.repository;

import at.fhv.tvv.backend.domain.model.event.Event;

import java.util.List;

public interface EventRepository {
    List<Event> searchByString (String searchString);
    List<Event> searchByDate (String searchDate);
}
