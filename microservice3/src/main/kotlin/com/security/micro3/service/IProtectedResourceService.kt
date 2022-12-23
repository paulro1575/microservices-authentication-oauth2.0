package com.security.micro3.service

import com.security.micro3.model.ProtectedResource
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication

interface IProtectedResourceService {
    fun getProtectedResource(auth: BearerTokenAuthentication): ProtectedResource
}