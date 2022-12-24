package com.security.micro1.controller

import com.security.micro1.model.ProtectedResource
import com.security.micro1.service.IProtectedResourceService
import org.apache.log4j.Logger
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
class ProtectedController(
    val protectedService: IProtectedResourceService
) {
    val logger: Logger = Logger.getLogger(ProtectedController::class.java)

    @GetMapping("/microservice1")
    fun getProtectedResourceValue1(
        auth: BearerTokenAuthentication
    ): Mono<ProtectedResource?>? {
        logger.info("EXECUTED ENDPOINT: /microservice1")
        val protectedResource = protectedService.getProtectedResource(auth)
        return Mono.just(protectedResource)
    }
}
