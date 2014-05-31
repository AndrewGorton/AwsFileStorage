package uk.co.devsoup.simpledropwizardecho.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;
import uk.co.devsoup.simpledropwizardecho.interfaces.Response;

import java.util.UUID;

@Path("/echo")
@Produces(MediaType.APPLICATION_JSON)
public class SimpleDropWizardEchoResource {
    public SimpleDropWizardEchoResource() {

    }

    @GET
    @Timed
    public Response getEcho(@QueryParam("echo") Optional<String> echo) {
        return new Response(UUID.randomUUID().toString(), echo.orNull());
    }
}
