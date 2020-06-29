package com.domain.oauth2.product.configuration.security

import com.domain.oauth2.product.model.AccountDetails
import com.domain.oauth2.product.model.CustomGrantedAuthority
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter

class CustomUserAuthenticationConverter : DefaultUserAuthenticationConverter() {

    override fun extractAuthentication(map: Map<String, *>): Authentication? {
        if (!map.containsKey("account")) {
            return super.extractAuthentication(map)
        }
        val accountMap = map["account"] as Map<String, Any>
        val authorities = (map["authorities"] as List<String>).map {
            CustomGrantedAuthority(it)
        }
        val accountDetails = AccountDetails(
                id = accountMap["id"].getLong(),
                email = accountMap["email"].getString(),
                username = accountMap["username"].getString(),
                authorities = authorities
        )
        return UsernamePasswordAuthenticationToken(accountDetails, "N/A", accountDetails.authorities)
    }
}

fun Any?.getLong(): Long {
    if (this is Int) {
        return this.toLong()
    }
    return (this as Long)
}

fun Any?.getString(): String {
    return this as String
}
