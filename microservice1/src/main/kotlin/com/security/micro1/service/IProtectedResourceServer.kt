package com.security.micro1.service

import com.security.micro1.model.ProtectedResource
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication

interface IProtectedResourceServer {
    fun getProtectedResource(auth: BearerTokenAuthentication): ProtectedResource
}