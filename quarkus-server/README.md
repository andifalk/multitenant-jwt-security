# Multi-tenancy JWT Quarkus Demo

This is the quarkus application for multi-tenant JWT, i.e. accepting JWT's having different issuers (different authz servers) in same resource server.

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

## Running the application

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```
The application can be packaged using:
```shell script
./gradlew build
```
It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

