package com.domain.oauth2.auth.configuration

import org.springframework.security.jwt.JwtHelper
import org.springframework.security.jwt.crypto.sign.RsaSigner
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.common.util.JsonParserFactory
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import java.security.KeyPair
import java.security.interfaces.RSAPrivateKey

class CustomHeaderJwtAccessTokenConverter(private val keyPair: KeyPair,
                                          private val customHeaders: Map<String, String>) : JwtAccessTokenConverter() {

    private val signer = RsaSigner(keyPair.private as RSAPrivateKey)
    private val jsonParser = JsonParserFactory.create()

    override fun encode(accessToken: OAuth2AccessToken, authentication: OAuth2Authentication): String {
        try {
            val accessTokenMap = accessTokenConverter.convertAccessToken(accessToken, authentication)
            val content = jsonParser.formatMap(accessTokenMap)
            return JwtHelper.encode(content, signer, customHeaders).encoded
        } catch (exception: Exception) {
            throw IllegalStateException("Cannot convert access token to JSON", exception)
        }
    }
}
