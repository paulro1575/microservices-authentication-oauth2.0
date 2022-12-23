# Microservices Authentication based on OAuth2.0
This project was designed in order to create a secure microservice authentication infrastructure. To achieve this, we used the OAuth2.0.

### Author 
Paul Rodríguez

### Context
This code is part of a final project for obtaining the Master's degree in Cybersecurity and Privacy according to the guidelines provided by the [Universitat Oberta de Catalunya](https://www.uoc.edu/). 


## Description
These project inlude the next parts:
<ol>
  <li>OAuth - Authorization Server</li>
  <li>API Gateway Microservice</li>
  <li>Microservice 1</li>
  <li>Microservice 2</li>
  <li>Microservice 3</li>
</ol>

The microservices infrastructure will follow the next scheme:
![Microservice Infrastructure](/assets/diagram.png "Microservice Infrastructure")

- The API Gateway will expose 3 endpoints: /microservice1, /microservice2 and /microservice3.
- Each API Gateway endpoint will redirect the request to each microservice containing an endpoint with the same name.
- The API Gateway will transmit the same access token that it was granted by the authorization server to the microservices. This way they can use it and return the protected resource.
- In case the access token is valid, each microservice will send the protected resource to the Gateway to be sent back to the user's agent.
- All endpoints, both the API Gateway and each microservice, are protected by the OAuth 2.0 protocol.
- The Authorization Server will be present during the entire data flow of the microservices.
