package com.domain.oauth2.campaign.configuration.feign

import com.domain.oauth2.campaign.configuration.feign.interceptor.FeignClientAuthorizationRequestInterceptor
import com.domain.oauth2.campaign.configuration.feign.interceptor.FeignClientRequestInterceptor
import feign.RequestInterceptor
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.OAuth2RestTemplate

@Configuration
@EnableFeignClients(basePackages = ["com.domain.oauth2.campaign.client"])
class FeignClientConfiguration

/**
 * ## Default FeignClientsConfiguration
 */

class FeignInterceptorConfiguration {

    @Bean
    fun feignClientRequestInterceptor(): RequestInterceptor {
        return FeignClientRequestInterceptor()
    }

}

class FeignAuthorizationInterceptorConfiguration {

    @Bean
    fun feignClientAuthorizationRequestInterceptor(oauth2RequestTemplate: OAuth2RestTemplate): RequestInterceptor {
        return FeignClientAuthorizationRequestInterceptor(oauth2RequestTemplate)
    }
}

