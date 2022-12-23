package com.security.micro2.controller

import com.security.micro2.model.ProtectedResource
import com.security.micro2.service.IProtectedResourceService
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
class ProtectedController(
    val protectedService: IProtectedResourceService
) {
    @GetMapping("/microservice2")
    fun getProtectedResourceValue2(
        auth: BearerTokenAuthentication
    ): Mono<ProtectedResource?>? {
        println("ENDPOINT: /microservice2")
        val protectedResource = protectedService.getProtectedResource(auth)
        return Mono.just(protectedResource)
    }
}