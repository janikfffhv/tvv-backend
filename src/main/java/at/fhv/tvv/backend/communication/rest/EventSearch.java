package at.fhv.tvv.backend.communication.rest;

import at.fhv.tvv.backend.interfaces.EventSearchInt;
import at.fhv.tvv.shared.dto.EventDescriptionDTO;
import at.fhv.tvv.shared.dto.EventSearchDTO;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/teamd/event")
public class EventSearch {

    @EJB
    private EventSearchInt event;

    @GET
    @Path("/searchByString")
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@QueryParam("eventname") @DefaultValue("") String eventName) {
        if (eventName != null) {
            List<EventSearchDTO> eventList;
            eventList = event.searchByString(eventName);
            System.out.println(eventList);
            return Response.status(Response.Status.OK).entity(eventList).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("/searchByCategory")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByCategory(@QueryParam("kategorie") String kategorie) {
        List<EventSearchDTO> eventList;
        eventList = event.searchByCategory(kategorie);
        return Response.status(Response.Status.OK).entity(eventList).build();
    }

    @GET
    @Path("/searchByDate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByDate(@QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
        List<EventSearchDTO> eventList;
        eventList = event.searchByDate(Integer.parseInt(startDate), Integer.parseInt(endDate));
        return Response.status(Response.Status.OK).entity(eventList).build();
    }


    @GET
    @Path("/searchById")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchById(@QueryParam("id") @DefaultValue("0") int id) {
        EventDescriptionDTO eventDesc = event.searchById(id);
        return Response.status(Response.Status.OK).entity(eventDesc).build();
    }

}
