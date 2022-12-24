package com.security.micro2.service

import com.security.micro2.model.ProtectedResource
import com.security.micro2.utils.ADMIN_ROLE
import com.security.micro2.utils.CUSTOMER_ROLE
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication
import org.springframework.stereotype.Service

@Service
class ProtectedResourceService: IProtectedResourceService {

    @Value("\${microservice.name}")
    private val microserviceName: String? = null

    private val logger: Logger = LoggerFactory.getLogger(ProtectedResourceService::class.java)

    override fun getProtectedResource(auth: BearerTokenAuthentication): ProtectedResource {
        val name = microserviceName ?: "Default Microservice"
        logger.info("MICROSERVICE NAME: $name, TOKEN: ${auth.token}")
        
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