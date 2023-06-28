package at.fhv.tvv.backend.communication.rest;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
@OpenAPIDefinition(info = @Info(
        title = "TVV OpenAPI",
        version = "1.0.0"),
        servers = {
                @Server(url = "/backend-1.0-SNAPSHOT", description = "localhost")
        }
)
public class Rest extends Application {
}
