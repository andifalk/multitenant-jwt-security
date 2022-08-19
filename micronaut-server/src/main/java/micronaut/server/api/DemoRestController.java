package micronaut.server.api;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.ServerAuthentication;
import io.micronaut.security.rules.SecurityRule;

import java.security.Principal;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller
public class DemoRestController {

    @Produces(MediaType.TEXT_PLAIN)
    @Get("/api/hello")
    public String index(Principal principal) {
        if (principal instanceof ServerAuthentication) {
            ServerAuthentication auth = (ServerAuthentication) principal;
            return String.format("[%s] (%s)", auth.getName(), auth.getAttributes().get("iss"));
        } else {
            return principal.getName();
        }
    }
}
