package com.domain.oauth2.campaign.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AccountDetails(val id: Long,
                     val email: String,
                     private val username: String,
                     private var authorities: Collection<CustomGrantedAuthority> = emptyList()) : UserDetails {


    fun hasAuthority(authority: String): Boolean {
        return authorities.map { it.authority }.contains(authority)
    }

    override fun getUsername(): String = username

    override fun getPassword(): String? = null

    override fun getAuthorities(): Collection<GrantedAuthority> = authorities

    override fun isEnabled(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true
}

class CustomGrantedAuthority(private val authority: String) : GrantedAuthority {

    override fun getAuthority(): String {
        return authority
    }
}
