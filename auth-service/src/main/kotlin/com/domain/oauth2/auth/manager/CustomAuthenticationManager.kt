package com.domain.oauth2.auth.manager

import com.domain.oauth2.auth.service.CustomUserDetailsService
import org.springframework.security.authentication.*
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationManager(
        private val customUserDetailsService: CustomUserDetailsService) : AuthenticationManager {

    override fun authenticate(authentication: Authentication): Authentication {

        val username = authentication.principal.toString()
        val password = authentication.credentials.toString()

        val userDetails = customUserDetailsService.loadUserByUsername(username)

//        Password validation
//        if (!passwordEncoder.matches(password, userDetails.password)) {
//            log.debug("Authentication failed: password does not match stored value")
//            throw BadCredentialsException("Bad credentials")
//        }
        check(userDetails)
        return UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
    }

    private fun check(userDetails: UserDetails) {

        if (!userDetails.isAccountNonLocked) {
            throw LockedException("User account is locked")
        }

        if (!userDetails.isEnabled) {
            throw DisabledException("User is disabled")
        }

        if (!userDetails.isAccountNonExpired) {
            throw AccountExpiredException("User account has expired")
        }

        if (!userDetails.isCredentialsNonExpired) {
            throw CredentialsExpiredException("User credentials have expired")
        }
    }
}
