package com.security.micro2.service

import com.security.micro2.model.ProtectedResource
import com.security.micro2.utils.ADMIN_ROLE
import com.security.micro2.utils.CUSTOMER_ROLE
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication
import org.springframework.stereotype.Service

@Service
class ProtectedResourceService: IProtectedResourceService {

    @Value("\${microservice.name}")
    private val microserviceName: String? = null

    private val logger: Logger = Logger.getLogger(ProtectedResourceService::class.java)

    override fun getProtectedResource(auth: BearerTokenAuthentication): ProtectedResource {
        val name = microserviceName ?: "Default Microservice"
        val principal: DefaultOAuth2AuthenticatedPrincipal = auth.principal as DefaultOAuth2AuthenticatedPrincipal
        logger.info("MICROSERVICE NAME: $name, TOKEN-TYPE: ${auth.token.tokenType}")
        logger.info("TOKEN-VALUE: ${auth.token.tokenValue}")
        logger.info("USER ID: ${principal.name}")
        
        val isAdmin = auth.authorities.any{it == ADMIN_ROLE}
        val isCustomer = auth.authorities.any{it == CUSTOMER_ROLE}
        return if (isAdmin) {
            ProtectedResource(name, "Role: $ADMIN_ROLE")
        } else if (isCustomer) {
            ProtectedResource(name, "Role: $CUSTOMER_ROLE")
        } else {
            ProtectedResource(name, "No role")
        }
    }
}