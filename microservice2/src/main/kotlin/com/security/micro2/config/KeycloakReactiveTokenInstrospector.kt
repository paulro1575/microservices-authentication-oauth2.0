package com.security.micro2.config

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal
import org.springframework.security.oauth2.server.resource.introspection.ReactiveOpaqueTokenIntrospector
import reactor.core.publisher.Mono
import kotlin.streams.toList


open class KeycloakReactiveTokenInstrospector (
    private var delegate: ReactiveOpaqueTokenIntrospector
): ReactiveOpaqueTokenIntrospector {

    override fun introspect(token: String): Mono<OAuth2AuthenticatedPrincipal> {
        return delegate.introspect(token).map(this::mapPrincipal)
    }

    private fun mapPrincipal(principal: OAuth2AuthenticatedPrincipal): OAuth2AuthenticatedPrincipal? {
        return DefaultOAuth2AuthenticatedPrincipal(
            principal.name,
            principal.attributes,
            extractAuthorities(principal)
        )
    }

    protected open fun extractAuthorities(principal: OAuth2AuthenticatedPrincipal): Collection<GrantedAuthority>? {
        val realm_access = principal.getAttribute<Map<String, List<String>>>("realm_access")!!!!
        val roles = realm_access["roles"] ?: emptyList()
        val rolesAuthorities: List<GrantedAuthority> = roles.stream()
            .map { role: String? ->
                SimpleGrantedAuthority(
                    role
                )
            }
            .toList()
        val allAuthorities: MutableSet<GrantedAuthority> = HashSet()
        allAuthorities.addAll(principal.authorities)
        allAuthorities.addAll(rolesAuthorities)
        return allAuthorities
    }

}