package com.security.micro1.service

import com.security.micro1.model.ProtectedResource
import com.security.micro1.utils.ADMIN_ROLE
import com.security.micro1.utils.CUSTOMER_ROLE
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication
import org.springframework.stereotype.Service

@Service
class ProtectedResourceService: IProtectedResourceService {

    @Value("\${microservice.name}")
    private val microserviceName: String? = null

    override fun getProtectedResource(auth: BearerTokenAuthentication): ProtectedResource {
        val name = microserviceName ?: "Default Microservice"
        println("MICROSERVICE NAME: $name, TOKEN: ${auth.token}")

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
