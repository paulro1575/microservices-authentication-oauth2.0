### Relevant Articles:

# OAuth Authorization Server
This project is created in order to create a secure microservice authentication infrastructure. To achieve this, we used the OAuth2.0.

### Author 
Based on [Keycloak Embedded in a Spring Boot Application](https://www.baeldung.com/keycloak-embedded-in-spring-boot-app).
- Code adapted by Paul Rodriguez.

### Context
This code is part of a final project for obtaining the Master's degree in Cybersecurity and Privacy according to the guidelines provided by the [Universitat Oberta de Catalunya](https://www.uoc.edu/). 

## Description
This is a OAuth2.0 Authorization server that implements all features according to the [RFC-6749](https://www.rfc-editor.org/rfc/rfc6749) specifications.

## Dependencies
This package uses this dependencies:
- [Keycloak](https://www.keycloak.org/).
- Java 13
- Spring Boot 2.6.7
- Apache Maven 3.6.3
- h2database

This one was based on this [original code](https://github.com/thomasdarimont/embedded-spring-boot-keycloak-server).

## Build project: 
To build this project we can use:

<code>mvn clean install</code>

## Run project
To run this project we can use:

<code>mvn spring-boot:run</code>
