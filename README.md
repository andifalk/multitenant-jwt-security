# Multi-tenancy JWT Demo

This repository includes application demos for multi-tenant JWT, i.e. accepting JWT's having different issuers (different authz servers) in same resource server.

## Included applications

The repository includes the following applications:

* _micronaut-server_: Multi-tenant JWT server app with Micronaut
* _quarkus-server_: Multi-tenant JWT server app with Quarkus
* _spring-server_: Multi-tenant JWT server app with Spring Boot (servlet stack)
* _spring-reactive-server_: Multi-tenant JWT server app with Spring Boot (reactive stack)

## Scenario

All included server applications use bearer token (JWT format) authentication, issued by two different
authorization servers ([Auth0](https://auth0.com) and [Okta](https://okta.com)).

The server applications define one API endpoint that is secured by bearer token authentication (JWT  format):

[http://localhost:8080/api/hello](http://localhost:8080/api/hello)

You can also use the provided corresponding [postman collection](postman/Multitenancy.postman_collection.json)

## Authorization Servers

For [Auth0](https://access-me.eu.auth0.com/.well-known/openid-configuration) please use the following user credentials to log in:

```
user@example.com / user_4demo!
```

For [Okta](https://dev-667216.oktapreview.com/oauth2/ausjbx4pq5Wg0kZx10h7/.well-known/oauth-authorization-server) please use the following user credentials to log in:

```
user@example.com / Library_access#1
```
