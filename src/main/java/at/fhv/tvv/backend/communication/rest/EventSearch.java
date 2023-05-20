package at.fhv.tvv.backend.communication.rest;

import at.fhv.tvv.backend.domain.model.event.Event;
import at.fhv.tvv.backend.domain.repository.EventRepository;
import at.fhv.tvv.backend.interfaces.EventSearchInt;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/event")
public class EventSearch {

    @EJB
    private EventSearchInt event;

    @EJB
    private EventRepository eventRepository;

    private List<Event> eventList;

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@QueryParam("eventname") @DefaultValue("") String eventName, @QueryParam("kategorie") @DefaultValue("") String kategorie, @QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
        if (eventName != null || kategorie != null || startDate != null || endDate != null) {
            if (eventName != null) {
                eventList = eventRepository.searchByString(eventName);
                return Response.status(Response.Status.OK).build();
            }
            if (kategorie != null) {
                eventList = eventRepository.searchByCategory(kategorie);
                return Response.ok().build();
            }
            if (startDate != null && endDate != null) {
                eventList = eventRepository.searchByDate(Integer.parseInt(startDate), Integer.parseInt(endDate));
                return Response.ok().build();
            }
        }

        return Response.ok().build();
    }

}
