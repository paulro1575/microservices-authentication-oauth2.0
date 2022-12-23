package com.security.micro3.controller

import com.security.micro3.model.ProtectedResource
import com.security.micro3.service.IProtectedResourceService
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
class ProtectedController(
    val protectedService: IProtectedResourceService
) {
    @GetMapping("/microservice3")
    fun getProtectedResourceValue3(
        auth: BearerTokenAuthentication
    ): Mono<ProtectedResource?>? {
        println("ENDPOINT: /microservice3")
        val protectedResource = protectedService.getProtectedResource(auth)
        return Mono.just(protectedResource)
    }
}
