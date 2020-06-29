package com.domain.oauth2.auth.service

import com.domain.oauth2.auth.model.AccountDetails
import com.domain.oauth2.auth.model.CustomGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {

        // find Account And Roles from Repository and map to AccountDetails
        return AccountDetails(
                id = 100L,
                email = "aykanferhat@gmail.com",
                username = "aykanferhat",
                password = "1234",
                enabled = true,
                accountNonExpired = true,
                credentialsNonExpired = true,
                accountNonLocked = true,
                authorities = listOf(
                        CustomGrantedAuthority("ROLE_CREATE_PRODUCT"),
                        CustomGrantedAuthority("ROLE_UPDATE_PRODUCT"),
                        CustomGrantedAuthority("ROLE_READ_PRODUCT")
                )
        )
    }
}
