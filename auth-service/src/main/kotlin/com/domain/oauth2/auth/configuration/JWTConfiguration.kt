package com.domain.oauth2.auth.configuration

import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.KeyUse
import com.nimbusds.jose.jwk.RSAKey
import org.apache.commons.codec.binary.Base64
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory
import java.security.KeyPair
import java.security.interfaces.RSAPublicKey

@Configuration
class JWTConfiguration {

    @Bean
    fun jwtTokenStore(jwtAccessTokenConverter: JwtAccessTokenConverter): JwtTokenStore {
        return JwtTokenStore(jwtAccessTokenConverter)
    }

//    @Bean
//    fun jwtAccessTokenConverter(keyPair: KeyPair): JwtAccessTokenConverter {
//        val jwtAccessTokenConverter = JwtAccessTokenConverter()
//        jwtAccessTokenConverter.setKeyPair(keyPair)
//        return jwtAccessTokenConverter
//    }


    @Bean // Custom User enhance
    fun jwtAccessTokenConverter(keyPair: KeyPair): JwtAccessTokenConverter {
        val jwtAccessTokenConverter = CustomEnhanceJwtAccessTokenConverter()
        jwtAccessTokenConverter.setKeyPair(keyPair)
        return jwtAccessTokenConverter
    }


//    @Bean // Custom Header for jwk
//    fun jwtAccessTokenConverter(keyPair: KeyPair): JwtAccessTokenConverter {
//        val customHeader = mapOf(Pair("kid", "auth-api-id"))
//        val jwtAccessTokenConverter = CustomHeaderJwtAccessTokenConverter(keyPair, customHeader)
//        jwtAccessTokenConverter.setKeyPair(keyPair)
//        return jwtAccessTokenConverter
//    }

//    @Bean
//    fun jwkSet(): JWKSet {
//        val builder = RSAKey.Builder(keyPair().public as RSAPublicKey)
//                .keyUse(KeyUse.SIGNATURE)
//                .algorithm(JWSAlgorithm.RS256)
//                .keyID("auth-api-id")
//        return JWKSet(builder.build())
//    }

    @Bean
    fun keyPair(): KeyPair {
        return KeyStoreKeyFactory(
                ClassPathResource("jks/auth-service.keystore"), "password".toCharArray())
                .getKeyPair("auth-service")
    }
}
