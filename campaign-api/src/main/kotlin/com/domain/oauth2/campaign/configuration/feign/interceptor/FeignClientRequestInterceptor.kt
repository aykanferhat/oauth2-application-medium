package com.domain.oauth2.campaign.configuration.feign.interceptor

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.security.oauth2.client.OAuth2RestTemplate


class FeignClientRequestInterceptor : RequestInterceptor {

    override fun apply(requestTemplate: RequestTemplate) {

        // Default Request
    }
}


class FeignClientAuthorizationRequestInterceptor(private val oauth2RequestTemplate: OAuth2RestTemplate) : RequestInterceptor {

    companion object {
        const val AUTH_TOKEN = "Authorization"
        const val BEARER = "Bearer"
    }

    override fun apply(requestTemplate: RequestTemplate) {
        requestTemplate.header(AUTH_TOKEN, "$BEARER ${oauth2RequestTemplate.accessToken.value}")
    }
}
