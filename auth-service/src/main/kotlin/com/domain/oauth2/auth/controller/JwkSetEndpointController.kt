package com.domain.oauth2.auth.controller

import com.nimbusds.jose.jwk.JWKSet
import org.apache.commons.codec.binary.Base64
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.KeyPair


//@RestController
//class JwkSetEndpointController(private val jwkSet: JWKSet,
//                               private val keyPair: KeyPair) {
//
//    @GetMapping("/jwk.json")
//    fun getPublicKey(): Map<String, Any> {
//        return jwkSet.toJSONObject()
//    }
//}
