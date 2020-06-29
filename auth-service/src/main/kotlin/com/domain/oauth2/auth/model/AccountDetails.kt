package com.domain.oauth2.auth.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AccountDetails(val id: Long,
                     val email: String,
                     private val username: String,
                     private val password: String?,
                     val enabled: Boolean,
                     val credentialsNonExpired: Boolean,
                     val accountNonExpired: Boolean,
                     val accountNonLocked: Boolean,
                     private val authorities: Collection<CustomGrantedAuthority> = emptyList()) : UserDetails {

    override fun getUsername(): String = username

    override fun getPassword(): String? = password

    override fun getAuthorities(): Collection<GrantedAuthority> = authorities

    override fun isEnabled(): Boolean = enabled

    override fun isCredentialsNonExpired(): Boolean = credentialsNonExpired

    override fun isAccountNonExpired(): Boolean = accountNonExpired

    override fun isAccountNonLocked(): Boolean = accountNonLocked
}

class CustomGrantedAuthority(private val authority: String) : GrantedAuthority {

    override fun getAuthority(): String {
        return authority
    }
}
