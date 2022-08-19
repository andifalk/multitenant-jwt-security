# Multi-tenancy JWT Micronaut Demo

This micronaut application for multi-tenant JWT, i.e. accepting JWT's having different issuers (different authz servers) in same resource server.

## Scenario

The implemented backend server application uses bearer token (JWT format) authentication, issued by 2 different
authorization servers (Auth0 and Okta).

The server application defines one API endpoint that is secured by bearer token authentication (JWT  format):

[http://localhost:8080/api/hello](http://localhost:8080/api/hello)

The actuator endpoint of the server application is just secured with basic authentication, not using JWT.
To access this use the following user credentials:

```
admin / secret
```

## Authorization Servers

For [Auth0](https://access-me.eu.auth0.com/.well-known/openid-configuration) please use the following user credentials to log in:

```
user@example.com / user_4demo!
```

For [Okta](https://dev-667216.oktapreview.com/oauth2/ausjbx4pq5Wg0kZx10h7/.well-known/oauth-authorization-server) please use the following user credentials to log in:

```
user@example.com / Library_access#1
```




