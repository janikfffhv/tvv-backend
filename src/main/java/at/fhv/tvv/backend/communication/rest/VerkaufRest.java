package at.fhv.tvv.backend.communication.rest;

import at.fhv.tvv.backend.domain.model.platz.Platz;
import at.fhv.tvv.backend.domain.model.verkauf.Verkauf;
import at.fhv.tvv.backend.domain.model.verkauf.Zahlungsmethode;
import at.fhv.tvv.backend.domain.repository.EventRepository;
import at.fhv.tvv.backend.interfaces.VerkaufInt;
import at.fhv.tvv.shared.dto.VerkaufDTO;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/verkauf")
public class VerkaufRest {

    @EJB
    private VerkaufInt verkauf;

    @EJB
    private EventRepository eventRepository;

    @GET
    @Path("/kaufe")
    @Produces(MediaType.APPLICATION_JSON)
    public Response kauf(@QueryParam("username") @DefaultValue("") String userId, @QueryParam("platzId") @DefaultValue("") String platzId, @QueryParam("preis") @DefaultValue("") String preis, @QueryParam("zahlungsmethode") @DefaultValue("") String zahlungsmethode) {
        Verkauf verkauf = new Verkauf(UUID.randomUUID(), Float.valueOf(preis), 0, UUID.fromString(userId), String.valueOf(System.currentTimeMillis()/1000), Zahlungsmethode.valueOf(zahlungsmethode), "Max Mustermann");
        Platz platz = eventRepository.searchByPlatzId(Integer.parseInt(platzId));
        verkauf.addPlatz(platz);
        eventRepository.purchase(verkauf);
        return Response.status(Response.Status.OK).build();
    }
}
