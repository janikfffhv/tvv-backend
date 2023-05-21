package at.fhv.tvv.backend.communication.rest;

import at.fhv.tvv.backend.domain.repository.EventRepository;

import javax.ejb.EJB;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginRest {

    @EJB
    LoginControllerImpl login;

    @EJB
    EventRepository eventRepository;

    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@QueryParam("username") String userName, @QueryParam("password") String password, @QueryParam("ipaddress") @DefaultValue("") String ipAddress,) {
        if (userName != null) {
            login.setusername(userName);
        } else {
            System.out.println("username is missing");
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        if (password != null) {
            login.setpwd(password);
        } else {
            System.out.println("password is missing");
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        if (ipAddress != null) {
            login.setIpAddress(ipAddress);
        } else {
            // Wenn nichts eingetragen auf standart port verbinden
            login.setDefaultIpAddress("");
        }
        return Response.ok().build();
    }


}
