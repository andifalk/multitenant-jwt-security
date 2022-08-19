package com.example;

import io.quarkus.oidc.OidcRequestContext;
import io.quarkus.oidc.OidcTenantConfig;
import io.quarkus.oidc.TenantConfigResolver;
import io.smallrye.mutiny.Uni;
import io.vertx.ext.web.RoutingContext;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.jose4j.json.internal.json_simple.parser.JSONParser;
import org.jose4j.json.internal.json_simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

@ApplicationScoped
public class MultiTenantJwtIssuerConfigResolver implements TenantConfigResolver {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private static final Logger LOGGER = LoggerFactory.getLogger(MultiTenantJwtIssuerConfigResolver.class);

    @Override
    public Uni<OidcTenantConfig> resolve(RoutingContext routingContext, OidcRequestContext<OidcTenantConfig> requestContext) {
        String issuer = resolveIssuer(routingContext);
        LOGGER.debug("Resolve OIDC tenant configuration for issuer {}", issuer);
        return Uni.createFrom().item(createOidcTenant(issuer));
    }

    // Resolve the issuer from provided bearer token (JWT format expected)
    // This just gets the issuer without any token validation, validation is done later by Quarkus
    private String resolveIssuer(RoutingContext routingContext) {
        String authorization = routingContext.request().getHeader(AUTHORIZATION_HEADER);
        if (authorization != null && authorization.toLowerCase().contains("bearer")) {
            String[] jwtParts = authorization.split(" ")[1].split("\\.");
            String payload = new String(Base64.getUrlDecoder().decode(jwtParts[1]), UTF_8);
            JSONParser parser = new JSONParser();
            try {
                JSONObject json = (JSONObject) parser.parse(payload);
                return (String) json.get("iss");

            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid JWT");
            }
        } else {
            LOGGER.debug("No authorization header found or not a bearer token");
            return null;
        }
    }

    private OidcTenantConfig createOidcTenant(String issuer) {
        if (issuer == null) {
            return null;
        }
        OidcTenantConfig oidcTenantConfig = new OidcTenantConfig();
        oidcTenantConfig.setDiscoveryEnabled(false);
        oidcTenantConfig.setApplicationType(OidcTenantConfig.ApplicationType.SERVICE);
        LOGGER.debug("Configure OIDC tenant for issuer {}", issuer);
        if (issuer.equals("https://access-me.eu.auth0.com/")) {
            oidcTenantConfig.setTenantId("auth0");
            oidcTenantConfig.setJwksPath("https://access-me.eu.auth0.com/.well-known/jwks.json");
            oidcTenantConfig.setAuthServerUrl("https://access-me.eu.auth0.com/");
        } else if (issuer.equals("https://dev-667216.oktapreview.com/oauth2/ausjbx4pq5Wg0kZx10h7")) {
            oidcTenantConfig.setTenantId("okta");
            oidcTenantConfig.setJwksPath("https://dev-667216.oktapreview.com/oauth2/ausjbx4pq5Wg0kZx10h7/v1/keys");
            oidcTenantConfig.setAuthServerUrl("https://dev-667216.oktapreview.com/oauth2/ausjbx4pq5Wg0kZx10h7");
        } else {
            throw new IllegalArgumentException("Unsupported issuer " + issuer);
        }
        LOGGER.info("OIDC config={}", oidcTenantConfig.getTenantId());
        return oidcTenantConfig;
    }
}
