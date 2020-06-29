package com.domain.oauth2.auth.configuration

import com.domain.oauth2.auth.model.AccountDetails
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter

class CustomEnhanceJwtAccessTokenConverter : JwtAccessTokenConverter() {

    override fun enhance(accessToken: OAuth2AccessToken, authentication: OAuth2Authentication): OAuth2AccessToken {
        val additionalInfo = mutableMapOf<String, Any>()
        if (authentication.userAuthentication == null) {
            return super.enhance(accessToken, authentication)
        }

        val account = authentication.userAuthentication.principal as AccountDetails

        additionalInfo["account"] = mapOf(
                Pair("id", account.id),
                Pair("email", account.email),
                Pair("username", account.username),
                Pair("enabled", account.enabled))

        (accessToken as DefaultOAuth2AccessToken).additionalInformation = additionalInfo
        return super.enhance(accessToken, authentication)
    }
}
