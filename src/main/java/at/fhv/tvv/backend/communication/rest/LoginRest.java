package at.fhv.tvv.backend.communication.rest;

import at.fhv.tvv.backend.domain.repository.EventRepository;
import at.fhv.tvv.backend.infrastructure.LdapService;
import at.fhv.tvv.backend.interfaces.CustomerTicketsInt;
import at.fhv.tvv.backend.interfaces.LdapServiceInt;
import at.fhv.tvv.shared.dto.CustomerInfoDTO;

import javax.ejb.EJB;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;
import java.util.UUID;

@Path("/login")
public class LoginRest {

    @EJB
    LdapServiceInt login;

    @EJB
    CustomerTicketsInt customerSearch;

    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@QueryParam("username") String userName, @QueryParam("password") String password) {
        if (userName == null) {
            System.out.println("username is missing");
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        if (password == null) {
            System.out.println("password is missing");
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        String loginLdap = login.customerInLdap(userName, password);
        if(Objects.equals(loginLdap, "FALSE")) {
            System.out.println("Unauthorized!");
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } else {
            CustomerInfoDTO cinfo = customerSearch.searchById(UUID.fromString(loginLdap));
            return Response.status(Response.Status.OK).entity(cinfo).entity(loginLdap).build();
        }

    }


}
