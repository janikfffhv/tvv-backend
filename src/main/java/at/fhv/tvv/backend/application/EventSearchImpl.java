package at.fhv.tvv.backend.application;

import at.fhv.tvv.backend.domain.model.event.Event;
import at.fhv.tvv.backend.domain.model.platz.Platz;
import at.fhv.tvv.backend.domain.repository.EventRepository;
import at.fhv.tvv.backend.infrastructure.EventRepositoryImpl;
import at.fhv.tvv.backend.interfaces.EventSearchInt;
import at.fhv.tvv.shared.dto.EventDescriptionDTO;
import at.fhv.tvv.shared.dto.EventSearchDTO;
import at.fhv.tvv.shared.dto.PlatzDTO;
import at.fhv.tvv.shared.rmi.EventSearch;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.rmi.RemoteException;
import java.util.*;
import java.util.stream.Collectors;

@Stateless
public class EventSearchImpl implements EventSearchInt {
    @EJB
    private at.fhv.tvv.backend.domain.repository.EventRepository eventRepository;

    public EventSearchImpl() {
    }

    public EventSearchImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public int countVerfuegbar(List<Platz> plaetze) {
        int count = 0;
        for(Platz platz:plaetze) {
            if(platz.getVerkauf()==null) {
                count++;
            }
        }
        return count;
    }

    public List<PlatzDTO> getPlaetze(List<Platz> plaetze) {
        List<PlatzDTO> plaetzeReturn = new ArrayList<>();
        for(Platz platz:plaetze) {
            String verkaufsUUID = "";
            if(platz.getVerkauf()!=null) {
                verkaufsUUID = platz.getVerkauf().getVerkaufsId().toString();
            }
            PlatzDTO platzDTO = new PlatzDTO(platz.getPlatzId(), platz.getNummer(), platz.getKategorie().toString(), platz.getReihe(), verkaufsUUID, platz.getPreis());
            plaetzeReturn.add(platzDTO);
        }
        return plaetzeReturn;
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
                        event.getPlaetze().size(),
                        countVerfuegbar(event.getPlaetze())))
                .collect(Collectors.toList());
    }

    @Override
    public List<EventSearchDTO> searchByDate(int searchDate1, int searchDate2) {
        return eventRepository
                .searchByDate(searchDate1, searchDate2)
                .stream()
                .map(event ->
                    new EventSearchDTO(
                            event.getEventId(),
                            event.getName(),
                            event.getVeranstaltungsserie().getName(),
                            event.getDatum(),
                            event.getVeranstaltungsort().getOrt(),
                            event.getPlaetze().size(),
                            countVerfuegbar(event.getPlaetze()))
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<EventSearchDTO> searchByCategory(String searchString) {
        return eventRepository
                .searchByCategory(searchString)
                .stream()
                .map(event -> new EventSearchDTO(
                        event.getEventId(),
                        event.getName(),
                        event.getVeranstaltungsserie().getName(),
                        event.getDatum(),
                        event.getVeranstaltungsort().getOrt(),
                        event.getPlaetze().size(),
                        countVerfuegbar(event.getPlaetze())))
                .collect(Collectors.toList());
    }

    @Override

    public EventDescriptionDTO searchById(int eventId) {
        Event event = eventRepository.searchById(eventId);
        return new EventDescriptionDTO(event.getEventId(),
                event.getName(),
                event.getVeranstaltungsserie().getName(),
                event.getVeranstaltungsserie().getVeranstalter(),
                event.getVeranstaltungsserie().getKategorie().getName(),
                event.getDatum(),
                event.getVeranstaltungsort().getGebaeude(),
                event.getVeranstaltungsort().getStrasse(),
                event.getVeranstaltungsort().getHausnummer(),
                event.getVeranstaltungsort().getPlz(),
                event.getVeranstaltungsort().getOrt(),
                event.getVeranstaltungsort().getRaum(),
                event.getBeschreibung(),
                getPlaetze(event.getPlaetze()));

    }
}
