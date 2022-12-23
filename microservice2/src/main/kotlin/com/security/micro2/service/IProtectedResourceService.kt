package com.security.micro2.service

import com.security.micro2.model.ProtectedResource
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication

interface IProtectedResourceService {
    fun getProtectedResource(auth: BearerTokenAuthentication): ProtectedResource
}
