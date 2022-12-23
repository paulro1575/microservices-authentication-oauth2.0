# API Gateway microservice
This project is created in order to create a secure microservice authentication infrastructure. To achieve this, we used the OAuth2.0.

### Author 
Paul Rodríguez

### Context
This code is part of a final project for obtaining the Master's degree in Cybersecurity and Privacy according to the guidelines provided by the [Universitat Oberta de Catalunya](https://www.uoc.edu/). 

## Description
This microservice API gateway acts as <b>OAuth Client</b> and will also expose 3 endpoints:

- /microservice1
- /microservice1
- /microservice1 

Each of them will redirect to each microservice with the same name and with the same endpoint within each.

### Dependencies

This package uses this dependencies:
- SpringBoot cloud started gateway.
- Kotlin 1.6.21
- Spring Boot 2.7.7
- Gradle 7.5.1

## Build project 
To build this project we can use:

<code>./gradlew build</code>

## Run project 
To run this project we can use:

<code>./gradlew bootRun</code>
