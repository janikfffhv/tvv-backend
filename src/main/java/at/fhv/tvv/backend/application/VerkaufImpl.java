package at.fhv.tvv.backend.application;

import at.fhv.tvv.backend.domain.model.event.Event;
import at.fhv.tvv.backend.domain.model.platz.Platz;
import at.fhv.tvv.backend.domain.model.verkauf.Zahlungsmethode;
import at.fhv.tvv.backend.domain.repository.EventRepository;
import at.fhv.tvv.backend.interfaces.VerkaufInt;
import at.fhv.tvv.shared.dto.*;
import at.fhv.tvv.shared.rmi.EventSearch;
import at.fhv.tvv.shared.rmi.Verkauf;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Stateless
public class VerkaufImpl implements VerkaufInt {

    @EJB
    private EventRepository eventRepository;

    public VerkaufImpl() {
    }

    public VerkaufImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    @Override
    public boolean kaufe(VerkaufDTO verkaufDTO) {
        Zahlungsmethode methode = Zahlungsmethode.valueOf(verkaufDTO.getZahlungsmethode());
        at.fhv.tvv.backend.domain.model.verkauf.Verkauf verkauf = new at.fhv.tvv.backend.domain.model.verkauf.Verkauf(
                UUID.randomUUID(),
                verkaufDTO.getGesamtpreis(),
                0,
                verkaufDTO.getKundenId(),
                verkaufDTO.getVerkaufszeit(),
                methode,
                "Max Mustermann"
        );
        for(WarenkorbZeileDTO platz:verkaufDTO.getPlaetze()) {
            Platz platz1 = eventRepository.searchByPlatzId(platz.getPlatzId());
            try{
                if(platz1.getVerkauf().getVerkaufsId() != null) {
                    return false;
                }} catch (Exception e) {
                verkauf.addPlatz(platz1);
                e.printStackTrace();
            }
        }
        eventRepository.purchase(verkauf);
        return true;
    }
}
