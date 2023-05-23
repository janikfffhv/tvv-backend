package at.fhv.tvv.backend.communication.rest;

import at.fhv.tvv.backend.domain.model.event.Event;
import at.fhv.tvv.backend.domain.repository.EventRepository;
import at.fhv.tvv.backend.interfaces.EventSearchInt;
import at.fhv.tvv.shared.dto.EventDescriptionDTO;
import at.fhv.tvv.shared.dto.EventSearchDTO;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/event")
public class EventSearch {

    @EJB
    private EventSearchInt event;

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@QueryParam("eventname") @DefaultValue("") String eventName, @QueryParam("kategorie") @DefaultValue("") String kategorie, @QueryParam("startDate") @DefaultValue("") String startDate, @QueryParam("endDate") @DefaultValue("") String endDate, @QueryParam("id") @DefaultValue("0") int id) {
        if (eventName != null || kategorie != null || startDate != null || endDate != null) {
            List<EventSearchDTO> eventList;
            if (eventName != "") {
                eventList = event.searchByString(eventName);
                return Response.status(Response.Status.OK).entity(eventList).build();
            }
            if (kategorie != "") {
                eventList = event.searchByCategory(kategorie);
                return Response.status(Response.Status.OK).entity(eventList).build();
            }
            if (startDate != "" && endDate != "") {
                eventList = event.searchByDate(Integer.parseInt(startDate), Integer.parseInt(endDate));
                return Response.status(Response.Status.OK).entity(eventList).build();
            }
            if (id != 0) {
                EventDescriptionDTO eventDesc = event.searchById(id);
                return Response.status(Response.Status.OK).entity(eventDesc).build();
            }
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

}
