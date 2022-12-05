package com.security.micro1.controller

import com.security.micro1.model.ProtectedResource
import com.security.micro1.service.IProtectedResourceServer
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
class ProtectedController(
    val protectedService: IProtectedResourceServer
) {

    @GetMapping("/microservice1")
    fun getProtectedResourceValue1(
        auth: BearerTokenAuthentication
    ): Mono<ProtectedResource?>? {
        println("ENDPOINT: /microservice1")
        val protectedResource = protectedService.getProtectedResource(auth)
        return Mono.just(protectedResource)
    }

    @GetMapping("/microservice2")
    fun getProtectedResourceValue2(
        auth: BearerTokenAuthentication
    ): Mono<ProtectedResource?>? {
        println("ENDPOINT: /microservice2")
        val protectedResource = protectedService.getProtectedResource(auth)
        return Mono.just(protectedResource)
    }

    @GetMapping("/microservice3")
    fun getProtectedResourceValue3(
        auth: BearerTokenAuthentication
    ): Mono<ProtectedResource?>? {
        println("ENDPOINT: /microservice3")
        val protectedResource = protectedService.getProtectedResource(auth)
        return Mono.just(protectedResource)
    }
}