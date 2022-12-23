package com.security.micro3.utils

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

val ADMIN_ROLE: GrantedAuthority = SimpleGrantedAuthority("admin_role")
val CUSTOMER_ROLE: GrantedAuthority = SimpleGrantedAuthority("customer_role")
